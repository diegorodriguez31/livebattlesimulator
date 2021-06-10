package main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.armored_unit;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.GroundHeavyMovementAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.TrollAttack;
import main.java.fr.enseeiht.lbs.model.game_object.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.dotVisitor.BasicTicVisitor;
import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.dotVisitor.TrollTicVisitor;
import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.statModifierVisitor.BasicStatModifierBuffVisitor;
import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.statModifierVisitor.TrollStatModifierVisitor;
import main.java.fr.enseeiht.lbs.utils.Vector2;

/**
 * L'unité Troll
 */
public class Troll extends ArmoredUnit {

    public Troll(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
        ai = new ChargeAndHitAI(new TrollAttack(this), new GroundHeavyMovementAction(this));
    }

    /**
     * Le Troll régait de manière unique aux débuffs qui modifient les statistiques
     * (voir TrollStatModifierVisitor)
     */
    @Override
    protected BasicStatModifierBuffVisitor getStatModifierVisitor() {
        return new TrollStatModifierVisitor(stats, this);
    }

    /**
     * Le Troll régait de manière unique aux tics de dégâts
     * (voir TrollTicVisitor)
     */
    @Override
    protected BasicTicVisitor getTicVisitor(long deltaTime) {
        return new TrollTicVisitor(deltaTime, this);
    }

    @Override
    public void start(Battle context) {

    }

    @Override
    public void end(Battle context) {

    }
}
