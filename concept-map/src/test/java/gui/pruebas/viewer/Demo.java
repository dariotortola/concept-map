package gui.pruebas.viewer;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gui.lesson.custompainting.SwingPaintDemo3;

/**
 * Evolución de {@link SwingPaintDemo3} para utilizar un grupo de coordenadas,
 * pensando en el futuro de los grafos
 * 
 * @author dtortola
 *
 */
public class Demo {

    private static void createGui() {
        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.getContentPane().add(new NodeViewer());

        f.pack();
        f.setVisible(true);
    }

    private static void createZoomGui() {
        NodeViewer viewer = new NodeViewer();
//        JScrollPane scroller = new JScrollPane(viewer);
//        
//
//        MouseWheelListener mwl = e->{
//            System.out.println(viewer.getSize());
//            System.out.println("Scroller " + scroller.getViewport().getSize().toString());
//            
//            double factor = 1.0 + e.getWheelRotation() / 20.0;
//            Dimension target = viewer.getSize();
//            target.height = (int) (factor * target.height);
//            target.width = (int) (factor * target.width);
//            
//            System.out.println(viewer.getSize());
//        };
//        
//        scroller.addMouseWheelListener(mwl);
//        viewer.addMouseWheelListener(mwl);

        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(viewer);
        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) {
//        SwingUtilities.invokeLater(Demo::createGui);
        SwingUtilities.invokeLater(Demo::createZoomGui);
    }
}