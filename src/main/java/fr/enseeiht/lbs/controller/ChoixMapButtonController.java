package main.java.fr.enseeiht.lbs.controller;

import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.model.world.WorldElement;
import main.java.fr.enseeiht.lbs.view.content.WorldView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ChoixMapButtonController extends JPanel {


    public String dryString = "terrain sec (désert et roches)";
    public String wetString = "terrain humide (forêt et eau)";
    public String mainString = "terrain classique";
    static int act = 0;
    static boolean rock = false;


    public ChoixMapButtonController(World world) {

        this.setLayout(new GridLayout(4, 1));
        JRadioButton mainButton = new JRadioButton(mainString);
        mainButton.setActionCommand(mainString);
        mainButton.setSelected(true);
        this.add(mainButton);
        mainButton.addActionListener(actionEvent -> {
            updateElements(this.mainString);
        });
        mainButton.addPropertyChangeListener(mainString, new WorldView());


        JRadioButton wetButton = new JRadioButton(wetString);
        wetButton.setActionCommand(wetString);
        this.add(wetButton);
        wetButton.addActionListener(actionEvent -> {
            updateElements(this.wetString);
        });
        wetButton.addPropertyChangeListener(wetString, new WorldView());
        JRadioButton dryButton = new JRadioButton(dryString);

        dryButton.setActionCommand(dryString);
        this.add(dryButton);
        dryButton.addActionListener(actionEvent -> {
            updateElements(this.dryString);
        });
        dryButton.addPropertyChangeListener(dryString, new WorldView());

        ButtonGroup group = new ButtonGroup();
        group.add(mainButton);
        group.add(dryButton);
        group.add(wetButton);

        JCheckBox rockButton;
        rockButton = new JCheckBox("plus grand nombre de roches");
        rockButton.setMnemonic(KeyEvent.VK_C);
        rockButton.setSelected(false);
        this.add(rockButton);
        rockButton.addActionListener(actionEvent -> {
            if(rockButton.isSelected()) {
                rock = true;
            }
            else {
                rock = false;
            }
        });
    }


    public int updateElements(String actionEvent) {
        String action = actionEvent;

        if (action.equals(dryString)) {
            new WorldView();
            act = 1;
        } else if (action.equals(wetString)) {
            act = 2;
        } else {
            act = 0;
        }
        return act;
    }

    public static int getAct() {
        return act;
    }
}
