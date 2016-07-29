package graph.gui;

import java.awt.Point;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.graph.gui.GraphViewer;
import com.graph.layout.StaticGraphLayout;
import com.graph.model.DefaultGraph;
import com.graph.model.EdgeType;
import com.graph.model.Graph;

public class GraphViewDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GraphViewDemo::createAndShowGui);
    }

    /**
     * @param n
     * @return grafo con esos vértices
     */
    private static Graph<Integer, String> createGraph(int n) {
        Graph<Integer, String> graph = new DefaultGraph<>();

        Integer last = null;
        for (int i = 0; i < n; i++) {
            Integer v = new Integer(i);
            graph.addVertex(v);
            if (last != null) {
                graph.addEdge(last, v, EdgeType.UNDIRECTED, "Edge");
            }
            last = v;
        }
        return graph;
    }

    private static void createAndShowGui() {
        Graph<Integer, String> graph = createGraph(10);
        StaticGraphLayout<Integer, String> layout = new StaticGraphLayout<>();
        layout.setGraph(graph);
        Random random = new Random();
        for (Integer i : graph.getVertices()) {
            layout.setCoordinates(i, new Point(random.nextInt(100), random.nextInt(100)));
        }
        GraphViewer<Integer, String> viewer = new GraphViewer<>();
        viewer.setGraphLayout(layout);

        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(viewer);
        f.pack();
        f.setVisible(true);
    }
}
