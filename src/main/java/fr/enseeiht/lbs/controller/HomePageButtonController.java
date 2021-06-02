package main.java.fr.enseeiht.lbs.controller;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;

import javax.swing.*;

import static main.java.fr.enseeiht.lbs.LiveBattleSimulator.mainFrame;

/**
 * Contrôleur qui reset la bataille et ramène à la page d'accueil.
 */
public class HomePageButtonController extends JButton {

    public HomePageButtonController() {
        super("Accueil");

        this.addActionListener(actionEvent -> {
            Battle.reset();
            mainFrame().showHomePage();
        });
    }
}
