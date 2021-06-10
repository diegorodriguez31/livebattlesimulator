package main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.dot.visitor;

import main.java.fr.enseeiht.lbs.model.game.object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game.object.unit.buff.*;
import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.BuffVisitor;

/**
 * Comportements de base face aux tics en fonction des différents Buffs existants
 */
public class BasicTicVisitor implements BuffVisitor {

    /**
     * Moment (en milliseconde) où la cible reçoit le tic
     */
    protected long deltaTime;

    /**
     * Cible du buff
     */
    protected Unit unit;

    public BasicTicVisitor(long deltaTime, Unit unit) {
        this.deltaTime = deltaTime;
        this.unit = unit;
    }

    /**
     * La cible reçoit chaque seconde des dégâts de feu
     * correspondants au montant du tic du buff
     */
    @Override
    public void visit(FireDebuff buff) {
        unit.receiveDamage(buff.getTicDamage() * deltaTime/1000);
    }

    /**
     * La cible reçoit chaque seconde des dégâts de poison
     * correspondants au montant du tic du buff
     */
    @Override
    public void visit(PoisonDebuff buff) {
        unit.receiveDamage(buff.getTicDamage() * deltaTime/1000);
    }

    /**
     * Le buff de ralentissement ne provoque pas de tics
     */
    @Override
    public void visit(SlowDebuff buff) {
        // do nothing
    }

    /**
     * Le buff de groupe de paysant ne provoque pas de tics
     */
    @Override
    public void visit(PeasantGroupBuff buff) {
        // do nothing
    }

    /**
     * Le buff de brisage d'armure ne provoque pas de tics
     */
    @Override
    public void visit(BreakArmorDebuff buff) {
        // do nothing
    }

    /**
     * Le buff d'étourdissement ne provoque pas de tics
     */
    @Override
    public void visit(StunDebuff buff) {
        // do nothing
    }

    /**
     * Le buff limité dans le temps se fait décrémenter
     */
    @Override
    public void visit(TimedBuff buff) {
        buff.decDuration(deltaTime);
    }
}
