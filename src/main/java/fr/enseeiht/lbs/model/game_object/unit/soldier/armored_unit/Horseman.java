package main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.armored_unit;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.AttackAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.FlightMovementAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.statModifierVisitor.BasicStatModifierBuffVisitor;
import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.statModifierVisitor.HorsemanStatModifierVisitor;
import main.java.fr.enseeiht.lbs.utils.Vector2;

public class Horseman extends ArmoredUnit {

    public Horseman(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
        ai = new ChargeAndHitAI(new AttackAction(this), new FlightMovementAction(this));
    }

    /**
     * Le horseman réagit de manière unique aux débuffs qui modifient les statistiques
     * (voir HorsemanStatModifierVisitor)
     */
    @Override
    protected BasicStatModifierBuffVisitor getStatModifierVisitor() {
        return new HorsemanStatModifierVisitor(stats, this);
    }

    @Override
    public void start(Battle context) {

    }

    @Override
    public void end(Battle context) {

    }
}
