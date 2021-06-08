package main.java.fr.enseeiht.lbs.model.game_object.unit.action;

import main.java.fr.enseeiht.lbs.model.game_object.Statistic;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.PoisonDebuff;

/**
 * Actions effectuées lors d'une attaque d'un troll
 */
public class TrollAttack extends AttackAction {

    /**
     * Montant des dégâts de tics de poison
     */
    private static final double POISON_TIC_DAMAGE = 10.0;

    /**
     * Multiplicateur de temps entre chaque action de la cible
     */
    private static final double COOLDOWN_INCREASE_MULTIPLIER = 1.5;

    public TrollAttack(Unit attaquant) {
        super(attaquant);
    }

    /**
     * Pendant l'attaque du troll
     * la cible reçoit des dégâts
     * le troll applique du poison à la cible
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
