package com.graph.layout;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import com.graph.model.DefaultGraph;
import com.graph.model.Graph;

public class GraphLayoutTest {

    static class MappedLayout<V, E> extends GraphLayout<V, E> {

        private final Map<V, Point> map = new HashMap<>();

        @Override
        public Point getCoordinates(V vertex) {
            Point p = map.get(vertex);
            if (p == null) {
                p = new Point(0, 0);
                map.put(vertex, p);
            }
            return p;
        }

        @Override
        protected void recalculate() {
        }

    }

    @Test
    public void bounded() {
        Random random = new Random();
        MappedLayout<Integer, String> layout = new MappedLayout<>();
        Graph<Integer, String> graph = new DefaultGraph<>();
        layout.setGraph(graph);

        int limite = 100;
        int nodos = 100;
        for (int i = 0; i < nodos; i++) {
            Integer nodo = new Integer(i);
            graph.addVertex(nodo);
            layout.map.put(nodo, new Point(random.nextInt(limite), random.nextInt(limite)));
        }

        Rectangle r = layout.getBounds();
        for (Integer v : graph.getVertices()) {
            Point point = layout.getCoordinates(v);
            if (!r.contains(point)){
                Assert.fail();
            }
            Assert.assertTrue(r.contains(point));
        }
    }
}
