package main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.dotVisitor;

import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.*;

/**
 * Comportements d'immunité face aux tics de buffs
 */
public class ImmuneTicVisitor extends BasicTicVisitor {

    public ImmuneTicVisitor(long deltaTime, Unit unit) {
        super(deltaTime, unit);
    }

    /**
     * IMMUNITÉ
     * On ignore le buff de feu
     */
    @Override
    public void visit(FireDebuff buff) {
        // do nothing
    }

    /**
     * IMMUNITÉ
     * On ignore le buff de poison
     */
    @Override
    public void visit(PoisonDebuff buff) {
        // do nothing
    }

    /**
     * IMMUNITÉ
     * On ignore le buff de ralentissement
     */
    @Override
    public void visit(SlowDebuff buff) {
        // do nothing
    }

    /**
     * IMMUNITÉ
     * On ignore le buff de groupe de paysant
     */
    @Override
    public void visit(PeasantGroupBuff buff) {
        // do nothing
    }

    /**
     * IMMUNITÉ
     * On ignore le buff de brisage d'armure
     */
    @Override
    public void visit(BreakArmorDebuff buff) {
        // do nothing
    }

    /**
     * IMMUNITÉ
     * On ignore le buff de ralentissement
     */
    @Override
    public void visit(StunDebuff buff) {
        // do nothing
    }
}
