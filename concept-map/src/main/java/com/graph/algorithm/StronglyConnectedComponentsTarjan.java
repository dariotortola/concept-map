package com.graph.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

import com.graph.model.Edge;
import com.graph.model.Graph;

/**
 * Tarjan's algorithm takes a directed graph as input and outputs a collection
 * of strongly connected components. It runs in linear time.
 * 
 * Each vertex of the graph appears in exactly one component (a single vertex is
 * a strongly connected component by itself)
 * 
 * @author dtortola
 * @see https://en.wikipedia.org/wiki/Tarjan'
 *      s_strongly_connected_components_algorithm
 */
public class StronglyConnectedComponentsTarjan<V> {
    private Collection<Collection<V>> components;
    private Graph<V, ?> graph;
    private int index;
    /**
     * keeps the index the vertices are discovered
     */
    private Map<V, Integer> indexes;
    /**
     * keeps the smallest index of any node known to be reachable from v,
     * including v itself
     */
    private Map<V, Integer> lowlink;
    private Stack<V> stack;

    /**
     * @param graph
     *            directed graph
     */
    private StronglyConnectedComponentsTarjan(Graph<V, ?> graph) {
        // utility
        this.graph = graph;
    }

    /**
     * @param graph
     * @return strongly connected components as calculated by Tarjan's algorithm
     */
    public static <V> Collection<Collection<V>> getStronglyConnectedComponents(Graph<V, ?> graph) {
        StronglyConnectedComponentsTarjan<V> algorithm = new StronglyConnectedComponentsTarjan<>(
                graph);
        algorithm.start();
        return algorithm.components;
    }

    private void start() {
        index = 0;
        this.stack = new Stack<>();
        this.indexes = new HashMap<>();
        this.lowlink = new HashMap<>();
        this.components = new ArrayList<>();

        for (V v : graph.getVertices()) {
            if (!indexes.containsKey(v)) {
                strongConnect(v);
            }
        }
    }

    private void strongConnect(V v) {
        indexes.put(v, index);
        lowlink.put(v, index);
        index++;
        stack.push(v);

        for (Edge<V, ?> edge : graph.getWholeEdges()) {
            if (v.equals(edge.getV1())) {
                // assumes directed
                if (!indexes.containsKey(edge.getV2())) {
                    // v2 still not visited, recourse
                    strongConnect(edge.getV2());
                    lowlink.put(v, Math.min(lowlink.get(v), lowlink.get(edge.getV2())));
                } else if (stack.contains(edge.getV2())) {
                    // v2 in stack, hence in current SCC
                    lowlink.put(v, Math.min(lowlink.get(v), indexes.get(edge.getV2())));
                }
            }
        }

        if (lowlink.get(v).equals(indexes.get(v))) {
            // start a new SCC
            Collection<V> scc = new HashSet<>();
            V w;
            do {
                w = stack.pop();
                // add w to SCC
                scc.add(w);
            } while (!w.equals(v));
            // output the current SCC
            components.add(scc);
        }
    }
}
