package main.java.fr.enseeiht.lbs.model.gameObject.unit.action;

import main.java.fr.enseeiht.lbs.model.gameObject.Entity;
import main.java.fr.enseeiht.lbs.model.gameObject.Statistic;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.Unit;

public class AttackAction implements IAttackAction {

    Entity attaquant;
    Entity target;

    public AttackAction(Entity attaquant) {
        this.attaquant = attaquant;
        this.target = null;
    }

    @Override
    public void execute(long deltaTime) {
        if (target == null||attaquant==null) {
            return;
        }
        target.receiveDamage(attaquant.getStats().getStatisticValue(Statistic.DAMAGE));
    }

    @Override
    public void setTarget(Unit unit) {
        target = unit;
    }
}
