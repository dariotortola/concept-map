package com.graph.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;

import com.graph.layout.GraphLayout;
import com.graph.model.Edge;
import com.graph.model.Graph;

/**
 * A graph viewer is based on a {@link GraphLayout} to know where it should
 * paint each vertex. Knowing each vertex, it can then paint the edges. Each
 * vertex is to be painted by itself, with a shape, colors, etc
 * 
 * @author dtortola
 *
 * @param <V>
 * @param <E>
 */
public class GraphViewer<V, E> extends JComponent {

    /**
     * 
     */
    private static final long serialVersionUID = 5021911491325587584L;

    /**
     * this indicates where each vertex should be, without regard to this
     * component's size
     */
    private GraphLayout<V, E> graphLayout;
    private final Map<V, Point> scaled = new HashMap<>();

    private void calculateScaled() {
        scaled.clear();

        if (getGraph() != null) {
            // original bounds
            Rectangle graphLayoutBounds = graphLayout.getBounds();
            Dimension graphLayoutSize = graphLayoutBounds.getSize();

            // scaling factor
            double fx = getWidth() / (double) graphLayoutSize.width;
            double fy = getHeight() / (double) graphLayoutSize.height;

            for (V v : getGraph().getVertices()) {
                Point original = graphLayout.getCoordinates(v);
                Point current = new Point((int) (fx * (original.x - graphLayoutBounds.x)),
                        (int) (fy * (original.y - graphLayoutBounds.y)));
                if (current.x > getWidth() || current.y > getHeight()){
                    System.out.println();
                }
                scaled.put(v, current);
            }
        }
    }

    public GraphLayout<V, E> getGraphLayout() {
        return graphLayout;
    }

    public Graph<V, E> getGraph() {
        return graphLayout.getGraph();
    }

    public void setGraph(Graph<V, E> graph) {
        graphLayout.setGraph(graph);
        calculateScaled();
        repaint();
    }

    public GraphViewer() {
        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                // recalculate and repaint on resize
                calculateScaled();
                repaint();
            }

        });
    }

    public void setGraphLayout(GraphLayout<V, E> layout) {
        if (layout != null && layout.getGraph() == null) {
            // assume we want to keep the current graph
            layout.setGraph(this.graphLayout.getGraph());
        }
        this.graphLayout = layout;
        calculateScaled();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

        // draw the edges
        g.setColor(Color.BLACK);
        for (Edge<V, E> edge : getGraph().getWholeEdges()) {
            Point p1 = scaled.get(edge.getV1());
            Point p2 = scaled.get(edge.getV2());
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

}
