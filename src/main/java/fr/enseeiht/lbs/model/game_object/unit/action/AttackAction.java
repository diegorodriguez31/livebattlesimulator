package main.java.fr.enseeiht.lbs.model.game_object.unit.action;

import main.java.fr.enseeiht.lbs.model.game_object.Statistic;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;

/**
 * Actions made during a basic attack
 */
public class AttackAction implements IAttackAction {

    Unit attaquant;
    Unit target;

    public AttackAction(Unit attaquant) {
        this.attaquant = attaquant;
        this.target = null;
    }

    /**
     * During the basic attack
     * target receive damages depending on the attacking unit damage stat
     */
    @Override
    public void execute(long deltaTime) {
        if (target == null||attaquant==null) {
            return;
        }

        if (attaquant.attackSuccess()) {
            target.receiveDamage(attaquant.getStats().getStatisticValue(Statistic.DAMAGE));
        }
    }

    @Override
    public void setTarget(Unit unit) {
        target = unit;
    }
}
