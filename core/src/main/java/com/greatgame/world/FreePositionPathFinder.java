package com.greatgame.world;

import com.badlogic.gdx.math.Vector2;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.contentGenerators.ContentGenerator;
import com.greatgame.environment.Environment;
import com.greatgame.environment.Location;

import java.util.*;

import static com.greatgame.explorationBehaviourState.ExplorationBehaviourState.PIXELS_PER_METER;

public class FreePositionPathFinder implements PathFinder {
    Map<Vector2, Boolean> freePositions = new HashMap<>();
    Environment environment;

    public FreePositionPathFinder(Environment environment) {
        this.environment = environment;
    }

    @Override
    public List<Vector2> findPath(CreatureBehaviour creature, Vector2 start, Vector2 end) {
        if (endBlocked(creature, end)) {
            return Collections.emptyList();
        }
        update(creature);
        try {
            Vector2 first = nearest(start, creature.getWidth() / 2);
            Vector2 last = nearest(end, creature.getWidth() / 2);
            Set<Node> done = new HashSet<>(), current = new HashSet<>(), checking = new HashSet<>();
            current.add(new Node(first, start, environment.dist(first, start)));
            Node lastNode = null;
            boolean found = false;
            while (!found) {
                current.forEach(node -> expand(node, creature, done, current, checking));
                Optional<Node> opt =  searchNodeByPosition(checking, last);
                if(opt.isPresent()) {
                    lastNode = opt.get();
                    found = true;
                }
                flushSets(done, current, checking);
            }
            done.addAll(current);
            return buildPath(end, start, lastNode, done);
        } catch (RuntimeException e) {
            return Collections.emptyList();
        }
    }

    private boolean endBlocked(CreatureBehaviour creature, Vector2 end) {
        return !environment.allowedPosition(creature, end.x, end.y, true);
    }

    private void update(CreatureBehaviour creature) {
        freePositions.clear();
        environment.getLoadedLocations().forEach(l -> updateLocation(creature, l));
    }

    private void updateLocation(CreatureBehaviour creature, Location location) {
        float steps = ContentGenerator.PIXELS_PER_LOCATION / creature.getWidth() * 2;
        float step = creature.getWidth() / 2;
        for (int i = 0; i < steps; i++) {
            for (int o = 0; o < steps; o++) {
                float x = location.getScreenX() * ContentGenerator.PIXELS_PER_LOCATION + i * step;
                float y = location.getScreenY() * ContentGenerator.PIXELS_PER_LOCATION + o * step;
                freePositions.put(new Vector2(x, y), environment.allowedPosition(creature, x, y, true));
            }
        }
    }

    private Vector2 nearest(Vector2 point, float step) {
        float dx = point.x % step;
        float dy = point.y % step;
        if(dx == 0 && dy == 0) {
            return point;
        }
        float x_increased = dx > 0 ? point.x + step - dx : point.x - dx;
        float x_decreased = dx > 0 ? point.x - dx : point.x - step - dx;
        float y_increased = dy > 0 ? point.y + step - dy : point.y - dy;
        float y_decreased = dy > 0 ? point.y - dy : point.y - step - dy;
        Vector2 result = new Vector2(x_increased, y_increased);
        if(freePositions.get(result)) return result;
        result.set(x_decreased, y_increased);
        if (freePositions.get(result)) return result;
        result.set(x_increased, y_decreased);
        if (freePositions.get(result)) return result;
        result.set(x_decreased, y_decreased);
        if (freePositions.get(result)) return result;
        throw new RuntimeException("Couldn't find a free position near point (" + point.x + ", " + point.y + ")");
    }

    private static class Node {
        public Vector2 position;
        public Vector2 previous;
        public float distance;

        public Node(Vector2 position, Vector2 previous, float distance) {
            this.position = position;
            this.previous = previous;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(position, node.position);
        }

        @Override
        public String toString() {
            return position.toString();
        }

        @Override
        public int hashCode() {
            return Objects.hash(position);
        }
    }

    private void expand(Node c, CreatureBehaviour creature, Set<Node> done, Set<Node> current, Set<Node> checking) {
        if(c.distance + creature.getWidth() / 2 > creature.getSpeed() * Math.sqrt(2) * PIXELS_PER_METER) {
            throw new RuntimeException("We have gone too far...");
        }
        Vector2 position = c.position;
        considerPosition(new Vector2(position.x + creature.getWidth() / 2, position.y), position, c.distance, done, current, checking);
        considerPosition(new Vector2(position.x - creature.getWidth() / 2, position.y), position, c.distance, done, current, checking);
        considerPosition(new Vector2(position.x, position.y + creature.getHeight() / 2), position, c.distance, done, current, checking);
        considerPosition(new Vector2(position.x, position.y - creature.getHeight() / 2), position, c.distance, done, current, checking);
    }

    private void considerPosition(Vector2 position, Vector2 previous, float distance, Set<Node> doneSet, Set<Node> currentSet, Set<Node> checkingSet) {
        if(searchNodeByPosition(doneSet, position).isEmpty() && searchNodeByPosition(currentSet, position).isEmpty() &&
            freePositions.get(position)) {
            checkingSet.add(new Node(position, previous, distance + environment.dist(position, previous)));
        }
    }

    private Optional<Node> searchNodeByPosition(Set<Node> nodes, Vector2 position) {
        return nodes.stream().filter(n -> n.position.equals(position)).findAny();
    }

    private void flushSets(Set<Node> done, Set<Node> current, Set<Node> checking) {
        done.addAll(current);
        current.clear();
        current.addAll(checking);
        checking.clear();
    }

    private List<Vector2> buildPath(Vector2 end, Vector2 start, Node node, Set<Node> nodeSet) {
        List<Vector2> result = new ArrayList<>();
        result.add(end);
        while (!node.previous.equals(start)) {
            result.add(node.position);
            node = searchNodeByPosition(nodeSet, node.previous).orElseThrow(
                    () -> new RuntimeException("Previous node not found"));
        }
        result.add(start);
        simplify(result);
        return result;
    }

    private void simplify(List<Vector2> path) {
        int previous = 0;
        for(int i = 2; i < path.size(); i++) {
            if(environment.freeView(path.get(previous), path.get(i))) {
                path.remove(i - 1);
                i--;
            } else {
                previous = i;
                i++;
            }
        }
    }
}
