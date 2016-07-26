package com.graph.layout;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

/**
 * A user controlled layout
 * 
 * @author dtortola
 *
 * @param <V>
 * @param <E>
 */
public class StaticLayout<V, E> extends GraphLayout<V, E> {

    private final Map<V, Point> coordinates = new HashMap<>();

    @Override
    public Point getCoordinates(V vertex) {
        Point p = coordinates.get(vertex);
        if (p == null) {
            p = new Point(0, 0);
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
