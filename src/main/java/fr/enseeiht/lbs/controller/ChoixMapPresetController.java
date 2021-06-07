package main.java.fr.enseeiht.lbs.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ChoixMapPresetController extends JPanel {


    private final static String DEFAULT_WORLD = "terrain classiqchoixMapPresetControllerue";
    private final static String DRY_WORLD = "terrain sec (désert et roches)";
    private final static String WET_WORLD = "terrain humide (forêt et eau)";

    private final JRadioButton defaultButton;
    private final JRadioButton wetButton;
    private final JRadioButton dryButton;
    private final JCheckBox rockButton;

    private static int activeChoice;

    private static boolean rocks;

    private static ChoixMapPresetController instance;

    private ChoixMapPresetController() {
        //le main panel permet d'afficher les boutons radio en groupe au lieu de les espacer sur la page
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));
        this.add(mainPanel);


        defaultButton = new JRadioButton(DEFAULT_WORLD);
        wetButton = new JRadioButton(WET_WORLD);
        dryButton = new JRadioButton(DRY_WORLD);
        rockButton = new JCheckBox("plus grand nombre de roches");

        mainPanel.add(defaultButton);
        mainPanel.add(wetButton);
        mainPanel.add(dryButton);
        mainPanel.add(rockButton);

        ButtonGroup group = new ButtonGroup();
        group.add(defaultButton);
        group.add(dryButton);
        group.add(wetButton);

        init();

        defaultButton.setActionCommand(DEFAULT_WORLD);
        defaultButton.addActionListener(actionEvent -> updateActiveChoice(defaultButton.getActionCommand()));

        wetButton.setActionCommand(WET_WORLD);
        wetButton.addActionListener(actionEvent -> updateActiveChoice(wetButton.getActionCommand()));

        dryButton.setActionCommand(DRY_WORLD);
        dryButton.addActionListener(actionEvent -> updateActiveChoice(dryButton.getActionCommand()));

        rockButton.setMnemonic(KeyEvent.VK_C);
        rockButton.addActionListener(actionEvent -> rocks = !rocks);

    }

    public static ChoixMapPresetController getInstance(){
        if (instance == null){
            instance = new ChoixMapPresetController();
        }
        return instance;
    }

    public void init(){
        this.updateActiveChoice(DEFAULT_WORLD);
        rocks = false;

        defaultButton.setSelected(true);
        wetButton.setSelected(false);
        dryButton.setSelected(false);
        rockButton.setSelected(false);

    }

    public void updateActiveChoice(String selectedWorld) {
        switch (selectedWorld) {
            case DEFAULT_WORLD:
                activeChoice = 0;
                break;
            case DRY_WORLD:
                activeChoice = 1;
                break;
            case WET_WORLD:
                activeChoice = 2;
                break;
        }
    }

    public int getActiveChoice() {
        return activeChoice;
    }

    public boolean hasRock() {
        return rocks;
    }
}
