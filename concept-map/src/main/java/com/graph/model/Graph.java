package com.graph.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.graph.algorithm.CompleteGraph;
import com.graph.algorithm.CycleGraph;

/**
 * @author dtortola
 *
 * @param <V>
 *            information contained in the vertices
 * @param <E>
 *            information contained in the edges
 * @see https://en.wikipedia.org/wiki/Graph_%28discrete_mathematics%29
 */
public interface Graph<V, E> {

    /**
     * Even the most permissive graphs (allowing multiedges, loops, etc) will
     * not add an edge exactly like an edge already in the graph
     * 
     * @param v1
     *            a vertex, will be added if it's not alredy in the graph
     * @param v2
     *            a vertex, will be added if it's not alredy in the graph
     * @param type
     *            a type of vertex allowed
     * @param content
     *            content of the vertex
     * @return true if the edge has been added
     */
    boolean addEdge(V v1, V v2, EdgeType type, E content);

    void addListener(GraphListener<V, E> listener);

    /**
     * @param v
     * @return true if v was not already in the graph
     */
    boolean addVertex(V v);

    /**
     * @param type
     * @return true if all edges are of this type
     */
    default boolean allEdgesOfType(EdgeType type) {
        return getWholeEdges().stream().allMatch(e -> type.equals(e.getType()));
    }

    /**
     * @param vertex
     * @return amount of edges that has vertex as one of its extremes, also
     *         called valency; an edge connecting this vertex with the same
     *         vertex should be counted twice
     */
    default int getDegree(V vertex) {
        Collection<Edge<V, E>> edges = getWholeEdges();
        int total = 0;
        for (Edge<V, E> edge : edges) {
            if (vertex.equals(edge.getV1())) {
                total++;
            }
            if (vertex.equals(edge.getV2())) {
                total++;
            }
        }
        return total;
    }

    /**
     * @return edges of this graph (possibly a unmodifiable or defensive-copied
     *         collection)
     */
    default Collection<E> getEdges() {
        Collection<Edge<V, E>> wholeEdges = getWholeEdges();
        List<E> list = new ArrayList<>(wholeEdges.size());
        for (Edge<V, E> edge : wholeEdges) {
            list.add(edge.getContent());
        }
        return list;
    }

    /**
     * @return amount of edges, also called size of the graph
     */
    default int getEdgesCount() {
        return getWholeEdges().size();
    }

    /**
     * A regular graph is a graph in which each vertex has the same number of
     * neighbours, i.e., every vertex has the same degree. A regular graph with
     * vertices of degree k is called a k‑regular graph or regular graph of
     * degree k
     * 
     * @return null if this graph is not regular, k if it's a k-regular graph
     */
    default Integer getKRegularGraph() {
        Collection<V> vertices = getVertices();
        if (vertices.isEmpty()) {
            return 0;
        }
        // not empty
        Iterator<V> iterator = vertices.iterator();
        int degree = getDegree(iterator.next());
        while (iterator.hasNext()) {
            if (degree != getDegree(iterator.next())) {
                // different degrees
                return null;
            }
        }
        return degree;
    }

    /**
     * @return vertices of this graph (possibly a unmodifiable or
     *         defensive-copied collection)
     */
    Collection<V> getVertices();

    /**
     * @return amount of vertices, also called order of the graph
     */
    default int getVerticesCount() {
        return getVertices().size();
    }

    /**
     * @return all information about this graph's edges (possibly a unmodifiable
     *         or defensive-copied collection)
     */
    Collection<Edge<V, E>> getWholeEdges();

    /**
     * @param vertex1
     * @param vertex2
     * @return true if there is an edge conecting vertex1 and vertex2
     */
    default boolean isAdjacent(V vertex1, V vertex2) {
        Collection<Edge<V, E>> edges = getWholeEdges();
        for (Edge<V, E> edge : edges) {
            if (vertex1.equals(edge.getV1()) && vertex2.equals(edge.getV2())) {
                // valid for both directed and undirected edges
                return true;
            } else if (EdgeType.UNDIRECTED.equals(edge.getType()) && vertex1.equals(edge.getV2())
                    && vertex2.equals(edge.getV1())) {
                // undirected
                return true;
            }
        }
        return false;
    }

    /**
     * A complete graph is a graph in which each pair of vertices is joined by
     * an edge. A complete graph contains all possible edges.
     * 
     * @return true if this graph is complete
     */
    default boolean isCompleteGraph() {
        return CompleteGraph.isCompleteGraph(this);
    }

    /**
     * A cycle graph or circular graph of order n ≥ 3 is a graph in which the
     * vertices can be listed in an order v1, v2, …, vn such that the edges are
     * the {vi, vi+1} where i = 1, 2, …, n − 1, plus the edge {vn, v1}. Cycle
     * graphs can be characterized as connected graphs in which the degree of
     * all vertices is 2. If a cycle graph occurs as a subgraph of another
     * graph, it is a cycle or circuit in that graph.
     * 
     * @return the list of edges ordered in the cycle if it's a cycle graph, an
     *         empty list otherwise
     */
    default List<Edge<V, E>> isCycleGraph() {
        return CycleGraph.isCycleGraph(this);
    }

    /**
     * A directed graph or digraph is a graph in which edges have orientations.
     * 
     * @return true if there is not undirected edges
     */
    default boolean isDirectedGraph() {
        return allEdgesOfType(EdgeType.DIRECTED);
    }

    /**
     * An undirected graph is a graph in which edges have no orientation. The
     * edge (x, y) is identical to the edge (y, x), i.e., they are not ordered
     * pairs, but sets {x, y} (or 2-multisets) of vertices. The maximum number
     * of edges in an undirected graph without a loop is n(n − 1)/2.
     * 
     * @return true if there is not directed edges
     */
    default boolean isUndirectedGraph() {
        return allEdgesOfType(EdgeType.UNDIRECTED);
    }

    /**
     * Removes edge
     * 
     * @param edge
     */
    void removeEdge(Edge<V, E> edge);

    void removeListener(GraphListener<V, E> listener);

    /**
     * Removes v and all the edges linked to it
     * 
     * @param v
     */
    void removeVertex(V v);

}
