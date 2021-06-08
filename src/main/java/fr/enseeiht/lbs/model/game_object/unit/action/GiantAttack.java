package main.java.fr.enseeiht.lbs.model.game_object.unit.action;

import main.java.fr.enseeiht.lbs.model.game_object.Statistic;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.BreakArmorDebuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.StunDebuff;

public class GiantAttack extends AttackAction {

    private static final double STUN_DURATION = 1.0;

    public GiantAttack(Unit attaquant) {
        super(attaquant);
    }

    @Override
    public void execute(long deltaTime) {
        if (target == null||attaquant==null) {
            return;
        }

        if (attaquant.attackSuccess()) {
            target.receiveDamage(attaquant.getStats().getStatisticValue(Statistic.DAMAGE));

            BreakArmorDebuff breakArmorDebuff = new BreakArmorDebuff();
            if (!target.hasBuff(breakArmorDebuff)) {
                target.addBuffs(breakArmorDebuff);
            }

            StunDebuff stunDebuff = new StunDebuff(STUN_DURATION);
            if (!target.hasBuff(stunDebuff)) {
                target.addBuffs(stunDebuff);
            }
        }
    }

    @Override
    public void setTarget(Unit unit) {
        target = unit;
    }
}
