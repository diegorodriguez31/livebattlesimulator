package main.java.fr.enseeiht.lbs.view.gui;

import main.java.fr.enseeiht.lbs.controller.content.BattleArmiesChoiceController;
import main.java.fr.enseeiht.lbs.controller.content.BattleSimulationController;
import main.java.fr.enseeiht.lbs.controller.content.HomePageController;

import javax.swing.*;

public class LiveBattleSimulatorGUI extends JFrame {

    static LiveBattleSimulatorGUI instance;

    public static LiveBattleSimulatorGUI getInstance() {
        if (instance == null) {
            instance = new LiveBattleSimulatorGUI();
        }
        return instance;
    }

    private LiveBattleSimulatorGUI() {
        showHomePage();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void setChangesReady(){
        this.pack();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }

    public void showHomePage(){
        getContentPane().removeAll();
        getContentPane().add(new HomePageController());
        setChangesReady();
    }

    public void showBattleArmiesChoice(){
        getContentPane().removeAll();
        getContentPane().add(new BattleArmiesChoiceController());
        setChangesReady();
    }

    public void showBattleSimulation(){
        BattleSimulationController battleSimulationController = new BattleSimulationController();
        getContentPane().removeAll();
        getContentPane().add(battleSimulationController);
        setChangesReady();
    }
}
