package main.java.fr.enseeiht.lbs.model.game_object.unit.action;

import main.java.fr.enseeiht.lbs.model.game_object.Statistic;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.FireDebuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.Archer;

public class ArrowAttack extends AttackAction {

    public ArrowAttack(Unit attaquant) {
        super(attaquant);
    }

    @Override
    public void execute(long deltaTime) {
        if (target == null||attaquant==null) {
            return;
        }

        if (attaquant.attackSuccess()) {
            target.receiveDamage(attaquant.getStats().getStatisticValue(Statistic.DAMAGE));

            if (((Archer) attaquant).getNbArrowsShot() == 3) {
                target.addBuffs(new FireDebuff());
            }

            ((Archer) attaquant).updateNbArrowsShot();
        }
    }
}
