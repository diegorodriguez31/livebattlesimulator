package main.java.fr.enseeiht.lbs.model.game_object.unit.action;

import main.java.fr.enseeiht.lbs.model.game_object.Statistic;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.PoisonDebuff;

/**
 * Actions made when an Troll attacks
 */
public class TrollAttack extends AttackAction {

    /**
     * Poison debuff amout of damage per seconds (tic)
     */
    private static final double POISON_TIC_DAMAGE = 10.0;

    /**
     * Cooldown increase multiplier
     */
    private static final double COOLDOWN_INCREASE_MULTIPLIER = 1.5;

    public TrollAttack(Unit attaquant) {
        super(attaquant);
    }

    /**
     * During the Troll attack
     * the target receive damages depending on the attacking unit damage stat
     * the Troll apply poison to the target
     */
    @Override
    public void execute(long deltaTime) {
        if (target == null||attaquant==null) {
            return;
        }

        if (attaquant.attackSuccess()) {
            target.receiveDamage(attaquant.getStats().getStatisticValue(Statistic.DAMAGE));

            PoisonDebuff poisonDebuff = new PoisonDebuff(POISON_TIC_DAMAGE, COOLDOWN_INCREASE_MULTIPLIER);
            if (!target.hasBuff(poisonDebuff)) {
                target.addBuffs(poisonDebuff);
            }
        }
    }
}
