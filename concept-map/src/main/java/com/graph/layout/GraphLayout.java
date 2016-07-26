package com.graph.layout;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Collection;
import java.util.Iterator;

import com.graph.model.Edge;
import com.graph.model.Graph;
import com.graph.model.GraphListener;

public abstract class GraphLayout<V, E> implements GraphListener<V, E> {
    private Graph<V, E> graph;

    @Override
    public void edgeAdded(Graph<V, E> graph, Edge<V, E> edge) {
        recalculate();
    }

    @Override
    public void edgeRemoved(Graph<V, E> graph, Edge<V, E> edge) {
        recalculate();
    }

    /**
     * @param vertex
     * @return center coordinates of vertex
     */
    public abstract Point getCoordinates(V vertex);

    public Graph<V, E> getGraph() {
        return graph;
    }

    /**
     * @return size of the minimum rectangle containing the coordinates of all
     *         the vertices
     */
    public Dimension getSize() {
        if (graph == null || graph.getVerticesCount() < 1) {
            return new Dimension(0, 0);
        }

        Iterator<V> iterator = graph.getVertices().iterator();
        Point min = getCoordinates(iterator.next());
        Point max = min;
        while (iterator.hasNext()) {
            Point current = getCoordinates(iterator.next());
            min = new Point(Math.min(min.x, current.x), Math.min(min.y, current.y));
            max = new Point(Math.max(min.x, current.x), Math.max(min.y, current.y));
        }
        return new Dimension(max.x - min.x, max.y - min.y);
    }

    /**
     * recalculate the position of every vertex
     */
    protected abstract void recalculate();

    public void setGraph(Graph<V, E> graph) {
        if (this.graph != null) {
            this.graph.removeListener(this);
        }
        this.graph = graph;
        if (this.graph != null) {
            this.graph.addListener(this);
        }
        // recalculate positions
        recalculate();
    }

    @Override
    public void vertexAdded(Graph<V, E> graph, V v) {
        recalculate();
    }

    @Override
    public void vertexRemoved(Graph<V, E> graph, V v, Collection<Edge<V, E>> edges) {
        recalculate();
    }

}
