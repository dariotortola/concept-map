package map.gui;

import java.awt.BorderLayout;
import java.beans.PropertyChangeListener;

import map.model.Information;
import map.model.MapItem;

public class MapItemEditor extends KnowableEditor<MapItem> {

    /**
     * 
     */
    private static final long serialVersionUID = 234769617715650115L;

    private final InformationEditor infoEditor;
    private final KnowableListEditor<Information<?>> listEditor;

    @Override
    public void setModel(MapItem model) {
        super.setModel(model);
        listEditor.getList().setItems(model.getInformation());
    }

    public MapItemEditor() {
        super();

        // editors
        infoEditor = new InformationEditor();
        infoEditor.setEnabled(false);
        listEditor = new KnowableListEditor<>(this::createNew);

        // change model of infoEditor on selection
        listEditor.getList().addListSelectionListener((e) -> {
            if (!e.getValueIsAdjusting()) {
                if (listEditor.getList().isSelectionEmpty()) {
                    infoEditor.setEnabled(false);
                } else {
                    infoEditor.setEnabled(true);
                    infoEditor.setModel(listEditor.getList().getSelectedValue());
                }
            }
        });
        PropertyChangeListener listener = (e) -> {
            listEditor.getList().selectedItemChanged();
        };
        infoEditor.addPropertyChangeListener(PROPERTY_TITLE, listener);
        infoEditor.addPropertyChangeListener(PROPERTY_KNOWN, listener);

        add(infoEditor, BorderLayout.CENTER);
        add(listEditor, BorderLayout.WEST);
    }

    private Information<?> createNew() {
        Information<String> info = Information.newText("new information fragment", null);
        if (!isGmEnabled()) {
            // only known things
            info.setKnown(true);
        }
        return info;
    }

    @Override
    public void setGmEnabled(boolean gmEnabled) {
        super.setGmEnabled(gmEnabled);
        listEditor.getList().setGmEnabled(gmEnabled);
        infoEditor.setGmEnabled(gmEnabled);
    }
}
