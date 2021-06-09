package main.java.fr.enseeiht.lbs.controller;

import main.java.fr.enseeiht.lbs.model.game_object.EntityFactory;
import main.java.fr.enseeiht.lbs.model.game_object.EntityPrimitiveTypes;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class UnitListController extends JPanel {

    private final UnitTypeListController unitTypeListController;
    private final JButton newButton;
    private final JTextField newField;
    private final JButton delButton;


    public UnitListController() {
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        // init
        unitTypeListController = new UnitTypeListController();
        newButton = new JButton("+");
        newField = new JTextField();
        delButton = new JButton("x");

        //Layout
        GridBagConstraints listConstraints = new GridBagConstraints();
        listConstraints.gridx = 0;
        listConstraints.gridy = 0;
        listConstraints.fill = GridBagConstraints.BOTH;
        listConstraints.gridwidth = 3;
        listConstraints.gridheight = 1;
        listConstraints.weighty = 1;
        listConstraints.weightx = 1;
        GridBagConstraints newBConstraints = new GridBagConstraints();
        newBConstraints.gridx = 1;
        newBConstraints.gridy = 1;
        newBConstraints.fill = GridBagConstraints.NONE;
        newBConstraints.gridwidth = 1;
        newBConstraints.gridheight = 1;
        GridBagConstraints delConstraints = new GridBagConstraints();
        delConstraints.gridx = 2;
        delConstraints.gridy = 1;
        delConstraints.fill = GridBagConstraints.NONE;
        delConstraints.gridwidth = 1;
        delConstraints.gridheight = 1;
        GridBagConstraints newTConstraints = new GridBagConstraints();
        newTConstraints.gridx = 0;
        newTConstraints.gridy = 1;
        newTConstraints.weightx = 1;
        newTConstraints.fill = GridBagConstraints.HORIZONTAL;
        newTConstraints.gridwidth = 1;
        newTConstraints.gridheight = 1;
        add(unitTypeListController, listConstraints);
        add(newButton, newBConstraints);
        add(delButton, delConstraints);
        add(newField, newTConstraints);

        // Logic
        newButton.setEnabled(false);
        newButton.addActionListener(actionEvent -> {
            String selected = unitTypeListController.getSelectedUnitType();
            String name = newField.getText();
            if (selected == null || name.length() == 0) return;
            EntityPrimitiveTypes entityPrimitiveType = EntityFactory.getEntityPrimitiveType(selected);
            Stats entityTypeStats = EntityFactory.getEntityTypeStats(selected);
            if (entityTypeStats == null || entityPrimitiveType == null) return;
            try {
                EntityFactory.setEntityType(name, entityPrimitiveType, entityTypeStats);
            } catch (EntityFactory.UnmodifiableTypeException e) {
                e.printStackTrace();
            }
        });
        delButton.setEnabled(false);
        delButton.addActionListener(actionEvent -> {
            String selected = unitTypeListController.getSelectedUnitType();
            if (selected == null || EntityFactory.getInitialUnit().contains(selected)) return;
            try {
                EntityFactory.dropEntityType(selected);
            } catch (EntityFactory.UnmodifiableTypeException e) {
                e.printStackTrace();
            }
        });

        unitTypeListController.addActionListener(actionEvent -> {
            if (actionEvent.getActionCommand() != null && EntityFactory.getInitialUnit().contains(actionEvent.getActionCommand())) {
                newButton.setEnabled(newField.getText().length() > 0);
                delButton.setEnabled(false);
            } else if (actionEvent.getActionCommand() != null) {
                newButton.setEnabled(newField.getText().length() > 0);
                delButton.setEnabled(true);
            } else {
                newButton.setEnabled(false);
                newButton.setEnabled(false);
            }
        });

        newField.getDocument().addDocumentListener(new NameChangeListener());
    }

    public void addSelectActionListener(ActionListener listener) {
        unitTypeListController.addActionListener(listener);
    }

    private class NameChangeListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent documentEvent) {
            newButton.setEnabled(newField.getText().length() > 0 && unitTypeListController.getSelectedUnitType() != null);
        }

        @Override
        public void removeUpdate(DocumentEvent documentEvent) {
            newButton.setEnabled(newField.getText().length() > 0 && unitTypeListController.getSelectedUnitType() != null);
        }

        @Override
        public void changedUpdate(DocumentEvent documentEvent) {
            newButton.setEnabled(newField.getText().length() > 0 && unitTypeListController.getSelectedUnitType() != null);
        }
    }
}
