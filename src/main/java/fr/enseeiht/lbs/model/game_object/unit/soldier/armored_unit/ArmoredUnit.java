package main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.armored_unit;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.AttackAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.FlightMovementAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.utils.Vector2;

import static main.java.fr.enseeiht.lbs.model.game_object.Statistic.ARMOR;

/**
 * Factorise les comportements d'Unités liées à l'armure
 */
public abstract class ArmoredUnit extends Unit {

    public ArmoredUnit(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
        ai = new ChargeAndHitAI(new AttackAction(this), new FlightMovementAction(this));
    }

    @Override
    public void status() {
        super.status();
        System.out.println("\tArmor : " + getStats().getStatisticValue(ARMOR));
        System.out.println("\n");
    }

    /**
     * Réduit les dégâts sur la santé en fonction de l'armure
     * (armure = pourcentage de réduction de dégâts)
     */
    @Override
    public void receiveDamage(double damage) {
        double reducedDamage = damage - (damage * (getStats().getStatisticValue(ARMOR) / 100));
        super.receiveDamage(reducedDamage);
    }

    public boolean hasArmor() {
        return getStats().getStatisticValue(ARMOR) > 0;
    }

    @Override
    public void start(Battle context) {

    }

    @Override
    public void end(Battle context) {

    }
}
