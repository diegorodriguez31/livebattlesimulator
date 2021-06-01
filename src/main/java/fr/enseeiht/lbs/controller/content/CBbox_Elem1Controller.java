package main.java.fr.enseeiht.lbs.controller.content;

import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.model.world.WorldElement;
import main.java.fr.enseeiht.lbs.view.content.WorldView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class CBbox_Elem1Controller extends JPanel implements ActionListener {
    World model;
    WorldElement[] ElementList = WorldElement.values();
    static String dryString = "terrain sec (désert et roches)";
    static String wetString = "terrain humide (forêt et eau)";
    static String mainString = "terrain classique";
    private Component propertyChangeSupport;

    public CBbox_Elem1Controller(World model) {
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
        mainButton.addActionListener(this);
        mainButton.addPropertyChangeListener(mainString,new WorldView(model));
        wetButton.addActionListener(this);
        wetButton.addPropertyChangeListener(wetString,new WorldView(model));
        dryButton.addActionListener(this);
        dryButton.addPropertyChangeListener(dryString,new WorldView(model));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        updateElements(actionEvent);


    }

    public String updateElements(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        if (action == dryString) {
            new WorldView(model);
            model.notify();
            return dryString;
        } else if (action == wetString) {
            return wetString;
        }
        return mainString;
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
}
