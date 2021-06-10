package main.java.fr.enseeiht.lbs.model.game.object.unit.action;

import main.java.fr.enseeiht.lbs.model.game.object.Statistic;
import main.java.fr.enseeiht.lbs.model.game.object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game.object.unit.buff.FireDebuff;
import main.java.fr.enseeiht.lbs.model.game.object.unit.soldier.standard.unit.Archer;

/**
 * Actions effectuées lors d'une attaque d'un archer
 */
public class ArcherAttack extends AttackAction {

    /**
     * Montant des dégâts de tics de feu
     */
    private static final double FIRE_TIC_DAMAGE = 20.0;
    private static final long FIRE_TIC_DURATION = 3000;

    /**
     * Indice de tir spécial (1 tir sur 3)
     */
    private static final int ARROWS_BEFORE_SPECIAL_SHOT = 3;

    public ArcherAttack(Unit attaquant) {
        super(attaquant);
    }

    /**
     * Pendant l'attaque de l'archer
     * chaque tir fait des dégâts à la cible
     * 1 tir sur 3, l'archer envoit une flèche de feu
     */
    @Override
    public void execute(long deltaTime) {
        if (target == null||attaquant==null) {
            return;
        }

        if (attaquant.attackSuccess()) {
            target.receiveDamage(attaquant.getStats().getStatisticValue(Statistic.DAMAGE));

            FireDebuff fireDebuff = new FireDebuff(FIRE_TIC_DAMAGE,FIRE_TIC_DURATION);
            if (((Archer) attaquant).getNbArrowsShot() == ARROWS_BEFORE_SPECIAL_SHOT) {
                target.addBuffs(fireDebuff);
            }

            ((Archer) attaquant).updateNbArrowsShot();
        }
    }
}
