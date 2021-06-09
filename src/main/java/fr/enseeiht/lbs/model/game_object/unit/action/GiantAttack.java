package main.java.fr.enseeiht.lbs.model.game_object.unit.action;

import main.java.fr.enseeiht.lbs.model.game_object.Statistic;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.BreakArmorDebuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.StunDebuff;

/**
 * Actions effectuées lors d'une attaque d'un géant
 */
public class GiantAttack extends AttackAction {

    /**
     * Durée de l'étourdissement en secondes
     */
    private static final double STUN_DURATION = 1.0;

    public GiantAttack(Unit attaquant) {
        super(attaquant);
    }

    /**
     * Pendant l'attaque du géant
     * la cible reçoit des dégâts
     * le géant brise l'armure de la cible
     * le géant étourdit la cible (elle met plus de temps avant de frapper à nouveau)
     */
    @Override
    public void execute(long deltaTime) {
        if (target == null||attaquant==null) {
            return;
        }

        if (attaquant.attackSuccess()) {
            target.receiveDamage(attaquant.getStats().getStatisticValue(Statistic.DAMAGE));
            target.addBuffs(new BreakArmorDebuff());
            target.addBuffs(new StunDebuff(STUN_DURATION));
        }
    }

    @Override
    public void setTarget(Unit unit) {
        target = unit;
    }
}
