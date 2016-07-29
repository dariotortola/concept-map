package gui.pruebas.viewer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Esto es lo que vamos a poner en cada nodo
 * 
 * @author dtortola
 *
 */
class Nodo extends Rectangle {

    /**
     * 
     */
    private static final long serialVersionUID = -2557449607305960331L;

    public Nodo(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * Esta sección de código estaba en {@link MyPanel#paintComponent(Graphics)}
     * 
     * @param g
     */
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        // como el outline tiene ancho 1, el final está en width+1
        // los bounds, entonces, estarían en (0,0)-(width+1, height+1)
        g.drawRect(0, 0, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(0, 0, width + 1, height + 1);
    }
}