package com.graph.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.graph.model.Edge;
import com.graph.model.EdgeType;
import com.graph.model.Graph;

public class CycleGraph {

    private CycleGraph() {
        // utility
    }
    
    /**
     * @param graph
     * @return true if graph is a cycle graph
     */
    public static <V, E> List<Edge<V, E>> isCycleGraph(Graph<V, E> graph){

        if (graph.getVerticesCount() < 3) {
            // at least 3
            return Collections.emptyList();
        }
        List<Edge<V, E>> edges = new ArrayList<>(graph.getWholeEdges());
        List<Edge<V, E>> cycle = new ArrayList<>(edges.size());
        
        // we add the first edge
        cycle.add(edges.remove(0));
        V first = cycle.get(0).getV1();
        V last = cycle.get(0).getV2();
       
        // iteration
        while (!edges.isEmpty()){
            
            // we have to be sure it diminishes in each iteration
            int size = edges.size();
            for (int i = 0; i < edges.size(); i++){
                Edge<V, E> edge = edges.get(i);
                if (edge.getV2().equals(first)){
                    // add at the beginning
                    edges.remove(i);
                    cycle.add(0, edge);
                    first = edge.getV1();
                } else if (edge.getV1().equals(last)){
                    // add at the end
                    edges.remove(i);
                    cycle.add(edge);
                    last = edge.getV2();
                } else if (EdgeType.UNDIRECTED.equals(edge.getType())){
                    if (edge.getV1().equals(first)){
                        // add reversed at beginning
                        edges.remove(i);
                        cycle.add(0, edge);
                        first = edge.getV2();
                    } else if (edge.getV2().equals(last)){
                        // add reversed at the end
                        edges.remove(i);
                        cycle.add(edge);
                        last = edge.getV1();
                    }
                }
            }
            if (size == edges.size()){
                // didn't found a suitable edge, not a cycle graph
                return Collections.emptyList();
            }
        }
        return cycle;
    
    }
}
