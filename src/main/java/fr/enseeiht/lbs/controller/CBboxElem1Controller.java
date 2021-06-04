package main.java.fr.enseeiht.lbs.controller;

import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.model.world.WorldElement;
import main.java.fr.enseeiht.lbs.view.content.WorldView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class CBboxElem1Controller extends JPanel {
    World model;
    WorldElement[] ElementList = WorldElement.values();
    public String dryString = "terrain sec (désert et roches)";
    public String wetString = "terrain humide (forêt et eau)";
    public String mainString = "terrain classique";
    static int act = 0;
    private Component propertyChangeSupport;

    public CBboxElem1Controller(World model) {
        this.model = model;
        this.setLayout(new GridLayout(4, 1));
        JRadioButton mainButton = new JRadioButton(mainString);
        mainButton.setMnemonic(KeyEvent.VK_D);
        mainButton.setActionCommand(mainString);
        mainButton.setSelected(true);
        this.add(mainButton);
        //writetext(mainString);


        JRadioButton wetButton = new JRadioButton(wetString);
        wetButton.setMnemonic(KeyEvent.VK_C);
        wetButton.setActionCommand(wetString);
        this.add(wetButton);

        JRadioButton dryButton = new JRadioButton(dryString);
        dryButton.setMnemonic(KeyEvent.VK_B);
        dryButton.setActionCommand(dryString);
        this.add(dryButton);

        ButtonGroup group = new ButtonGroup();
        group.add(mainButton);
        group.add(dryButton);
        group.add(wetButton);

        JCheckBox rockButton;
        rockButton = new JCheckBox("terrain rocheux");
        rockButton.setMnemonic(KeyEvent.VK_C);
        rockButton.setSelected(true);
        this.add(rockButton);

        rockButton.addActionListener(actionEvent -> {

        });

        mainButton.addActionListener(actionEvent -> {
            updateElements(this.mainString);
        });
        mainButton.addPropertyChangeListener(mainString, new WorldView());

        wetButton.addActionListener(actionEvent -> {
            updateElements(this.wetString);
        });
        wetButton.addPropertyChangeListener(wetString, new WorldView());


        dryButton.addActionListener(actionEvent -> {
            updateElements(this.dryString);
        });
        dryButton.addPropertyChangeListener(dryString, new WorldView());

    }


    public int updateElements(String actionEvent) {
        String action = actionEvent;
        if (action.equals(dryString)) {
            new WorldView();
            act = 1;
            System.out.println(act);

        } else if (action.equals(wetString)) {
            act = 2;
            System.out.println(act);

        } else {
            act = 0;
            System.out.println(act);

        }
        return act;
    }

    public void writetext(String text) {
        JTextArea texte = new JTextArea(text);
        texte.setEditable(false);
        texte.setBorder(BorderFactory.createEmptyBorder(8, 5, 20, 0));
        texte.setColumns(10);
        texte.setBackground(getBackground());
        texte.setLineWrap(true);
        texte.setRows(3);
        texte.setWrapStyleWord(true);
        this.add(texte);
    }

    public static int getAct() {
        return act;
    }
}
