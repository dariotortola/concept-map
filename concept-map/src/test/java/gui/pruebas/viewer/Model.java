package gui.pruebas.viewer;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

/**
 * Esta es una simplificación exagerada del papel de graph layout: indicar una
 * posición donde va cada vértice
 * 
 * @author dtortola
 *
 */
public class Model extends ArrayList<Point> {

    /**
     * 
     */
    private static final long serialVersionUID = 8209474587168436323L;

    private final Dimension dimension;

    public Model(int n) {
        // inicializamos con n elementos
        Random random = new Random();
        int limite = 100;
        Rectangle rect = new Rectangle(limite, limite, 0, 0);
        for (int i = 0; i < n; i++) {
            Point point = new Point(random.nextInt(limite), random.nextInt(limite));
            add(point);
            rect.x = Math.min(rect.x, point.x);
            rect.y = Math.min(rect.y, point.y);
            rect.width = Math.max(rect.width, point.x);
            rect.height = Math.max(rect.height, point.y);
        }
        dimension = rect.getSize();
    }

    public Dimension getDimension() {
        return dimension;
    }
}
