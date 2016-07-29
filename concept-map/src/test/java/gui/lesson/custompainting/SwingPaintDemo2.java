package gui.lesson.custompainting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class MyPanel2 extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -4678175029867611791L;

    private RedRectangle bounds = new RedRectangle(50, 50, 20, 20);

    public MyPanel2() {
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
    }
}

class RedRectangle extends Rectangle {

    /**
     * 
     */
    private static final long serialVersionUID = -2557449607305960331L;

    public RedRectangle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * Esta sección de código estaba en {@link MyPanel#paintComponent(Graphics)}
     * 
     * @param g
     */
    public void paintSquare(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }
}

/**
 * Es una evolución de {@link SwingPaintDemo1} en la que el dibujo del
 * rectángulo se autocontiene en su propia clase. En 2d game programming se
 * suele llamar "sprite animation" a eso
 * 
 * @author dtortola
 *
 */
public class SwingPaintDemo2 {

    private static void createGui() {
        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.getContentPane().add(new MyPanel2());

        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingPaintDemo2::createGui);
    }
}