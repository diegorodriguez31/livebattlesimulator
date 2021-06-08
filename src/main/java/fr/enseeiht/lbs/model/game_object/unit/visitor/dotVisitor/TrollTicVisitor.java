package main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.dotVisitor;

import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.PoisonDebuff;

/**
 * Comportements du Troll face aux tics de buffs
 */
public class TrollTicVisitor extends BasicTicVisitor{

    public TrollTicVisitor(long deltaTime, Unit unit) {
        super(deltaTime, unit);
    }

    /**
     * IMMUNITÉ
     * On ignore le buff de poison
     */
    @Override
    public void visit(PoisonDebuff buff) {
        // do nothing
    }
}
