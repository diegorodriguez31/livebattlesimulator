package main.java.fr.enseeiht.lbs.model.game_object.unit.action;

import main.java.fr.enseeiht.lbs.model.game_object.Statistic;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.PoisonDebuff;

public class TrollAttack extends AttackAction {

    public TrollAttack(Unit attaquant) {
        super(attaquant);
    }

    @Override
    public void execute(long deltaTime) {
        if (target == null||attaquant==null) {
            return;
        }

        if (attaquant.attackSuccess()) {
            target.receiveDamage(attaquant.getStats().getStatisticValue(Statistic.DAMAGE));

            PoisonDebuff poisonDebuff = new PoisonDebuff(10, 0.5);

            if (!target.hasBuff(poisonDebuff)) {
                target.addBuffs(poisonDebuff);
            }
        }
    }
}
