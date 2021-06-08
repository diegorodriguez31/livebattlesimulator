package main.java.fr.enseeiht.lbs.model.game_object.unit.action;

import main.java.fr.enseeiht.lbs.model.game_object.Statistic;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.FireDebuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.standard_unit.Archer;

/**
 * Actions made when an Archer attacks
 */
public class ArcherAttack extends AttackAction {

    /**
     * Fire debuff amout of damage per seconds (tic)
     */
    private static final double FIRE_TIC_DAMAGE = 20.0;

    /**
     * Special shot indice (1 special shot over 3)
     */
    private static final int ARROWS_BEFORE_SPECIAL_SHOT = 3;

    public ArcherAttack(Unit attaquant) {
        super(attaquant);
    }

    /**
     * During the alien attack
     * each shots apply damages to the target
     * 1 shot over 3, the special shot apply fire to the target
     */
    @Override
    public void execute(long deltaTime) {
        if (target == null||attaquant==null) {
            return;
        }

        if (attaquant.attackSuccess()) {
            target.receiveDamage(attaquant.getStats().getStatisticValue(Statistic.DAMAGE));

            FireDebuff fireDebuff = new FireDebuff(FIRE_TIC_DAMAGE);
            if (!target.hasBuff(fireDebuff) && ((Archer) attaquant).getNbArrowsShot() == ARROWS_BEFORE_SPECIAL_SHOT) {
                target.addBuffs(fireDebuff);
            }

            ((Archer) attaquant).updateNbArrowsShot();
        }
    }
}
