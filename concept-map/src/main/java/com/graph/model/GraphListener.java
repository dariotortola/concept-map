package com.graph.model;

import java.util.Collection;

public interface GraphListener<V, E> {

    /**
     * called when a vertex has been added from graph
     * 
     * @param graph
     * @param v
     */
    void vertexAdded(Graph<V, E> graph, V v);

    /**
     * @param graph
     * @param edge
     */
    void edgeAdded(Graph<V, E> graph, Edge<V, E> edge);

    /**
     * @param graph
     * @param v
     * @param edges
     *            collection of edges that had v as an end and hence have been
     *            removed
     */
    void vertexRemoved(Graph<V, E> graph, V v, Collection<Edge<V, E>> edges);

    void edgeRemoved(Graph<V, E> graph, Edge<V, E> edge);
}
