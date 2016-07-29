package gui.lesson.custompainting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

class MyPanel3 extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -4678175029867611791L;

    private RedRectangle2 bounds = new RedRectangle2(50, 50, 20, 20);

    public MyPanel3() {
        setBorder(BorderFactory.createLineBorder(Color.black));

        // controles de ratón para controlar el cuadrado
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                moveSquare(e.getX(), e.getY());
            }

        });
        addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                moveSquare(e.getX(), e.getY());
            }

        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(250, 200);
    }

    private void moveSquare(int x, int y) {
        int OFFSET = 1;
        if ((bounds.x != x) || (bounds.y != y)) {
            // al repintar sólo este área ahorramos repintar otras áreas
            // esta primera línea repinta donde estaba el elemento, dejando sólo
            // el color de background
            repaint(bounds.x, bounds.y, bounds.width + OFFSET, bounds.height + OFFSET);
            bounds.x = x;
            bounds.y = y;
            // al repintar sólo este área ahorramos repintar otras áreas
            // esta segunda llamada repinta donde está ahora el elemento,
            // llamando a paintComponent
            repaint(bounds.x, bounds.y, bounds.width + OFFSET, bounds.height + OFFSET);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw Text
        g.drawString("This is my custom Panel!", 10, 20);

        // draw rectangle
        bounds.paintSquare(g);
        // para la parte de rubber stamp creamos un nuevo graphics limitado y posicionado en los bounds
        Graphics g2 = g.create(bounds.x, bounds.y, bounds.width+1, bounds.height+1);
        ((Graphics2D)g2).scale(.5, .5);
        bounds.paintSquare(g2);
    }
}

class RedRectangle2 extends Rectangle {

    /**
     * 
     */
    private static final long serialVersionUID = -2557449607305960331L;

    public RedRectangle2(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * Esta sección de código estaba en {@link MyPanel#paintComponent(Graphics)}
     * 
     * @param g
     */
    public void paintSquare(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        // como el outline tiene ancho 1, el final está en width+1
        // los bounds, entonces, estarían en (0,0)-(width+1, height+1)
        g.drawRect(0, 0, width, height);
    }
}

/**
 * Intento evolucionar {@link SwingPaintDemo3} para que funcione como rubber
 * stamp, que sería previo a utilizar un renderer del estilo de
 * {@link ListCellRenderer}, etc
 * 
 * @author dtortola
 *
 */
public class SwingPaintDemo3 {

    private static void createGui() {
        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.getContentPane().add(new MyPanel3());

        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingPaintDemo3::createGui);
    }
}