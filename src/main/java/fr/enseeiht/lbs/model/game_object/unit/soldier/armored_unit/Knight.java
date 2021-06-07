package main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.armored_unit;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.AttackAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.FlightMovementAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.BasicDotVisitor;
import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.dotVisitor.KnightDotVisitor;
import main.java.fr.enseeiht.lbs.utils.Vector2;

public class Knight extends ArmoredUnit {

    public Knight(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
        ai = new ChargeAndHitAI(new AttackAction(this), new FlightMovementAction(this));
    }

    protected BasicDotVisitor getUpdateVisitor(long deltaTime) {
        return new KnightDotVisitor(deltaTime, this);
    }

    @Override
    public void start(Battle context) {

    }

    @Override
    public void end(Battle context) {

    }
}
