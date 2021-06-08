package main.java.fr.enseeiht.lbs;

import main.java.fr.enseeiht.lbs.view.gui.LiveBattleSimulatorGUI;

public class LiveBattleSimulator {
    public static final int VERBOSE = 0;

    public static void main(String[] args) {
        LiveBattleSimulatorGUI.getInstance();
    }

    public static LiveBattleSimulatorGUI mainFrame() {
        return LiveBattleSimulatorGUI.getInstance();
    }
}
