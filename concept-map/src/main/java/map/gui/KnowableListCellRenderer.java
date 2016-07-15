package map.gui;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.apache.commons.lang3.StringUtils;

import map.model.Knowable;

/**
 * {@link ListCellRenderer} using the title of the {@link Knowable}. The known
 * items are in plain style, the unknown are surrounded by square brackets and
 * in italic style
 * 
 * @author dtortola
 *
 */
public class KnowableListCellRenderer implements ListCellRenderer<Knowable> {
    private final DefaultListCellRenderer delegate = new DefaultListCellRenderer();

    @Override
    public Component getListCellRendererComponent(JList<? extends Knowable> list, Knowable value, int index,
            boolean isSelected, boolean cellHasFocus) {
        delegate.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value != null) {
            int style;
            if (value.isKnown()) {
                delegate.setText(StringUtils.defaultString(value.getTitle()));
                style = Font.BOLD;
            } else {
                delegate.setText(StringUtils.join("[", value.getTitle(), "]"));
                style = Font.ITALIC;
            }
            Font font = delegate.getFont();
            font = font.deriveFont(style);
            delegate.setFont(font);
        }
        return delegate;
    }
}
