package map.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.function.Supplier;

import javax.swing.JButton;
import javax.swing.JScrollPane;

import com.gui.GuiUtils;

import map.model.Knowable;

/**
 * A {@link KnowableList} with buttons to delete selected and add new
 * 
 * @author dtortola
 *
 */
public class KnowableListEditor<T extends Knowable> extends Container {

    /**
     * 
     */
    private static final long serialVersionUID = 777300923603876474L;

    private final KnowableList<T> list;

    /**
     * @param supplier
     *            can return null to avoid adding an element (for instance,
     *            because it requires a dialog and the user cancels it); can be
     *            null and then there will be not add button
     */
    public KnowableListEditor(Supplier<T> supplier) {
        // layout
        setLayout(new BorderLayout());
        list = new KnowableList<>();
        add(new JScrollPane(list), BorderLayout.CENTER);

        Container buttons = new Container();
        add(buttons, BorderLayout.SOUTH);
        buttons.setLayout(new GridLayout(1, 2));

        // buttons
        if (supplier != null) {
            JButton add = GuiUtils.buildButton("Nuevo", () -> {
                T t = supplier.get();
                if (t != null) {
                    list.addItem(t);
                }
            });
            buttons.add(add);
        }
        buttons.add(GuiUtils.buildButton("Eliminar", () -> list.deleteSelectedItems()));
    }

    public KnowableList<T> getList() {
        return list;
    }
}
