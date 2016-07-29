package com.graph.layout;

import java.awt.Point;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.graph.model.Edge;
import com.graph.model.Graph;

/**
 * Instead of calculating, this layout let's the client set each vertex
 * position, defaulting to 0,0 if not set
 * 
 * @author dtortola
 *
 * @param <V>
 * @param <E>
 */
public class StaticGraphLayout<V, E> extends GraphLayout<V, E> {

    private final Map<V, Point> coordinates = new HashMap<>();

    @Override
    public void vertexAdded(Graph<V, E> graph, V v) {
        // nothing needed
    }

    @Override
    public void vertexRemoved(Graph<V, E> graph, V v, Collection<Edge<V, E>> edges) {
        // nothing needed
    }

    @Override
    public Point getCoordinates(V vertex) {
        Point p = coordinates.get(vertex);
        if (p == null) {
            p = new Point(0, 0);
            coordinates.put(vertex, p);
        }
        return p;
    }

    @Override
    protected void recalculate() {
        coordinates.clear();
    }

    public void setCoordinates(V vertex, Point coordinates) {
        this.coordinates.put(vertex, coordinates);
    }
}
