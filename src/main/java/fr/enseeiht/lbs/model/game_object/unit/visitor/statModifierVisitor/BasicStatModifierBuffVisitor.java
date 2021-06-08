package main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.statModifierVisitor;

import main.java.fr.enseeiht.lbs.model.game_object.Statistic;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.*;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.standard_unit.Peasant;
import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.BuffVisitor;

/**
 * Visite les statistiques de la cible, les copie
 * et applique les modifications de base des statistiques en fonction des différents Buffs existants
 */
public class BasicStatModifierBuffVisitor implements BuffVisitor {
    private Stats stats;
    private Unit unit;

    public BasicStatModifierBuffVisitor(Stats stats, Unit unit){
        this.stats = new Stats(stats);
        this.unit = unit;
    }

    /**
     * Le feu ne modifie pas les statistiques
     */
    @Override
    public void visit(FireDebuff buff) {
        // do nothing
    }

    /**
     * Le poison divise la vitesse par 2
     * et augmente le temps d'attente entre chaque attaque de la cible
     */
    @Override
    public void visit(PoisonDebuff buff) {
        stats.addStat(Statistic.SPEED, getStats().getStatisticValue(Statistic.SPEED) / 2);
        stats.addStat(Statistic.COOLDOWN, buff.getCooldownIncreaseMulitplier() * getStats().getStatisticValue(Statistic.COOLDOWN));
    }

    /**
     * Le ralentissement réduit la vitesse de déplacement
     */
    @Override
    public void visit(SlowDebuff buff) {
        stats.addStat(Statistic.SPEED, buff.getSlowAmount() * getStats().getStatisticValue(Statistic.SPEED));
    }

    /**
     * Être dans un groupe de paysants augmente la vitesse de déplacement
     * et réduit le temps d'attente entre chaque attaque
     */
    @Override
    public void visit(PeasantGroupBuff buff) {
        if (((Peasant) unit).isInAGroupOfThree()) {
            stats.addStat(Statistic.SPEED, buff.getSpeedMultiplier() * getStats().getStatisticValue(Statistic.SPEED));
            stats.addStat(Statistic.COOLDOWN, buff.getCooldownReducer() * getStats().getStatisticValue(Statistic.COOLDOWN));
        }
    }

    /**
     * L'armure est brisée
     */
    @Override
    public void visit(BreakArmorDebuff buff) {
        stats.addStat(Statistic.ARMOR, 0);
    }

    /**
     * La cible est étourdie, le temps d'attente entre chaque attaque est plus long
     */
    @Override
    public void visit(StunDebuff buff) {
        stats.addStat(Statistic.COOLDOWN, getStats().getStatisticValue(Statistic.COOLDOWN) + buff.getDuration());
    }

    public Stats getStats() {
        return stats;
    }
}
