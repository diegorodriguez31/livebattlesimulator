package main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.statModifierVisitor;

import main.java.fr.enseeiht.lbs.model.game_object.Statistic;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.FireDebuff;

/**
 * Visite les statistiques de la cible, les copie
 * et applique les modifications propres au Horseman des statistiques en fonction des différents Buffs existants
 */
public class HorsemanStatModifierVisitor extends BasicStatModifierBuffVisitor{

    public HorsemanStatModifierVisitor(Stats stats, Unit unit) {
        super(stats, unit);
    }

    /**
     * Le feu paralyse le Horseman
     */
    @Override
    public void visit(FireDebuff buff) {
        getStats().addStat(Statistic.SPEED, 0);
    }
}
