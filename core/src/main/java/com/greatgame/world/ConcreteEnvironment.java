package com.greatgame.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.environment.Behaviour;
import com.greatgame.environment.Environment;
import com.greatgame.environment.Location;
import com.greatgame.environment.ModeName;
import com.greatgame.explorationBehaviourState.PlayerExplorationBehaviourState;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.greatgame.factory.BehaviourRefiner.behaviourRefiner;

public class ConcreteEnvironment implements Environment {
    World world;
    Stage stage;
    List<Location> loadedLocations;
    CreatureBehaviour player;
    ModeName currentMode;
    ModeName nextMode;
    int originalScreenX = 0, originalScreenY = 0;
    PathFinder pathFinder = new FreePositionPathFinder(this);
    boolean newModeTriggered = false;

    public ConcreteEnvironment() {
        stage = new Stage(new ScreenViewport());
        loadedLocations = new ArrayList<>();
    }

    public void addBehaviour(Behaviour behaviour) {
        stage.addActor(behaviour);
        behaviour.setEnvironment(this);
        behaviour.setName(behaviour.getOriginalLocation().x + "_" + behaviour.getOriginalLocation().y +
                "_" + behavioursInLocation(behaviour.getOriginalLocation()));
        behaviourRefiner.refine(behaviour);
    }

    private int behavioursInLocation(Location l) {
        int result = 0;
        for (Actor actor : stage.getActors()) {
            Behaviour behaviour = (Behaviour) actor;
            if (behaviour.getOriginalLocation() == l) {
                result++;
            }
        }
        return result;
    }

