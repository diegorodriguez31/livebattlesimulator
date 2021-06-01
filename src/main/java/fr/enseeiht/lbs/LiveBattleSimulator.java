package main.java.fr.enseeiht.lbs;

import main.java.fr.enseeiht.lbs.model.battleSimulator.Army;
import main.java.fr.enseeiht.lbs.model.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.model.battleSimulator.Extermination;
import main.java.fr.enseeiht.lbs.model.gameObject.Vector2;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.Unit;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.soldier.Knight;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.soldier.Peasant;
import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.view.gui.BattleWorldGUI;
import main.java.fr.enseeiht.lbs.view.gui.LiveBattleSimulatorGUI;
import main.java.fr.enseeiht.lbs.view.gui.WorldText;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class LiveBattleSimulator {
    public static final int SUPER_PIXEL_SIZE = 11;

    public static void main(String[] args) {
        LiveBattleSimulatorGUI.getInstance();
    }

    public static LiveBattleSimulatorGUI mainFrame() {
        return LiveBattleSimulatorGUI.getInstance();
    }

    public static List<Army> createArmies() {
        List<Army> armies = new ArrayList<>();

        // ARMY 1
        Army army1 = new Army();

        for (int i = 15; i <= 24; i = i+3) {
            army1.addUnit(new Knight(new Vector2(10, i)));
        }

        // ARMY 2
        Army army2 = new Army();

        for (int i = 10; i <= 30; i = i+3) {
            army2.addUnit(new Peasant(new Vector2(20, i)));
            army2.addUnit(new Peasant(new Vector2(25, i)));
            army2.addUnit(new Peasant(new Vector2(30, i)));
            army2.addUnit(new Peasant(new Vector2(15, i)));
            army2.addUnit(new Peasant(new Vector2(10, i)));
        }

        //Unit shieldMan = new Shieldman("shieldman", new Vector2(250,260), 200, 1, 10, 50, 3, 10);
        //army2.addUnit(shieldMan);

        armies.add(army1);
        armies.add(army2);

        return armies;
    }
}
