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
import main.java.fr.enseeiht.lbs.view.gui.WorldText;

import java.util.ArrayList;
import java.util.List;

public class LiveBattleSimulator {
    public static final int SUPER_PIXEL_SIZE = 11;

    public static void main(String[] args) {
        Battle battle = Battle.getInstance();

        List<Army> armies = createArmies();
        battle.init(new Extermination(), armies);
        for (Army army : armies) {
            for (Unit u :
                    army.getUnits()) {
                u.setReady();
            }
        }


        World world = new World(20, 20, 35, 10, 5, 50);

//		new BattleGUI(battle);
//		new SpeedGUI(battle);
//		new WorldGUI(world);
//      WorldText.writeWorld(world);
        new BattleWorldGUI(battle, world);

        battle.run();
    }

    public static List<Army> createArmies() {
        List<Army> armies = new ArrayList<>();

        // ARMY 1
        Army army1 = new Army();

        Unit knight1 = new Knight(new Vector2(10, 15));
        army1.addUnit(knight1);

        Unit knight2 = new Knight(new Vector2(10, 35));
        army1.addUnit(knight2);

        // ARMY 2
        Army army2 = new Army();
        Unit peasant2 = new Peasant(new Vector2(20, 20));
        army2.addUnit(peasant2);

        Unit peasant3 = new Peasant(new Vector2(20, 10));
        army2.addUnit(peasant3);

        Unit peasant4 = new Peasant(new Vector2(20, 30));
        army2.addUnit(peasant4);

        Unit peasant5 = new Peasant(new Vector2(20, 2));
        army2.addUnit(peasant5);

        Unit peasant6 = new Peasant(new Vector2(20, 6));
        army2.addUnit(peasant6);

        //Unit shieldMan = new Shieldman("shieldman", new Vector2(250,260), 200, 1, 10, 50, 3, 10);
        //army2.addUnit(shieldMan);

        armies.add(army1);
        armies.add(army2);

        return armies;
    }
}
