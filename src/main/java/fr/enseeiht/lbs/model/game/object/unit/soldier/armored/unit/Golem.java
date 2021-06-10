package main.java.fr.enseeiht.lbs.model.game.object.unit.soldier.armored.unit;

import main.java.fr.enseeiht.lbs.model.battle.simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game.object.Stats;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.AttackAction;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.GroundHeavyMovementAction;
import main.java.fr.enseeiht.lbs.model.game.object.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.dot.visitor.BasicTicVisitor;
import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.dot.visitor.ImmuneTicVisitor;
import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.stat.modifier.visitor.BasicStatModifierBuffVisitor;
import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.stat.modifier.visitor.ImmuneStatModifierVisitor;
import main.java.fr.enseeiht.lbs.utils.Vector2;

/**
 * L'unité Golem
 */
public class Golem extends ArmoredUnit {

    public Golem(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
        ai = new ChargeAndHitAI(new AttackAction(this), new GroundHeavyMovementAction(this));
    }

    /**
     * Le golem est immunisé débuffs qui modifient les statistiques
     */
    @Override
    protected BasicStatModifierBuffVisitor getStatModifierVisitor() {
        return new ImmuneStatModifierVisitor(stats, this);
    }

    /**
     * Le golem est immunisé aux tics de dégâts
     */
    @Override
    protected BasicTicVisitor getTicVisitor(long deltaTime) {
        return new ImmuneTicVisitor(deltaTime, this);
    }

    @Override
    public void start(Battle context) {

    }

    @Override
    public void end(Battle context) {

    }
}
