package main.java.fr.enseeiht.lbs.controller;

import main.java.fr.enseeiht.lbs.model.game_object.EntityFactory;
import main.java.fr.enseeiht.lbs.view.content.UnitTypeList;

import javax.swing.*;
import java.awt.*;

public class UnitListController extends JPanel {

    private final UnitTypeList unitTypeList;
    private final JButton newButton;
    private final JButton delButton;


    public UnitListController() {
        setLayout(new GridBagLayout());

        // init
        unitTypeList = new UnitTypeList();
        newButton = new JButton("+");
        delButton = new JButton("x");

        //Layout
        GridBagConstraints listConstraints = new GridBagConstraints();
        listConstraints.gridx = 0;
        listConstraints.gridy = 0;
        listConstraints.fill = GridBagConstraints.VERTICAL;
        listConstraints.gridwidth = 2;
        listConstraints.gridheight = 1;
        GridBagConstraints addConstraints = new GridBagConstraints();
        addConstraints.gridx = 0;
        addConstraints.gridy = 1;
        addConstraints.fill = GridBagConstraints.BASELINE;
        addConstraints.gridwidth = 1;
        addConstraints.gridheight = 1;
        GridBagConstraints delConstraints = new GridBagConstraints();
        delConstraints.gridx = 1;
        delConstraints.gridy = 1;
        delConstraints.fill = GridBagConstraints.BASELINE;
        delConstraints.gridwidth = 1;
        delConstraints.gridheight = 1;
        add(unitTypeList, listConstraints);
        add(newButton, addConstraints);
        add(delButton, delConstraints);

        // Logic
        newButton.setEnabled(false);
        delButton.setEnabled(false);
        delButton.addActionListener(actionEvent -> {
            String selected = unitTypeList.getSelectedUnitType();
            if (selected == null || EntityFactory.getInitialUnit().contains(selected)) return;
            try {
                EntityFactory.dropEntityType(selected);
            } catch (EntityFactory.UnmodifiableTypeException e) {
                e.printStackTrace();
            }
        });
    }
}
