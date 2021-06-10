package main.java.fr.enseeiht.lbs.model.game.object.unit.action;

import main.java.fr.enseeiht.lbs.model.game.object.Statistic;
import main.java.fr.enseeiht.lbs.model.game.object.unit.Unit;

/**
 * Actions effectuées lors d'une attaque de base
 */
public class AttackAction implements IAttackAction {

    Unit attaquant;
    Unit target;

    public AttackAction(Unit attaquant) {
        this.attaquant = attaquant;
        this.target = null;
    }

    /**
     * Pendant l'attaque de base
     * la cible reçoit des dégâts du montant de ceux de l'attaquant
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
