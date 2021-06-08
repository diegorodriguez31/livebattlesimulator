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
    public String lavaString = "terrain volcanique (lave, rock)";
    public String coldString = "terrain froid (forêt, neige)";
    static int act = 0;
    static boolean rock = false;


    public ChoixMapButtonController(World world) {

        this.setLayout(new GridLayout(6, 1));
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

        JRadioButton lavaButton = new JRadioButton(lavaString);
        lavaButton.setActionCommand(lavaString);
        lavaButton.setSelected(true);
        this.add(lavaButton);
        lavaButton.addActionListener(actionEvent -> {
            updateElements(this.lavaString);
        });
        lavaButton.addPropertyChangeListener(lavaString, new WorldView());

        JRadioButton snowButton = new JRadioButton(coldString);
        snowButton.setActionCommand(coldString);
        snowButton.setSelected(true);
        this.add(snowButton);
        snowButton.addActionListener(actionEvent -> {
            updateElements(this.coldString);
        });
        snowButton.addPropertyChangeListener(coldString, new WorldView());

        ButtonGroup group = new ButtonGroup();
        group.add(mainButton);
        group.add(dryButton);
        group.add(wetButton);
        group.add(lavaButton);
        group.add(snowButton);

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
        } else if (action.equals(lavaString)) {
            act = 3;
        }
        else if (action.equals(coldString)) {
            act = 4;
        }else {
            act = 0;
        }
        return act;
    }

    public static int getAct() {
        return act;
    }
}
