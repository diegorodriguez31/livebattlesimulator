package main.java.fr.enseeiht.lbs.model.game.object.unit.action;

import main.java.fr.enseeiht.lbs.model.game.object.Statistic;
import main.java.fr.enseeiht.lbs.model.game.object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game.object.unit.buff.FireDebuff;

/**
 * Actions effectuées lors d'une attaque d'un samourai
 */
public class SamuraiAttack extends AttackAction {

    /**
     * Montant des dégâts de tics de feu
     */
    private static final double FIRE_TIC_DAMAGE = 10.0;

    public SamuraiAttack(Unit attaquant) {
        super(attaquant);
    }

    /**
     * Pendant l'attaque du samourai
     * le samourai fait des dégâts à la cible
     * le samourai applique du feu à la cible
     */
    @Override
    public void execute(long deltaTime) {
        if (target == null||attaquant==null) {
            return;
        }

        if (attaquant.attackSuccess()) {
            target.receiveDamage(attaquant.getStats().getStatisticValue(Statistic.DAMAGE));
            target.addBuffs(new FireDebuff(FIRE_TIC_DAMAGE));
        }
    }
}
