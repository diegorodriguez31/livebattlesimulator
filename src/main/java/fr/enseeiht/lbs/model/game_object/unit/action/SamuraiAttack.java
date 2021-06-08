package main.java.fr.enseeiht.lbs.model.game_object.unit.action;

import main.java.fr.enseeiht.lbs.model.game_object.Statistic;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.FireDebuff;

/**
 * Actions made when an Samurai attacks
 */
public class SamuraiAttack extends AttackAction {

    /**
     * Fire debuff amout of damage per seconds (tic)
     */
    private static final double FIRE_TIC_DAMAGE = 10.0;

    public SamuraiAttack(Unit attaquant) {
        super(attaquant);
    }

    /**
     * During the Samurai attack
     * the target receive damages depending on the attacking unit damage stat
     * the Samurai apply fire to the target
     */
    @Override
    public void execute(long deltaTime) {
        if (target == null||attaquant==null) {
            return;
        }

        if (attaquant.attackSuccess()) {
            target.receiveDamage(attaquant.getStats().getStatisticValue(Statistic.DAMAGE));

            FireDebuff fireDebuff = new FireDebuff(FIRE_TIC_DAMAGE);
            if (!target.hasBuff(fireDebuff)) {
                target.addBuffs(fireDebuff);
            }
        }
    }
}
