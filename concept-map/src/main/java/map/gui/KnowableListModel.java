package map.gui;

import static map.model.Knowable.ALPHABETIC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractListModel;

import map.model.Knowable;

/**
 * {@link AbstractListModel} ordered alphabetically
 * 
 * @author dtortola
 *
 */
class KnowableListModel<T extends Knowable> extends AbstractListModel<T> {

    /**
     * 
     */
    private static final long serialVersionUID = -3727792335530946680L;

    private List<T> allItems = new ArrayList<>();
    private boolean gmEnabled = false;
    /**
     * sortered copy of the information list
     */
    private List<T> itemsKnown = new ArrayList<>();

    /**
     * Adds item in its alphabetical position in list
     * 
     * @param list
     * @param item
     * @return index the item was added in
     */
    private static <T extends Knowable> int add(List<T> list, T item) {
        int index = Collections.binarySearch(list, item, ALPHABETIC);
        if (index < 0) {
            index = -1 - index;
        }
        list.add(index, item);
        return index;
    }

    /**
     * @param list
     *            a list
     * @param index
     *            an index in list
     * @return index the item at index should really be; if it's different from
     *         index, the positions at both indexes will had been swapped
     */
    private static <T extends Knowable> int checkPosition(List<T> list, int index) {
        int newIndex = index;
        T moving = list.get(index);
        // check if it should be before
        while (newIndex > 0 && ALPHABETIC.compare(list.get(newIndex - 1), moving) > 0) {
            newIndex--;
        }
        // check if it should be after
        while (newIndex < list.size() - 1 && ALPHABETIC.compare(list.get(newIndex + 1), moving) < 0) {
            newIndex++;
        }
        if (newIndex != index) {
            list.set(index, list.get(newIndex));
            list.set(newIndex, moving);
        }
        return newIndex;
    }

    /**
     * @param item
     * 
     */
    public void addItem(T item) {
        // it always get add to allItems
        int gmIndex = add(allItems, item);
        int pcIndex = -1;
        if (item.isKnown()) {
            pcIndex = add(itemsKnown, item);
        }
        // event
        if (gmEnabled) {
            fireIntervalAdded(this, gmIndex, gmIndex);
        } else if (item.isKnown()) {
            fireIntervalAdded(this, pcIndex, pcIndex);
        }
    }

    @Override
    public T getElementAt(int index) {
        if (gmEnabled) {
            return allItems.get(index);
        } else {
            return itemsKnown.get(index);
        }
    }

    @Override
    public int getSize() {
        if (gmEnabled) {
            return allItems.size();
        } else {
            return itemsKnown.size();
        }
    }

    public boolean isGmEnabled() {
        return gmEnabled;
    }

    /**
     * @param index
     *            index of the item that may have changed its title or known
     *            field, so may need to be reordered or hidden
     * @return index the item is after the reordering; it may be -1 if the item
     *         became unknown and this model is not gmEnabled
     */
    int onItemChanged(int index) {
        // changed element
        T t = getElementAt(index);

        if (gmEnabled) {
            // the item may have changed order
            int newIndex = checkPosition(allItems, index);
            fireContentsChanged(this, index, newIndex);

            if (t.isKnown()) {
                // assure it's in itemsKnown
                if (!itemsKnown.contains(t)) {
                    // it should be contained
                    add(itemsKnown, t);
                }
            } else {
                // assure it's not in itemsKnown
                itemsKnown.remove(t);
            }
            return newIndex;
        } else {
            // correct the position in index
            checkPosition(allItems, allItems.indexOf(t));

            // t is contained in itemsKnown or we wouldn't have been called
            if (t.isKnown()) {
                // it's still to be shown, but maybe in another position
                int newIndex = checkPosition(itemsKnown, index);
                fireContentsChanged(this, index, newIndex);
                return newIndex;
            } else {
                // it's not to be shown
                itemsKnown.remove(index);
                fireIntervalRemoved(this, index, index);
                return -1;
            }
        }
    }

    /**
     * @param indexes
     *            indexes of the items to be removed
     */
    void removeItems(int[] indexes) {
        Arrays.sort(indexes);
        List<T> shown;
        List<T> other;
        if (gmEnabled) {
            shown = allItems;
            other = itemsKnown;
        } else {
            shown = itemsKnown;
            other = allItems;
        }
        for (int i = indexes.length - 1; i >= 0; i--) {
            T item = shown.remove(indexes[i]);
            other.remove(item);
        }
        fireIntervalRemoved(this, indexes[0], indexes[indexes.length - 1]);
    }

    public void setGmEnabled(boolean gmEnabled) {
        this.gmEnabled = gmEnabled;
        fireContentsChanged(this, 0, getSize());
    }

    /**
     * Sets the internal, ordered list of items
     * 
     * @param items
     *            no null
     */
    void setItems(Collection<T> items) {
        allItems = new ArrayList<>(items);
        Collections.sort(allItems, ALPHABETIC);
        itemsKnown = new ArrayList<>(allItems.size());
        for (T t : allItems) {
            if (t.isKnown()) {
                itemsKnown.add(t);
            }
        }
    }
}
