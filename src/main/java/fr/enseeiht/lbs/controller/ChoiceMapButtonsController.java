package main.java.fr.enseeiht.lbs.controller;

import main.java.fr.enseeiht.lbs.model.world.World;

import javax.swing.*;
import java.awt.*;

import static main.java.fr.enseeiht.lbs.LiveBattleSimulator.mainFrame;

/**
 * Contrôleur qui gère le chargement et la validation d'un terrain avant une bataille.
 */
public class ChoiceMapButtonsController extends JPanel {

    private static JButton reloadMapButton;

    public ChoiceMapButtonsController(){
        //gestion du button ok
        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Sans Serif", Font.PLAIN, 30));
        okButton.addActionListener(actionEvent -> mainFrame().showUnitPlacement());

        reloadMapButton = new JButton("Recharger");
        reloadMapButton.setFont(new Font("Sans Serif",Font.PLAIN, 30));
        reloadMapButton.addActionListener(actionEvent -> reloadMapButtonTreatment());

        this.setLayout(new BorderLayout());
        JPanel eastpanel = new JPanel();
        JPanel middlepanel = new JPanel();
        JPanel westpanel = new JPanel();
        HomePageButtonController accueilButton = new HomePageButtonController();
        westpanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 300));
        eastpanel.setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 30));
        middlepanel.setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 200));
        //JPanel westpanel = new JPanel();


        this.add(westpanel,BorderLayout.WEST);
        this.add(middlepanel,BorderLayout.CENTER);
        this.add(eastpanel,BorderLayout.EAST);
        westpanel.add(accueilButton);
        middlepanel.add(reloadMapButton);
        eastpanel.add(okButton);

        //Initiate a first world
        init();
    }

    private void reloadMapButtonTreatment() {
        int percentdesert, percentwater, percentrock, percentplain, percentforest, percentlava, percentsnow;
        int choix = ChoiceMapPresetController.getInstance().getActiveChoice();
        if (choix == 1) {//humide
            percentdesert = 3;
            percentwater = 30;
            percentforest = 26;
            percentplain = 35;
            percentrock = 0;
            percentlava = 3;
            percentsnow = 3;
        } else if (choix == 2) {//desertique
            percentdesert = 44;
            percentwater = 5;
            percentforest = 5;
            percentrock = 10;
            percentplain = 30;
            percentlava = 3;
            percentsnow = 3;
        }  else if (choix == 3) {//volcanique
            percentdesert = 30;
            percentwater = 10;
            percentforest = 0;
            percentrock = 30;
            percentplain = 20;
            percentlava = 28;
            percentsnow = 0;
        } else if (choix == 4) {//enneigé
            percentdesert = 0;
            percentwater = 6;
            percentforest = 30;
            percentrock = 10;
            percentplain = 17;
            percentlava = 0;
            percentsnow = 31;
        }else {//classique
            percentdesert = 10;
            percentwater = 20;
            percentforest = 24;
            percentplain = 33;
            percentrock = 5;
            percentlava = 5;
            percentsnow = 3;

        }
        //si rock est appuyé
        if (ChoiceMapPresetController.getInstance().hasRock()) {
            percentdesert = percentdesert - 2;
            percentwater = percentwater - 2;
            percentforest = percentforest - 2;
            percentplain = percentplain - 2;
            percentrock = percentrock + 12;
            percentlava = percentlava -2;
            percentsnow = percentsnow -2;
        }


        World.getInstance().generateWorld(percentdesert, percentwater, percentrock, percentforest, percentplain, percentlava, percentsnow);
    };

    public static void init(){
        reloadMapButton.doClick();
    }
}