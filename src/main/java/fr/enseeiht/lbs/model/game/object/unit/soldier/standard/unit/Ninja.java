package main.java.fr.enseeiht.lbs.model.game.object.unit.soldier.standard.unit;

import main.java.fr.enseeiht.lbs.model.battle.simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game.object.Stats;
import main.java.fr.enseeiht.lbs.model.game.object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.AttackAction;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.GroundMovementAction;
import main.java.fr.enseeiht.lbs.model.game.object.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.utils.Vector2;

/**
 * L'unité Ninja
 */
public class Ninja extends Unit {

    public Ninja(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
        ai = new ChargeAndHitAI(new AttackAction(this), new GroundMovementAction(this));
    }

    @Override
    public void start(Battle context) {

    }

    @Override
    public void end(Battle context) {

    }
}
