package main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.standard_unit;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.ArrowAttack;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.AttackAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.FlightMovementAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.utils.Vector2;

public class Alien extends Unit {

    public Alien(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
        ai = new ChargeAndHitAI(new AttackAction(this), new FlightMovementAction(this));
    }

    @Override
    public void start(Battle context) {

    }

    @Override
    public void end(Battle context) {

    }
}
