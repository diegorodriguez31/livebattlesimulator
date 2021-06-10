package main.java.fr.enseeiht.lbs.controller;

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

/**
 * Contrôleur qui gère les boutons de sélection de troupe à editer, copier ou supprimer
 * dans le mode creator.
 */
public class UnitTypeListController extends JPanel implements PropertyChangeListener {
    private String selectedUnitType;
    private final HashMap<String, JButton> buttons = new HashMap<>();
    private final List<ActionListener> listeners = new ArrayList<>();

    public UnitTypeListController() {
        super();
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);
        updateButtons();
        EntityFactory.addPropertyChangeListener(EntityFactory.EVENT_LIST_CHANGE, this);
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
            button.addActionListener(actionEvent -> setSelectedUnitType(actionEvent.getActionCommand()));
        }
        if (selectedUnitType != null && !buttons.containsKey(selectedUnitType)) {
            setSelectedUnitType(null);
        }
        updateUI();
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        updateButtons();
    }

    public void setSelectedUnitType(String selectedUnitType) {
        if (this.selectedUnitType != null && buttons.get(this.selectedUnitType) != null) {
            buttons.get(this.selectedUnitType).setEnabled(true);
        }
        this.selectedUnitType = selectedUnitType;
        if (this.selectedUnitType != null && buttons.get(this.selectedUnitType) != null) {
            buttons.get(this.selectedUnitType).setEnabled(false);
        }
        actionNotifyAll();
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
