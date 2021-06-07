package main.java.fr.enseeiht.lbs;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Army;
import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.Knight;
import main.java.fr.enseeiht.lbs.utils.Vector2;
import main.java.fr.enseeiht.lbs.view.gui.LiveBattleSimulatorGUI;

import java.util.List;

public class LiveBattleSimulator {
    public static final int VERBOSE = 0;

    public static void main(String[] args) {
        LiveBattleSimulatorGUI.getInstance();
    }

    public static LiveBattleSimulatorGUI mainFrame() {
        return LiveBattleSimulatorGUI.getInstance();
    }
}
