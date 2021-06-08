package main.java.fr.enseeiht.lbs.controller;

import main.java.fr.enseeiht.lbs.model.game_object.EntityFactory;
import main.java.fr.enseeiht.lbs.model.game_object.EntityPrimitiveTypes;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.view.content.UnitTypeListView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UnitListController extends JPanel {

    private final UnitTypeListView unitTypeListView;
    private final JButton newButton;
    private final JTextField newField;
    private final JButton delButton;


    public UnitListController() {
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        // init
        unitTypeListView = new UnitTypeListView();
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
        add(unitTypeListView, listConstraints);
        add(newButton, newBConstraints);
        add(delButton, delConstraints);
        add(newField, newTConstraints);

        // Logic
        newButton.setEnabled(false);
        newButton.addActionListener(actionEvent -> {
            String selected = unitTypeListView.getSelectedUnitType();
            String name = newField.getText();
            if (selected == null && name.length() > 0) return;
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
            String selected = unitTypeListView.getSelectedUnitType();
            if (selected == null || EntityFactory.getInitialUnit().contains(selected)) return;
            try {
                EntityFactory.dropEntityType(selected);
            } catch (EntityFactory.UnmodifiableTypeException e) {
                e.printStackTrace();
            }
        });

        unitTypeListView.addActionListener(actionEvent -> {
            if (actionEvent.getActionCommand() != null && EntityFactory.getInitialUnit().contains(actionEvent.getActionCommand())) {
                newButton.setEnabled(true);
                delButton.setEnabled(false);
            } else if (actionEvent.getActionCommand() != null) {
                newButton.setEnabled(true);
                delButton.setEnabled(true);
            } else {
                newButton.setEnabled(false);
                newButton.setEnabled(false);
            }
        });
    }

    public void addSelectActionListener(ActionListener listener) {
        unitTypeListView.addActionListener(listener);
    }
}
