package main.java.fr.enseeiht.lbs;

import main.java.fr.enseeiht.lbs.battleSimulator.Army;
import main.java.fr.enseeiht.lbs.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.battleSimulator.Extermination;
import main.java.fr.enseeiht.lbs.controller.SpeedControlPanel;
import main.java.fr.enseeiht.lbs.controller.gui.SpeedControlGUI;
import main.java.fr.enseeiht.lbs.gameObject.unit.Infantryman;
import main.java.fr.enseeiht.lbs.gameObject.unit.Shieldman;
import main.java.fr.enseeiht.lbs.gameObject.unit.Unit;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class LiveBattleSimulator {

    public static void main(String[] args) {
        Battle battle = Battle.getInstance();

        List<Army> armies = createArmies();
        battle.init(new Extermination(), armies);
        for (Army army :armies) {
            for (Unit u :
                    army.getUnits()) {
                u.setReady();
            }
        }
        new SpeedControlGUI(battle);
        battle.run();
    }

    public static List<Army> createArmies(){
        List<Army> armies = new ArrayList<>();

        Army army1 = new Army();
        Unit unit11 = new Infantryman(100, 1, 10, 1000);
        army1.addUnit(unit11);

        Army army2 = new Army();
        Unit unit21 = new Shieldman(200, 1, 10, 50, 500);
        army2.addUnit(unit21);

        armies.add(army1);
        armies.add(army2);

        return armies;
    }
}
