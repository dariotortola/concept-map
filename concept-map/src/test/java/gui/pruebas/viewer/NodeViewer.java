package gui.pruebas.viewer;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

class NodeViewer extends JPanel {

    /**
     * este elemento correspondería al graph layout: lo que dice dónde va cada
     * punto sin importar las dimensiones reales de donde se va a mostrar
     */
    private final Model graphLayout;

    /**
     * este elemento corresponde a la vista en el espacio que tenemos disponible
     */
    private final List<Point> vistaVentana;

    private final Nodo nodo;

    /**
     * 
     */
    private static final long serialVersionUID = -4678175029867611791L;

    public NodeViewer() {
        this.graphLayout = new Model(10);
        setPreferredSize(graphLayout.getDimension());
        nodo = new Nodo(0, 0, 3, 3);
        vistaVentana = new ArrayList<>(graphLayout);
        
        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                recalculateView();
                repaint();
            }
            
        });
    }

    /**
     * 
     */
    private void recalculateView() {
        System.out.println("Recalculate " + getSize().toString());
        
        // recalcula las coordenadas en vista
        double fx = getWidth()/(double)graphLayout.getDimension().getWidth();
        double fy = getHeight()/(double)graphLayout.getDimension().getHeight();
        
        vistaVentana.clear();
        for (Point p : graphLayout){
            vistaVentana.add(new Point((int)(p.x*fx), (int)(p.y*fy)));
            // aquí podríamos estar recalculando también con los bounds de cada vértice, los de las label, etc
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Rectangle nodoBounds = nodo.getBounds();
        for (int i = 0; i < vistaVentana.size(); i++) {
            Point p = vistaVentana.get(i);
            // ponemos cada punto
            Graphics g2 = g.create(p.x, p.y, nodoBounds.width, nodoBounds.height);
            nodo.paint(g2);

            // trazamos líneas a los demás
            for (int j = 0; j < i; j++) {
                Point p2 = vistaVentana.get(j);
                g.drawLine(p.x, p.y, p2.x, p2.y);
            }
        }
    }
}