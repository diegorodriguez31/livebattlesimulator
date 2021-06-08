package main.java.fr.enseeiht.lbs.controller;

import main.java.fr.enseeiht.lbs.model.world.World;

import javax.swing.*;
import java.awt.*;

import static main.java.fr.enseeiht.lbs.LiveBattleSimulator.mainFrame;

public class ChoiceMapButtonsController extends JPanel {

    private static JButton reloadMapButton;

    public ChoiceMapButtonsController(){
        //gestion du button ok
        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Sans Serif", Font.PLAIN, 30));
        okButton.addActionListener(actionEvent -> mainFrame().showUnitPlacement());

        reloadMapButton = new JButton("Recharger");
        reloadMapButton.addActionListener(actionEvent -> reloadMapButtonTreatment());

        this.setLayout(new GridLayout(1, 3));

        this.add(new HomePageButtonController());
        this.add(reloadMapButton);
        this.add(okButton);

        //Initiate a first world
        init();
    }

    private void reloadMapButtonTreatment() {
        int percentdesert, percentwater, percentrock, percentplain, percentforest;
        int choix = ChoiceMapPresetController.getInstance().getActiveChoice();
        if(choix ==2) {
            percentdesert = 0;
            percentwater = 30;
            percentforest = 30;
            percentplain = 35;
            percentrock = 5;
        }else if(choix ==1){
            percentdesert = 44;
            percentwater = 5;
            percentforest = 5;
            percentrock = 10;
            percentplain = 36;
        }else{
            percentdesert = 10;
            percentwater = 20;
            percentforest = 25;
            percentplain = 40;
            percentrock = 5;
        }
        //si rock est appuyé
        if (ChoiceMapPresetController.getInstance().hasRock()){
            percentdesert -= 3;
            percentwater -= 3;
            percentforest -= 3;
            percentplain -= 3;
            percentrock += 12;
        }
        World.getInstance().generateWorld(percentdesert, percentwater, percentrock, percentforest, percentplain);
    }

    public static void init(){
        reloadMapButton.doClick();
    }
}