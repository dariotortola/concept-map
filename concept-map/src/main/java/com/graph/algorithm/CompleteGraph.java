package com.graph.algorithm;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.graph.model.Edge;
import com.graph.model.EdgeType;
import com.graph.model.Graph;

public class CompleteGraph {
    private CompleteGraph() {
        // utility
    }

    private static <V> void fillNeighbors(Map<V, Collection<V>> neighbors, V v1, V v2) {
        // directed or undirected first direction
        Collection<V> neigh = neighbors.get(v1);
        if (neigh == null) {
            neigh = new HashSet<>();
            neighbors.put(v1, neigh);
        }
        neigh.add(v2);
    }

    /**
     * @param graph
     * @return true if the graph is complete
     */
    public static <V, E> boolean isCompleteGraph(Graph<V, E> graph) {
        // we'll remove edges as we check them
        Collection<Edge<V, E>> edges = graph.getWholeEdges();
        Map<V, Collection<V>> neighbors = new HashMap<>();
        for (Edge<V, E> edge : edges) {
            // directed or undirected first direction
            fillNeighbors(neighbors, edge.getV1(), edge.getV2());

            // undirected second direction
            if (EdgeType.UNDIRECTED.equals(edge.getType())) {
                fillNeighbors(neighbors, edge.getV2(), edge.getV1());
            }
        }

        // all vertices in the map
        int size = graph.getVerticesCount();
        if (size > neighbors.size()) {
            return false;
        }
        // all neighbors contains all other vertex
        // a vertex can be its own neighbour
        size--;
        for (Collection<V> col : neighbors.values()) {
            if (size > col.size()) {
                return false;
            }
        }
        return true;
    }
}
