package com.greatgame.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.greatgame.environment.Behaviour;
import com.greatgame.environment.Environment;
import com.greatgame.environment.Location;
import com.greatgame.environment.ModeName;

import java.util.ArrayList;
import java.util.List;

public class ConcreteEnvironment implements Environment {
    World world;
    List<Behaviour> behaviours;
    List<Location> loadedLocations;
    Behaviour player;
    ModeName currentMode;
    ModeName nextMode;
    int originalScreenX = 0, originalScreenY = 0;

    public ConcreteEnvironment() {
        behaviours = new ArrayList<>();
    }

    public void addBehaviour(Behaviour behaviour) {
        behaviours.add(behaviour);
    }

    @Override
    public Behaviour getPlayer() {
        return player;
    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        behaviours.forEach(b -> b.draw(batch, parentAlpha));
    }

    @Override
    public void checkContents(int x, int y) {
        for(int i = 0; i < 2; i++) {
            for(int o = 0; o < 2; o++) {
                if(loadedLocations.stream().noneMatch(l -> l.x == x && l.y  == y)) {
                    loadLocation(x,y);
                }
            }
        }
        loadedLocations.forEach(l -> {
            if(Math.abs(l.x - x) > 2  || Math.abs(l.y - y) > 2) {
                removeLocation(l);
            }
        });
    }

    private void loadLocation(int x, int y) {
        Location l = world.generate(x,y);
        loadedLocations.add(l);
        l.setScreenPosition(x - originalScreenX, y - originalScreenY);
        world.generateContents(x,y);
    }

    private void removeLocation(Location l) {
        behaviours.removeIf(behaviour -> behaviour.getOriginalLocation() == l);
    }

    @Override
    public boolean freePoint(float x, float y) {
        return behaviours.stream().noneMatch(behaviour ->
                behaviour.getX() - behaviour.getWidth() / 2 < x && x < behaviour.getX() + behaviour.getWidth() / 2 &&
                behaviour.getY() - behaviour.getHeight() / 2 < y && y < behaviour.getY() + behaviour.getHeight() / 2);
    }

    @Override
    public boolean allowedPosition(Behaviour behaviour, float newX, float newY) {
        float left = newX - behaviour.getWidth() / 2;
        float right = newX + behaviour.getWidth() / 2;
        float up = newY - behaviour.getHeight() / 2;
        float down = newY + behaviour.getHeight() / 2;
        return behaviours.stream().allMatch(b ->
                b == behaviour || !((b.getX() - b.getWidth() / 2 <= right && right <= b.getX() + b.getWidth() / 2
                || b.getX() - b.getWidth() / 2 <= left && left <= b.getX() + b.getWidth() / 2) &&
                (b.getY() - b.getHeight() / 2 <= up && up <= b.getY() + b.getHeight() / 2
                        || b.getY() - b.getHeight() / 2 <= down && down <= b.getY() + b.getHeight() / 2)));
    }

    @Override
    public float dist(Vector2 v1, Vector2 v2) {
        return (float) Math.sqrt(Math.pow(v1.x - v2.x, 2) + Math.pow(v2.y  -v1.y, 2));
    }

    @Override
    public boolean freeView(Vector2 v1, Vector2 v2) {
        Segment segment = new Segment(v1.x, v1.y, v2.x, v2.y);
        return behaviours.stream().noneMatch(b ->
                segment.intersectsHorizontalLine(b.getY() - b.getHeight() / 2,
                        b.getX() - b.getWidth() / 2, b.getX() + b.getWidth() / 2) ||
                        segment.intersectVerticalLine(b.getX() - b.getWidth() / 2,
                                b.getY() - b.getHeight() / 2, b.getY() + b.getHeight() / 2) ||
                        segment.intersectsHorizontalLine(b.getY() + b.getHeight() / 2,
                                b.getX() - b.getWidth() / 2, b.getX() + b.getWidth() / 2) ||
                        segment.intersectVerticalLine(b.getX() + b.getWidth() / 2,
                                b.getY() - b.getHeight() / 2, b.getY() + b.getHeight() / 2));
    }

    @Override
    public void act(float delta) {
        behaviours.forEach(b -> b.act(delta));
    }

    @Override
    public void triggerModeChange(ModeName newMode) {
        nextMode = newMode;
    }

    @Override
    public List<Vector2> findPath(Vector2 start, Vector2 end) {
        return null;
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

        boolean horizontal() {
            return y1 == y2;
        }

        boolean vertical() {
            return x1 == x2;
        }

        boolean intersectsHorizontalLine(float yLine, float x1, float x2) {
            if(x1 > x2) {
                float t = x1;
                x1 = x2;
                x2 = t;
            }
            if(horizontal()) {
                return y1 == yLine && ((x1 < this.x1 && this.x1 < x2) || (x1 < this.x2 && this.x2 < x2));
            } else if (vertical()) {
              return x1 < this.x1 && this.x1 < x2;
            } else {
                float xIntersection = (yLine - q) / m;
                return x1 < xIntersection && xIntersection < x2;
            }
        }

        boolean intersectVerticalLine(float xLine, float y1, float y2) {
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
                return y1 < yIntersection && yIntersection < y2;
            }
        }
    }
}
