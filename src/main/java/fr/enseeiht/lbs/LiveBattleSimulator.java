package main.java.fr.enseeiht.lbs;

import java.util.ArrayList;
import java.util.List;

import main.java.fr.enseeiht.lbs.battleSimulator.Army;
import main.java.fr.enseeiht.lbs.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.battleSimulator.Extermination;
import main.java.fr.enseeiht.lbs.gameObject.Vector2;
import main.java.fr.enseeiht.lbs.gameObject.unit.soldier.Infantryman;
import main.java.fr.enseeiht.lbs.gameObject.unit.soldier.Knight;
import main.java.fr.enseeiht.lbs.gameObject.unit.soldier.Peasant;
import main.java.fr.enseeiht.lbs.gameObject.unit.soldier.Shieldman;
import main.java.fr.enseeiht.lbs.gameObject.unit.Unit;
import main.java.fr.enseeiht.lbs.view.gui.BattleGUI;

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

        new BattleGUI(battle);
        
        battle.run();
        
    }

    public static List<Army> createArmies(){
        List<Army> armies = new ArrayList<>();

        Army army1 = new Army();

        Unit knight1 = new Knight(new Vector2(100, 150));
        army1.addUnit(knight1);

        Army army2 = new Army();
        Unit peasant2 = new Peasant(new Vector2(200, 210));
        army2.addUnit(peasant2);

        armies.add(army1);
        armies.add(army2);

        return armies;
    }
}
