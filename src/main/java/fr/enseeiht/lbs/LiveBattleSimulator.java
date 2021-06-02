package main.java.fr.enseeiht.lbs;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Army;
import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game_object.Vector2;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.Knight;
import main.java.fr.enseeiht.lbs.view.gui.LiveBattleSimulatorGUI;

import java.util.List;

public class LiveBattleSimulator {
    public static final int SUPER_PIXEL_SIZE = 11;

    public static void main(String[] args) {
        LiveBattleSimulatorGUI.getInstance();
    }

    public static LiveBattleSimulatorGUI mainFrame() {
        return LiveBattleSimulatorGUI.getInstance();
    }

    public static void createArmies() {
        List<Army> armies = Battle.getInstance().getArmies();

        for (Army army: armies) {
            army.addUnit(new Knight(new Vector2(10 *(army.getArmyIndex() + 1), 10 * (army.getArmyIndex() + 1))));
        }
    }
}
