package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.model.game_object.EntityFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UnitTypeList extends JPanel implements PropertyChangeListener {
    private String selectedUnitType;
    private final HashMap<String, JButton> buttons = new HashMap<>();
    private final List<ActionListener> listeners = new ArrayList<>();

    public UnitTypeList() {
        super();
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);
        updateButtons();
    }

    private void updateButtons() {
        buttons.clear();
        removeAll();
        for (String entityType : EntityFactory.getEntityTypes()) {
            //Button
            JButton button = new JButton(entityType);
            buttons.put(entityType, button);
            button.setMaximumSize(new Dimension(4000, 30));
            add(button);

            //Logic
            button.addActionListener(actionEvent -> {
                if (selectedUnitType != null) {
                    buttons.get(selectedUnitType).setSelected(false);
                    buttons.get(selectedUnitType).setEnabled(true);
                }
                selectedUnitType = actionEvent.getActionCommand();
                buttons.get(selectedUnitType).setSelected(true);
                buttons.get(selectedUnitType).setEnabled(false);

                actionNotifyAll();
            });
        }
        if (selectedUnitType != null && !buttons.containsKey(selectedUnitType)) {
            selectedUnitType = null;
            actionNotifyAll();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        updateButtons();
    }

    public String getSelectedUnitType() {
        return selectedUnitType;
    }

    public void addActionListener(ActionListener listener) {
        listeners.add(listener);
    }

    public void removeActionListener(ActionListener listener) {
        listeners.remove(listener);
    }

    private void actionNotifyAll() {
        for (ActionListener listener : listeners) {
            listener.actionPerformed(new ActionEvent(this, 0, selectedUnitType));
        }
    }
}
