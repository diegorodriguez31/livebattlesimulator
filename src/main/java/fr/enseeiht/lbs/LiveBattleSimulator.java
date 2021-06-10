package main.java.fr.enseeiht.lbs;

import main.java.fr.enseeiht.lbs.view.gui.LiveBattleSimulatorGUI;

/**
 * Classe principale de l'application.
 * Contient la fonction main().
 */
public class LiveBattleSimulator {
    public static final int VERBOSE = 0;

    public static void main(String[] args) {
        LiveBattleSimulatorGUI.getInstance();
    }

    /**
     * Accès à la fenêtre principale de l'application
     * @return LiveBattleSimulatorGUI la fenêtre principale
     */
    public static LiveBattleSimulatorGUI mainFrame() {
        return LiveBattleSimulatorGUI.getInstance();
    }
}
