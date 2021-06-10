package main.java.fr.enseeiht.lbs.model.game.object.unit.soldier.armored.unit;

import main.java.fr.enseeiht.lbs.model.battle.simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game.object.Statistic;
import main.java.fr.enseeiht.lbs.model.game.object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.AttackAction;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.GroundMovementAction;
import main.java.fr.enseeiht.lbs.model.game.object.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.model.game.object.Stats;
import main.java.fr.enseeiht.lbs.utils.Vector2;

/**
 * Factorise les comportements d'Unités liées à l'armure
 */
public abstract class ArmoredUnit extends Unit {

    public ArmoredUnit(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
        ai = new ChargeAndHitAI(new AttackAction(this), new GroundMovementAction(this));
    }

    @Override
    public void status() {
        super.status();
        System.out.println("\tArmor : " + getStats().getStatisticValue(Statistic.ARMOR));
        System.out.println("\n");
    }

    /**
     * Réduit les dégâts sur la santé en fonction de l'armure
     * (armure = pourcentage de réduction de dégâts)
     */
    @Override
    public void receiveDamage(double damage) {
        double reducedDamage = damage - (damage * (getStats().getStatisticValue(Statistic.ARMOR) / 100));
        super.receiveDamage(reducedDamage);
    }

    public boolean hasArmor() {
        return getStats().getStatisticValue(Statistic.ARMOR) > 0;
    }

    @Override
    public void start(Battle context) {

    }

    @Override
    public void end(Battle context) {

    }
}
