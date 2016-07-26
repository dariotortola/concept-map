package com.graph.model;

import java.util.Objects;

/**
 * Implementation of all the structure of an edge
 * 
 * @author dtortola
 *
 * @param <V>
 * @param <E>
 */
public final class Edge<V, E> {
    private final E content;
    private final EdgeType type;
    private final V v1;
    private final V v2;

    public boolean contains(V vertex) {
        return v1.equals(vertex) || v2.equals(vertex);
    }

    /**
     * @param content
     *            content of this edge
     * @param v1
     *            initial vertex
     * @param v2
     *            end vertex
     * @param type
     *            type of this vertex
     */
    public Edge(E content, V v1, V v2, EdgeType type) {
        if (content == null || v1 == null || v2 == null || type == null) {
            throw new NullPointerException("All parameters are mandatory");
        }
        this.content = content;
        this.type = type;
        this.v1 = v1;
        this.v2 = v2;
    }

    public E getContent() {
        return content;
    }

    public EdgeType getType() {
        return type;
    }

    public V getV1() {
        return v1;
    }

    public V getV2() {
        return v2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, type, v1, v2);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Edge)) {
            return false;
        }
        Edge<?, ?> other = (Edge<?, ?>) obj;
        return content.equals(other.content) && type.equals(other.type) && v1.equals(other.v1) && v2.equals(other.v2);
    }

}
