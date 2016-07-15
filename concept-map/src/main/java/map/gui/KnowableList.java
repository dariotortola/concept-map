package map.gui;

import java.util.Collection;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

import map.model.Knowable;

/**
 * {@link JList} initialized with a {@link KnowableListModel}
 * 
 * @author dtortola
 *
 */
public class KnowableList<T extends Knowable> extends JList<T> {

    /**
     * 
     */
    private static final long serialVersionUID = 3292280524811875076L;

    public KnowableList() {
        super(new KnowableListModel<T>());
        setCellRenderer(new KnowableListCellRenderer());
    }

    /**
     * Works only if model is instanceof {@link KnowableListModel}; adds the
     * item to the model
     * 
     * @param item
     */
    public void addItem(T item) {
        KnowableListModel<T> model = (KnowableListModel<T>) getModel();
        model.addItem(item);
    }

    /**
     * Works only if model is instanceof {@link KnowableListModel}; deletes
     * selected elements after a confirmation message
     */
    public void deleteSelectedItems() {
        if (!isSelectionEmpty() && JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this,
                "Se borrarán los elementos seleccionados. ¿Seguro?")) {
            KnowableListModel<T> model = (KnowableListModel<T>) getModel();
            model.removeItems(getSelectedIndices());
        }
    }

    /**
     * works only with {@link KnowableListModel}
     * 
     * @return true if all items are shown, false if only known items are shown
     */
    public boolean isGmEnabled() {
        KnowableListModel<T> model = (KnowableListModel<T>) getModel();
        return model.isGmEnabled();
    }

    /**
     * Works only if model is instanceof {@link KnowableListModel}; to be called
     * when the title of the item may have changed
     */
    public void selectedItemChanged() {
        if (!isSelectionEmpty()) {
            // notify the model, maybe the elements get reordered
            KnowableListModel<T> model = (KnowableListModel<T>) getModel();
            int selectedIndex = model.onItemChanged(getSelectedIndex());
            // ensure the same element is selected
            setSelectedIndex(selectedIndex);
        }
    }

    /**
     * works only with {@link KnowableListModel}
     * 
     * @param gmEnabled
     */
    public void setGmEnabled(boolean gmEnabled) {
        KnowableListModel<T> model = (KnowableListModel<T>) getModel();
        model.setGmEnabled(gmEnabled);
    }

    /**
     * Sets the internal, ordered list of items; works only with
     * {@link KnowableListModel}
     * 
     * @param items
     *            no null
     */
    public void setItems(Collection<T> items) {
        KnowableListModel<T> model = (KnowableListModel<T>) getModel();
        model.setItems(items);
    }

    @Override
    public void setModel(ListModel<T> model) {
        super.setModel(model);
    }
}
