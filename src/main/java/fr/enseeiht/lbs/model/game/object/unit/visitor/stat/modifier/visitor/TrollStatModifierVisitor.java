package main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.stat.modifier.visitor;

import main.java.fr.enseeiht.lbs.model.game.object.Stats;
import main.java.fr.enseeiht.lbs.model.game.object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game.object.unit.buff.PoisonDebuff;

/**
 * Visite les statistiques de la cible, les copie
 * et applique les modifications propres au Troll des statistiques en fonction des différents Buffs existants
 */
public class TrollStatModifierVisitor extends BasicStatModifierBuffVisitor {

    public TrollStatModifierVisitor(Stats stats, Unit unit) {
        super(stats, unit);
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
