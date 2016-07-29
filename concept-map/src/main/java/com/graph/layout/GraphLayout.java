package com.graph.layout;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Collection;
import java.util.Iterator;

import com.graph.model.Edge;
import com.graph.model.Graph;
import com.graph.model.GraphListener;

/**
 * @author dtortola
 *
 * @param <V>
 * @param <E>
 *            this parameter is needed only because the layout includes the
 *            graph. If it didn't, then it wouldn't be needed. It may be changed
 *            to be the (calculated) positions of a graph first and the actual
 *            algorithm second, so that the algorithm would actually need no
 *            parameters while the position would
 */
public abstract class GraphLayout<V, E> implements GraphListener<V, E> {
    private Graph<V, E> graph;

    @Override
    public void edgeAdded(Graph<V, E> graph, Edge<V, E> edge) {
    }

    @Override
    public void edgeRemoved(Graph<V, E> graph, Edge<V, E> edge) {
    }

    /**
     * The default implementation iterates over the vertices
     * 
     * @return a rectangle containing all points
     */
    public Rectangle getBounds() {
        if (graph == null || graph.getVerticesCount() < 1) {
            return new Rectangle(0, 0, 1, 1);
        }
        Iterator<V> iterator = graph.getVertices().iterator();
        Point point = getCoordinates(iterator.next());
        // we'll use the start as the uppermost and leftmost limits
        // and the end as the lowermost and rightmost limits
        Rectangle r = new Rectangle(point.x, point.y, point.x, point.y);

        while (iterator.hasNext()) {
            point = getCoordinates(iterator.next());
            r.x = Math.min(r.x, point.x);
            r.y = Math.min(r.y, point.y);
            r.width = Math.max(r.width, point.x);
            r.height = Math.max(r.height, point.y);
        }

        // correct the actual width and height
        r.width = r.width - r.x + 1;
        r.height = r.height - r.y + 1;
        return r;
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
     * The default implementation iterates over the graph's vertices looking for
     * the minimum and maximum to calculate the dimension
     * 
     * @return size of the minimum rectangle containing the coordinates of all
     *         the vertices
     */
    public Dimension getSize() {
        return getBounds().getSize();
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
