package main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.stat.modifier.visitor;

import main.java.fr.enseeiht.lbs.model.game.object.Stats;
import main.java.fr.enseeiht.lbs.model.game.object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game.object.unit.buff.*;

/**
 * Visite les statistiques de la cible, les copie
 * et applique l'immunité à la modification des statistiques en fonction des différents Buffs existants
 */
public class ImmuneStatModifierVisitor extends BasicStatModifierBuffVisitor {

    public ImmuneStatModifierVisitor(Stats stats, Unit unit) {
        super(stats, unit);
    }

    /**
     * IMMUNITÉ
     * On ignore le buff
     */
    @Override
    public void visit(FireDebuff buff) {
        // do nothing
    }

    /**
     * IMMUNITÉ
     * On ignore le buff
     */
    @Override
    public void visit(PoisonDebuff buff) {
        // do nothing
    }

    /**
     * IMMUNITÉ
     * On ignore le buff
     */
    @Override
    public void visit(SlowDebuff buff) {
        // do nothing
    }

    /**
     * IMMUNITÉ
     * On ignore le buff
     */
    @Override
    public void visit(PeasantGroupBuff buff) {
        // do nothing
    }

    /**
     * IMMUNITÉ
     * On ignore le buff
     */
    @Override
    public void visit(BreakArmorDebuff buff) {
        // do nothing
    }

    /**
     * IMMUNITÉ
     * On ignore le buff
     */
    @Override
    public void visit(StunDebuff buff) {
        // do nothing
    }
}
