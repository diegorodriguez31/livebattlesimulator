package main.java.fr.enseeiht.lbs;

import java.util.ArrayList;
import java.util.List;

import main.java.fr.enseeiht.lbs.battleSimulator.Army;
import main.java.fr.enseeiht.lbs.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.battleSimulator.Extermination;
import main.java.fr.enseeiht.lbs.gameObject.Vector2;
import main.java.fr.enseeiht.lbs.gameObject.unit.Infantryman;
import main.java.fr.enseeiht.lbs.gameObject.unit.Knight;
import main.java.fr.enseeiht.lbs.gameObject.unit.Shieldman;
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
        Unit unit11 = new Infantryman("Infantry man", new Vector2(30, 10), 100, 1.5, 80, 500, 10);
        army1.addUnit(unit11);

        Unit knight = new Knight(new Vector2(100, 20));
        army1.addUnit(knight);

        Army army2 = new Army();
        Unit unit21 = new Shieldman("Shield man", new Vector2(250, 200), 200, 2.5, 10, 50, 500, 3);
        army2.addUnit(unit21);

        armies.add(army1);
        armies.add(army2);

        return armies;
    }
}
