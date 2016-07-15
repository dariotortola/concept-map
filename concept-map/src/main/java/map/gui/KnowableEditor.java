package map.gui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;

import com.gui.GuiUtils;

import map.model.Knowable;

/**
 * Superclass for editors of {@link Knowable} things. The title and check are in
 * the north and south sections respectively, leaving the rest to the subclasses
 * 
 * @author dtortola
 *
 * @param <T>
 */
class KnowableEditor<T extends Knowable> extends Container {

    /**
     * property related to the value of the known field of the model
     */
    public static final String PROPERTY_KNOWN = "known";

    /**
     * property related to the content of the title field of the model
     */
    public static final String PROPERTY_TITLE = "title";
    /**
     * 
     */
    private static final long serialVersionUID = -1095208352548959880L;

    private boolean gmEnabled = false;
    private final JCheckBox known = new JCheckBox("Los jugadores conocen esta información");
    private T model;
    private final JTextField title = new JTextField();

    /**
     * @param gm
     *            true by use for the gm, false by use by the players
     */
    public KnowableEditor() {
        // eventos
        wire();

        // estado inicial
        gmEnabled = false;
        known.setVisible(gmEnabled);
        
        // layout
        setLayout(new BorderLayout());
        add(title, BorderLayout.NORTH);
        add(known, BorderLayout.SOUTH);
    }

    public T getModel() {
        return model;
    }

    public boolean isGmEnabled() {
        return gmEnabled;
    }

    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);
        known.setEnabled(b);
        title.setEnabled(b);
    }

    public void setGmEnabled(boolean gmEnabled) {
        this.gmEnabled = gmEnabled;
        known.setVisible(gmEnabled);
    }

    /**
     * @param model
     *            no null
     */
    public void setModel(T model) {
        this.model = model;

        if (known != null) {
            known.setSelected(model.isKnown());
        }
        title.setText(StringUtils.defaultString(model.getTitle()));
    }

    /**
     * asigna eventos para sincronizar los cambios en los controles con el
     * modelo
     */
    private void wire() {
        if (known != null) {
            known.addChangeListener((e) -> {
                if (model == null) {
                    return;
                }
                boolean oldValue = model.isKnown();
                model.setKnown(known.isSelected());
                firePropertyChange(PROPERTY_KNOWN, oldValue, model.isKnown());
            });
        }

        title.addFocusListener(GuiUtils.onFocusLost(() -> {
            if (model == null) {
                return;
            }
            String oldValue = model.getTitle();
            model.setTitle(title.getText());
            firePropertyChange(PROPERTY_TITLE, oldValue, model.getTitle());
        }));
    }
}
