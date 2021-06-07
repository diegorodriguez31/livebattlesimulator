package main.java.fr.enseeiht.lbs.model.game_object.unit.action;

import main.java.fr.enseeiht.lbs.model.game_object.Statistic;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.FireDebuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.standard_unit.Archer;

public class ArcherAttack extends AttackAction {

    private static final double FIRE_TIC_DAMAGE = 20.0;
    private static final int ARROWS_BEFORE_SPECIAL_SHOT = 3;

    public ArcherAttack(Unit attaquant) {
        super(attaquant);
    }

    @Override
    public void execute(long deltaTime) {
        if (target == null||attaquant==null) {
            return;
        }

        if (attaquant.attackSuccess()) {
            target.receiveDamage(attaquant.getStats().getStatisticValue(Statistic.DAMAGE));

            if (((Archer) attaquant).getNbArrowsShot() == ARROWS_BEFORE_SPECIAL_SHOT) {
                target.addBuffs(new FireDebuff(FIRE_TIC_DAMAGE));
            }

            ((Archer) attaquant).updateNbArrowsShot();
        }
    }
}
