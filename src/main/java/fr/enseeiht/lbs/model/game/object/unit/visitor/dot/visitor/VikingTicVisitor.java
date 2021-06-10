package main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.dot.visitor;

import main.java.fr.enseeiht.lbs.model.game.object.unit.Unit;

/**
 * Comportements du Viking face aux tics de buffs
 */
public class VikingTicVisitor extends BasicTicVisitor{

    /**
     * Multiplicateur de faiblaisse / sensibilité aux tics des buffs
     */
    private static final int WEAKNESS_MULTIPLIER = 2;

    /**
     * Le Viking est plus sensible aux tics des buffs
     */
    public VikingTicVisitor(long deltaTime, Unit unit) {
        super(deltaTime * WEAKNESS_MULTIPLIER, unit);
    }
}
