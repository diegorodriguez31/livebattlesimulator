package main.java.fr.enseeiht.lbs.model.gameObject.unit.action;

import main.java.fr.enseeiht.lbs.model.gameObject.Entity;
import main.java.fr.enseeiht.lbs.model.gameObject.Statistic;

public class AttackAction implements Action {

    Entity attaquant;
    Entity victime;

    public AttackAction(Entity attaquant, Entity victime) {
        this.attaquant = attaquant;
        this.victime = victime;
    }

    @Override
    public void execute() {
        System.out.println("execute attack");
        victime.receiveDamage(attaquant.getStats().getStatisticValue(Statistic.DAMAGE));
    }
}
