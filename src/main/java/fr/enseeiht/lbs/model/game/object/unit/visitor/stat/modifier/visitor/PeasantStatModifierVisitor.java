package main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.stat.modifier.visitor;

import main.java.fr.enseeiht.lbs.model.game.object.Statistic;
import main.java.fr.enseeiht.lbs.model.game.object.Stats;
import main.java.fr.enseeiht.lbs.model.game.object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game.object.unit.buff.PeasantGroupBuff;
import main.java.fr.enseeiht.lbs.model.game.object.unit.soldier.standard.unit.Peasant;

/**
 * Visite les statistiques de la cible, les copie et applique les modifications propres
 * au Paysant des statistiques en fonction des différents Buffs existants
 */
public class PeasantStatModifierVisitor extends BasicStatModifierBuffVisitor {

    public PeasantStatModifierVisitor(Stats stats, Unit unit) {
        super(stats, unit);
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
}
