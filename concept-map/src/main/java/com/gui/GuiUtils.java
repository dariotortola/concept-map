package com.gui;

import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;

public class GuiUtils {

    private GuiUtils() {
        // utility class
    }

    /**
     * To use less LOC when we want a button that doesn't care about the
     * {@link ActionEvent}
     * 
     * @param label
     *            for the button
     * @param action
     *            what the button should do when clicked
     * @return button that calls action when clicked
     */
    public static JButton buildButton(String label, Runnable action) {
        return new JButton(new AbstractAction(label) {

            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                action.run();
            }
        });
    }

    /**
     * To use less LOCs when we want a {@link FocusListener} that doesn't care
     * about gained focus or the {@link FocusEvent}
     * 
     * @param action
     * @return FocusListener that calls action when the focus is lost
     */
    public static FocusListener onFocusLost(Runnable action) {
        return new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                action.run();
            }

        };
    }
}
