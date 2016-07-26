package com.graph.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class DefaultGraph<V, E> implements Graph<V, E> {

    private final Collection<Edge<V, E>> edges = new HashSet<>();
    private final List<GraphListener<V, E>> listeners = new ArrayList<>();
    private final Collection<V> vertices = new HashSet<>();

    @Override
    public boolean addEdge(V v1, V v2, EdgeType type, E content) {
        Edge<V, E> edge = new Edge<>(content, v1, v2, type);
        if (edges.add(edge)) {
            addVertex(v1);
            addVertex(v2);
            for (GraphListener<V, E> l : listeners) {
                l.edgeAdded(this, edge);
            }
            return true;
        }
        return false;
    }

    @Override
    public void addListener(GraphListener<V, E> listener) {
        listeners.add(listener);
    }

    @Override
    public boolean addVertex(V v) {
        if (vertices.add(v)) {
            for (GraphListener<V, E> l : listeners) {
                l.vertexAdded(this, v);
            }
            return true;
        }
        return false;
    }

    @Override
    public Collection<V> getVertices() {
        return Collections.unmodifiableCollection(vertices);
    }

    @Override
    public Collection<Edge<V, E>> getWholeEdges() {
        return Collections.unmodifiableCollection(edges);
    }

    @Override
    public void removeEdge(Edge<V, E> edge) {
        if (edges.remove(edge)) {
            for (GraphListener<V, E> l : listeners) {
                l.edgeRemoved(this, edge);
            }
        }
    }

    @Override
    public void removeListener(GraphListener<V, E> listener) {
        listeners.remove(listener);
    }

    @Override
    public void removeVertex(V v) {
        Collection<Edge<V, E>> edges = new ArrayList<>();
        for (Edge<V, E> edge : this.edges) {
            if (edge.contains(v)) {
                edges.add(edge);
            }
        }
        if (vertices.remove(v)) {
            this.edges.removeAll(edges);
            for (GraphListener<V, E> l : listeners) {
                l.vertexRemoved(this, v, edges);
            }
        }
    }

}
