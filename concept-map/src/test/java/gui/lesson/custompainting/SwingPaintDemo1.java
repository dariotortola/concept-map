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

/**
 * Este panel pinta una cadena de texto y un rectángulo, que puede moverse con
 * el ratón
 * 
 * @author dtortola
 *
 */
class MyPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -4678175029867611791L;

    private Rectangle bounds = new Rectangle(50, 50, 20, 20);

    public MyPanel() {
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
        g.setColor(Color.RED);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        g.setColor(Color.BLACK);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}

public class SwingPaintDemo1 {

    private static void createGui() {
        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.getContentPane().add(new MyPanel());

        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingPaintDemo1::createGui);
    }
}