package main.java.fr.enseeiht.lbs.view.gui;

import main.java.fr.enseeiht.lbs.controller.content.BattleArmiesChoiceController;
import main.java.fr.enseeiht.lbs.controller.content.BattleSimulationController;
import main.java.fr.enseeiht.lbs.controller.content.HomePageController;

import javax.swing.*;
import java.awt.*;

public class LiveBattleSimulatorGUI extends JFrame {

    static LiveBattleSimulatorGUI instance;
    static JPanel cards;

    static String HOME_PAGE_CARD = "HOME_PAGE_CARD";
    static String ARMIES_NB_CHOICES_CARD = "ARMIES_NB_CHOICES_CARD";
    static String BATTLE_SIMULATION_CARD = "BATTLE_SIMULATION_CARD";

    public static LiveBattleSimulatorGUI getInstance() {
        if (instance == null) {
            instance = new LiveBattleSimulatorGUI();
        }
        return instance;
    }

    private LiveBattleSimulatorGUI() {
        cards = new JPanel(new CardLayout());
        getContentPane().add(cards);

        cards.add(new HomePageController(), HOME_PAGE_CARD);
        cards.add(new BattleArmiesChoiceController(), ARMIES_NB_CHOICES_CARD);

        showHomePage();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        /*BattleSimulationController battleSimulationController = new BattleSimulationController();
        getContentPane().add(battleSimulationController,"sim");
        setChangesReady();*/
    }

    private void setChangesReady(){
        pack();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public void showHomePage(){
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, HOME_PAGE_CARD);
        setChangesReady();
    }

    public void showBattleArmiesChoice(){
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, ARMIES_NB_CHOICES_CARD);
        setChangesReady();
    }

    public void showBattleSimulation(){
        cards.add(new BattleSimulationController(), BATTLE_SIMULATION_CARD);
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, BATTLE_SIMULATION_CARD);
        setChangesReady();
    }
}
