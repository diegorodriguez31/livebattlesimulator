package main.java.fr.enseeiht.lbs.model.game.object.unit.soldier.armored.unit;

import main.java.fr.enseeiht.lbs.model.battle.simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.AttackAction;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.GroundMovementAction;
import main.java.fr.enseeiht.lbs.model.game.object.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.stat.modifier.visitor.BasicStatModifierBuffVisitor;
import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.stat.modifier.visitor.HorsemanStatModifierVisitor;
import main.java.fr.enseeiht.lbs.model.game.object.Stats;
import main.java.fr.enseeiht.lbs.utils.Vector2;

/**
 * L'unité Horseman
 */
public class Horseman extends ArmoredUnit {

    public Horseman(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
        ai = new ChargeAndHitAI(new AttackAction(this), new GroundMovementAction(this));
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
