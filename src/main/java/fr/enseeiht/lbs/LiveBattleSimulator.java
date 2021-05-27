package main.java.fr.enseeiht.lbs;

import java.util.ArrayList;
import java.util.List;

import main.java.fr.enseeiht.lbs.model.gameObject.unit.Unit;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.soldier.Knight;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.soldier.Peasant;
import main.java.fr.enseeiht.lbs.model.battleSimulator.Army;
import main.java.fr.enseeiht.lbs.model.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.model.battleSimulator.Extermination;
import main.java.fr.enseeiht.lbs.model.gameObject.Vector2;
import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.view.gui.BattleWorldGUI;

public class LiveBattleSimulator {
    public static final int SUPER_PIXEL_SIZE = 11;

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


		World world = new World(20, 10, 35, 10, 5, 50);
//		new BattleGUI(battle);
//		new SpeedGUI(battle);
//		new WorldGUI(world);
//		WorldText.writeWorld(world);	
		new BattleWorldGUI(battle, world);

		battle.run();
	}

	public static List<Army> createArmies(){
		List<Army> armies = new ArrayList<>();

		// ARMY 1
		Army army1 = new Army();

		Unit knight1 = new Knight(new Vector2(100, 150));
		army1.addUnit(knight1);

		Unit knight2 = new Knight(new Vector2(100, 350));
		army1.addUnit(knight2);

		// ARMY 2
		Army army2 = new Army();
		Unit peasant2 = new Peasant(new Vector2(200, 200));
		army2.addUnit(peasant2);

		Unit peasant3 = new Peasant(new Vector2(200, 100));
		army2.addUnit(peasant3);

		Unit peasant4 = new Peasant(new Vector2(200, 300));
		army2.addUnit(peasant4);

		Unit peasant5 = new Peasant(new Vector2(200, 20));
		army2.addUnit(peasant5);

		Unit peasant6 = new Peasant(new Vector2(200, 60));
		army2.addUnit(peasant6);

		//Unit shieldMan = new Shieldman("shieldman", new Vector2(250,260), 200, 1, 10, 50, 3, 10);
		//army2.addUnit(shieldMan);

		armies.add(army1);
		armies.add(army2);

		return armies;
	}
}
