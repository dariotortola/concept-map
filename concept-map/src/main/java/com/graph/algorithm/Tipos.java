package com.graph.algorithm;

/**
 * TODO estas cuestiones pueden pasarse a la propia interfaz de grafos
 * 
 * @author dtortola
 *
 */
interface Tipos {
    /**
     * A forest is a graph with no cycles, i.e. the disjoint union of one or
     * more trees.
     * 
     * TODO use a cycle detection algorithm
     * 
     * @return true if this graph is a forest
     */
    boolean isForest();

    /**
     * A k-edge-connected graph is a connected graph in which the minimum set of
     * edges that need to be removed to disconnect the graph is k
     * 
     * @param k
     * @return k if this graph is k-edge-connected, 0 if it's not connected
     */
    int getKEdgeConnectedGraph();

    /**
     * A k-vertex-connected graph is a connected graph in which the minimum set
     * of vertices that need to be removed to disconnect the graph is k
     * 
     * @param k
     * @return k if this graph is k-vertex-connected, 0 if it's disconnected
     */
    int getKVertexConnectedGraph();

    /**
     * An oriented graph is a directed graph in which at most one of (x, y) and
     * (y, x) may be arrows of the graph. That is, it is a directed graph that
     * can be formed as an orientation of an undirected graph.
     * 
     * @return true if this graph is oriented
     */
    boolean isOrientedGraph();

    /**
     * A path graph or linear graph of order n ≥ 2 is a graph in which the
     * vertices can be listed in an order v1, v2, …, vn such that the edges are
     * the {vi, vi+1} where i = 1, 2, …, n − 1. Path graphs can be characterized
     * as connected graphs in which the degree of all but two vertices is 2 and
     * the degree of the two remainding vertices is 1. If a path graph occurs as
     * a subgraph of another graph, it is a path in that graph.
     * 
     * @return true if this graph is a path graph
     */
    boolean isPathGraph();

    /**
     * A planar graph is a graph whose vertices and edges can be drawn in a
     * plane such that no two of the edges intersect.
     * 
     * @return true if this graph is a planar graph
     */
    boolean isPlanarGraph();

    /**
     * A quiver or multidigraph is a directed multigraph. A quiver may also have
     * directed loops in it
     * 
     * TODO all directed and at least one multiedges
     * 
     * @return true if this graph is a quiver
     */
    boolean isQuiver();

    /**
     * A directed graph G is called symmetric if, for every arrow in G, the
     * corresponding inverted arrow also belongs to G.
     * 
     * @return true if this graph is symmetric
     */
    boolean isSimmetricGraph();

    /**
     * A simple graph, as opposed to a multigraph, is an undirected graph in
     * which both multiple edges and loops are disallowed.
     * 
     * @return true if this graph is simple
     */
    boolean isSimpleGraph();

    /**
     * DIRECTED && there is a directed path from x to y for every vertices x and
     * y
     * 
     * In a directed graph, an ordered pair of vertices (x, y) is called
     * strongly connected if a directed path leads from x to y.
     * 
     * A strongly connected graph is a directed graph in which every ordered
     * pair of vertices in the graph is strongly connected.
     * 
     * @return true if this graph is strongly connected
     */
    boolean isStronglyConnected();

    /**
     * A tree is a connected graph with no cycles.
     * 
     * @return true if this graph is a tree
     */
    boolean isTree();

    /**
     * DIRECTED && for every vertices x, y there is either a directed or
     * undirected path from x to y, && there are vertices x, y that there are
     * not a directed path
     * 
     * In a directed graph, an ordered pair of vertices (x, y) is called weakly
     * connected if an undirected path leads from x to y after replacing all of
     * its directed edges with undirected edges.
     * 
     * A weakly connected graph if every ordered pair of vertices in the graph
     * is weakly connected.
     * 
     * @return true if this graph is weakly connected
     */
    boolean isWeaklyConnected();

    /**
     * In an undirected graph, an unordered pair of vertices {x, y} is called
     * connected if a path leads from x to y. Otherwise, the unordered pair is
     * called disconnected.
     * 
     * A connected graph is an undirected graph in which every unordered pair of
     * vertices in the graph is connected. Otherwise, it is called a
     * <b>disconnected</b> graph.
     * 
     * In a directed graph, an ordered pair of vertices (x, y) is called
     * strongly connected if a directed path leads from x to y. Otherwise, the
     * ordered pair is called weakly connected if an undirected path leads from
     * x to y after replacing all of its directed edges with undirected edges.
     * Otherwise, the ordered pair is called disconnected.
     * 
     * A strongly connected graph is a directed graph in which every ordered
     * pair of vertices in the graph is strongly connected. Otherwise, it is
     * called a weakly connected graph if every ordered pair of vertices in the
     * graph is weakly connected. Otherwise it is called a <b>disconnected</b>
     * graph.
     * 
     * @return true if this graph is disconnected by either the undirected or
     *         directed criteria
     */
    boolean isDisconnectedGraph();
}