    @Override
    public CreatureBehaviour getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(CreatureBehaviour behaviour, Stage uiStage) {
        this.player = behaviour;
        stage.addActor(player);
        player.setEnvironment(this);
        behaviourRefiner.refine(player);
        PlayerExplorationBehaviourState state = (PlayerExplorationBehaviourState) player.getState();
        state.setUiStageReference(uiStage);
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    @Override
    public List<Location> getLoadedLocations() {
        return loadedLocations;
    }

    @Override
    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public void update(float delta) {
        stage.act(delta);

        stage.getViewport().getCamera().
                    position.set(world.getEnvironment().getPlayer().getX(),
                            world.getEnvironment().getPlayer().getY(), 1);

        stage.getActors().sort((o1, o2) -> {
            float bottom1 = o1.getY() - o1.getHeight() / 2;
            float bottom2 = o2.getY() - o2.getHeight() / 2;
            if(bottom1 > bottom2) {
                return -1;
            } else if(bottom1 < bottom2) {
                return 1;
            }
            return 0;
        });

        stage.draw();
    }

    @Override
    public void setOriginalLocation(int x, int y) {
        originalScreenX = x;
        originalScreenY = y;
    }

    @Override
    public int getOriginalLocationX() {
        return originalScreenX;
    }

    @Override
    public int getOriginalLocationY() {
        return originalScreenY;
    }

    @Override
    public void checkContents(int x, int y) {
        int dim = 1;
        for(int i = -dim; i <= dim; i++) {
            for(int o = -dim; o <= dim; o++) {
                final int i_ = i, o_ = o;
                if(loadedLocations.stream().noneMatch(l -> l.x == x + i_ && l.y  == y + o_)) {
                    loadLocation(x + i,y + o);
                }
            }
        }
        for(Iterator<Location> locationIterator = loadedLocations.iterator(); locationIterator.hasNext();) {
            Location l = locationIterator.next();
            if(Math.abs(l.x - x) > dim || Math.abs(l.y - y) > dim) {
                removeLocation(l);
                locationIterator.remove();
            }
        }
    }

    private void loadLocation(int x, int y) {
        Location l = world.generate(x,y);
        loadedLocations.add(l);
        l.setScreenPosition(x - originalScreenX, y - originalScreenY);
        world.generateContents(x,y);
    }

    private void removeLocation(Location l) {
        for(Iterator<Actor> iterator = stage.getActors().iterator(); iterator.hasNext();) {
            Behaviour behaviour = (Behaviour) iterator.next();
            if(behaviour.getOriginalLocation() == l) {
                iterator.remove();
            }
        }
    }

    @Override
    public Behaviour behaviourInPosition(float x, float y) {
        for(Actor actor : stage.getActors()) {
            if(actor.getX() - actor.getWidth() / 2 < x && x < actor.getX() + actor.getWidth() / 2 &&
                    actor.getY() - actor.getHeight() / 2 < y && y < actor.getY() + actor.getHeight() / 2) {
                return (Behaviour) actor;
            }
        }
        return null;
    }

    @Override
    public boolean allowedPosition(Behaviour behaviour, float newX, float newY, boolean touchable) {
        float left = newX - behaviour.getWidth() / 2;
        float right = newX + behaviour.getWidth() / 2;
        float up = newY + behaviour.getHeight() / 2;
        float down = newY - behaviour.getHeight() / 2;
        for(Actor b : stage.getActors()) {
            float b_left = b.getX() - b.getWidth() / 2;
            float b_right = b.getX() + b.getHeight() / 2;
            float b_up = b.getY() + b.getHeight() / 2;
            float b_down = b.getY() - b.getHeight() / 2;
            if((!touchable || b.isTouchable()) && b != behaviour
                    && (((b_left <= right && right <= b_right) || (b_left <= left && left <= b_right) ||
                    (left <= b_left && b_left <= right) || (left <= b_right && b_right <= right)) &&
                    ((b_down <= up && up <= b_up) || (b_down <= down && down <= b_up) ||
                            (down <= b_down && b_down <= up) || (down <= b_up && b_up <= up)))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public float dist(Vector2 v1, Vector2 v2) {
        return (float) Math.sqrt(Math.pow(v1.x - v2.x, 2) + Math.pow(v2.y  -v1.y, 2));
    }

    @Override
    public boolean freeView(Behaviour b1, Behaviour b2) {
        Segment segment = new Segment(b1.getX(), b1.getY(), b2.getX(), b2.getY());
        for(Actor b : stage.getActors()) {
            if(b.isTouchable() && b != b1 && b != b2 && segment.intersectsActor(b)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean freeView(Vector2 pos1, Vector2 pos2) {
        Segment segment = new Segment(pos1.x, pos1.y, pos2.x, pos2.y);
        for(Actor b : stage.getActors()) {
            if(b.isTouchable() && segment.intersectsActor(b)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void triggerModeChange(ModeName newMode) {
        nextMode = newMode;
        newModeTriggered  = true;
    }

    @Override
    public ModeName getCurrentMode() {
        return currentMode;
    }

    @Override
    public void setCurrentMode(ModeName name) {
        currentMode = name;
    }

    @Override
    public ModeName getNextMode() {
        return nextMode;
    }

    @Override
    public List<Vector2> findPath(CreatureBehaviour creature, Vector2 start, Vector2 end) {
        return pathFinder.findPath(creature, start, end);
    }

    static class Segment {
        final float x1, y1;
        final float x2, y2;
        final float m,q;
        Segment(float x1, float y1, float x2, float y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            m = (y2 - y1) / (x2 - x1);
            q = y1 - m * x1;
        }

        private boolean horizontal() {
            return y1 == y2;
        }

        private boolean vertical() {
            return x1 == x2;
        }

        private boolean intersectsHorizontalLine(float yLine, float x1, float x2) {
            if(x1 > x2) {
                float t = x1;
                x1 = x2;
                x2 = t;
            }
            if(horizontal()) {
                return y1 == yLine && ((x1 < this.x1 && this.x1 < x2) || (x1 < this.x2 && this.x2 < x2));
            } else if (vertical()) {
              return x1 < this.x1 && this.x1 < x2 &&
                      (this.y1 < this.y2 ? this.y1 < yLine && yLine < this.y2 :
                              this.y2 < yLine && yLine < this.y1);
            } else {
                float xIntersection = (yLine - q) / m;
                return x1 < xIntersection && xIntersection < x2 &&
                        (this.x1 < this.x2 ? this.x1 < xIntersection && xIntersection < this.x2 :
                            this.x2 < xIntersection && xIntersection < this.x1);
            }
        }

        private boolean intersectVerticalLine(float xLine, float y1, float y2) {
            if(y1 > y2) {
                float t = y1;
                y1 = y2;
                y2 = t;
            }
            if(vertical()) {
                return x1 == xLine && ((y1 < this.y1 && this.y1 < y2) || (y1 < this.y2 && this.y2 < y2));
            } else if (horizontal()) {
                return y1 < this.y1 && this.y1 < y2;
            } else {
                float yIntersection = xLine * m + q;
                return y1 < yIntersection && yIntersection < y2 &&
                        (this.y1 < this.y2 ? this.y1 < yIntersection && yIntersection < this.y2 :
                            this.y2 < yIntersection && yIntersection < this.y1);
            }
        }

        boolean intersectsActor(Actor b) {
            return intersectsHorizontalLine(b.getY() - b.getHeight() / 2,
                    b.getX() - b.getWidth() / 2, b.getX() + b.getWidth() / 2) ||
                    intersectVerticalLine(b.getX() - b.getWidth() / 2,
                            b.getY() - b.getHeight() / 2, b.getY() + b.getHeight() / 2) ||
                    intersectsHorizontalLine(b.getY() + b.getHeight() / 2,
                            b.getX() - b.getWidth() / 2, b.getX() + b.getWidth() / 2) ||
                    intersectVerticalLine(b.getX() + b.getWidth() / 2,
                            b.getY() - b.getHeight() / 2, b.getY() + b.getHeight() / 2);
        }
    }
}
