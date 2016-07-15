package map.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JTextArea;

import org.apache.commons.lang3.StringUtils;

import com.gui.GuiUtils;

import map.model.Information;

/**
 * Editor of a single {@link Information} item
 * 
 * @author dtortola
 *
 */
public class InformationEditor extends KnowableEditor<Information<?>> {

    /**
     * 
     */
    private static final long serialVersionUID = -1095208352548959880L;
    private final CardLayout cards = new CardLayout();
    private final Container content = new Container();
    private final JTextArea stringEditor = new JTextArea();

    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);
        stringEditor.setEnabled(b);
    }

    /**
     * asigna eventos para sincronizar los cambios en los controles con el
     * modelo
     */
    private void wire() {

        stringEditor.addFocusListener(GuiUtils.onFocusLost(() -> {
            if (getModel() != null && String.class.isAssignableFrom(getModel().getContentClass())) {
                @SuppressWarnings("unchecked")
                Information<String> casted = (Information<String>) getModel();
                casted.setContent(stringEditor.getText());
            }
        }));
    }

    /**
     * @param gm
     *            true by use for the gm, false by use by the players
     */
    public InformationEditor() {
        super();

        // eventos
        wire();

        // layout
        add(content, BorderLayout.CENTER);
        content.setLayout(cards);
        content.add(stringEditor, String.class.getName());
    }

    /**
     * @param model
     *            no null
     */
    @Override
    public void setModel(Information<?> model) {
        super.setModel(model);
        if (String.class.isAssignableFrom(model.getContentClass())) {
            cards.show(content, String.class.getName());
            stringEditor.setText(StringUtils.defaultString((String) model.getContent()));
        }
    }

}
