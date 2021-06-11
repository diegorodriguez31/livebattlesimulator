package main.java.fr.enseeiht.lbs.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Contrôleur qui gère la personalisation du terrain avant une bataille.
 */
public class ChoiceMapPresetController extends JPanel {


    private final static String DEFAULT_WORLD = "terrain classique";
    private final static String DRY_WORLD = "terrain sec (désert et roches)";
    private final static String WET_WORLD = "terrain humide (forêt et eau)";
    private final static String LAVA_WORLD = "terrain volcanique (lave, rock)";
    private final static String COLD_WORLD = "terrain froid (forêt, neige)";

    private final JRadioButton defaultButton;
    private final JRadioButton wetButton;
    private final JRadioButton dryButton;
    private final JRadioButton lavaButton;
    private final JRadioButton coldButton;
    private final JCheckBox rockButton;

    private static int activeChoice;

    private static boolean rocks;

    private static ChoiceMapPresetController instance;

    private ChoiceMapPresetController() {
        //le main panel permet d'afficher les boutons radio en groupe au lieu de les espacer sur la page
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 1));
        this.add(mainPanel);


        defaultButton = new JRadioButton(DEFAULT_WORLD);
        wetButton = new JRadioButton(WET_WORLD);
        dryButton = new JRadioButton(DRY_WORLD);
        lavaButton = new JRadioButton(LAVA_WORLD);
        coldButton = new JRadioButton(COLD_WORLD);
        rockButton = new JCheckBox("plus grand nombre de roches");

        mainPanel.add(defaultButton);
        mainPanel.add(wetButton);
        mainPanel.add(dryButton);
        mainPanel.add(lavaButton);
        mainPanel.add(coldButton);
        mainPanel.add(rockButton);

        ButtonGroup group = new ButtonGroup();
        group.add(defaultButton);
        group.add(dryButton);
        group.add(wetButton);
        group.add(lavaButton);
        group.add(coldButton);

        init();

        defaultButton.setActionCommand(DEFAULT_WORLD);
        defaultButton.addActionListener(actionEvent -> updateActiveChoice(defaultButton.getActionCommand()));

        wetButton.setActionCommand(WET_WORLD);
        wetButton.addActionListener(actionEvent -> updateActiveChoice(wetButton.getActionCommand()));

        dryButton.setActionCommand(DRY_WORLD);
        dryButton.addActionListener(actionEvent -> updateActiveChoice(dryButton.getActionCommand()));

        lavaButton.setActionCommand(LAVA_WORLD);
        lavaButton.addActionListener(actionEvent -> updateActiveChoice(lavaButton.getActionCommand()));

        coldButton.setActionCommand(COLD_WORLD);
        coldButton.addActionListener(actionEvent -> updateActiveChoice(coldButton.getActionCommand()));

        rockButton.setMnemonic(KeyEvent.VK_C);
        rockButton.addActionListener(actionEvent -> rocks = !rocks);

    }

    public static ChoiceMapPresetController getInstance(){
        if (instance == null){
            instance = new ChoiceMapPresetController();
        }
        return instance;
    }

    public void init(){
        this.updateActiveChoice(DEFAULT_WORLD);
        rocks = false;

        defaultButton.setSelected(true);
        wetButton.setSelected(false);
        dryButton.setSelected(false);
        lavaButton.setSelected(false);
        coldButton.setSelected(false);
        rockButton.setSelected(false);

    }

    /**
     * Met à jour le type de terrain sélectionné
     * @param selectedWorld le nouveau type de terrain sélectionné
     */
    public void updateActiveChoice(String selectedWorld) {
        switch (selectedWorld) {
            case DEFAULT_WORLD:
                activeChoice = 0;
                break;
            case WET_WORLD:
                activeChoice = 1;
                break;
            case DRY_WORLD:
                activeChoice = 2;
                break;
            case LAVA_WORLD:
                activeChoice = 3;
                break;
            case COLD_WORLD:
                activeChoice = 4;
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
