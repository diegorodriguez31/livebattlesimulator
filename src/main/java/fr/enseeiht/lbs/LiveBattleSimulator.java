package main.java.fr.enseeiht.lbs;

import java.util.ArrayList;
import java.util.List;

import main.java.fr.enseeiht.lbs.model.battleSimulator.Army;
import main.java.fr.enseeiht.lbs.model.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.model.battleSimulator.Extermination;
import main.java.fr.enseeiht.lbs.model.gameObject.Vector2;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.Infantryman;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.Shieldman;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.Unit;
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
        Unit unit11 = new Infantryman(new Vector2(250, 10), 100, 1.5, 80, 500, 10);
        army1.addUnit(unit11);

        Army army2 = new Army();
        Unit unit21 = new Shieldman(new Vector2(250, 200), 200, 2.5, 10, 50, 8000, 3);
        army2.addUnit(unit21);

        armies.add(army1);
        armies.add(army2);

        return armies;
    }
}
