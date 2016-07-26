package com.graph.gui;

import java.awt.Component;
import java.awt.Graphics;

import com.graph.layout.GraphLayout;
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
public class GraphViewer<V, E> extends Component {

    /**
     * 
     */
    private static final long serialVersionUID = 5021911491325587584L;

    private GraphLayout<V, E> layout;

    public GraphLayout<V, E> getLayout() {
        return layout;
    }

    public Graph<V, E> getGraph() {
        return layout.getGraph();
    }

    public void setGraph(Graph<V, E> graph) {
        layout.setGraph(graph);
    }

    public void setLayout(GraphLayout<V, E> layout) {
        if (layout != null && layout.getGraph() == null) {
            // assume we want to keep the current graph
            layout.setGraph(this.layout.getGraph());
        }
        this.layout = layout;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        
        
    }
}
