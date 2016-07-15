package demos;

import javax.swing.JFrame;

import map.gui.MapItemEditor;

public class GuiDemo {

    public static void main(String[] args) {
        MapItemEditor editor = new MapItemEditor();
        editor.setGmEnabled(true);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(editor);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
