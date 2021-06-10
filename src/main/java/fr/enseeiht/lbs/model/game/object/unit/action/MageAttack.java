package main.java.fr.enseeiht.lbs.model.game.object.unit.action;

import main.java.fr.enseeiht.lbs.model.battle.simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game.object.Statistic;
import main.java.fr.enseeiht.lbs.model.game.object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game.object.unit.buff.FireDebuff;
import main.java.fr.enseeiht.lbs.model.game.object.unit.buff.SlowDebuff;
import main.java.fr.enseeiht.lbs.model.game.object.unit.soldier.standard.unit.Mage;

import java.util.List;

/**
 * Actions effectuées lors d'une attaque de mage
 */
public class MageAttack extends AttackAction {

    /**
     * Montant des dégâts de tics de feu
     */
    private static final double FIRE_TIC_DAMAGE = 30.0;
    private static final long FIRE_TIC_DURATION = 5000;
    private static final long SPEED_BUFF_MULTIPLIER = 5;
    private static final long SPEED_BUFF_DURATION = 2000;

    /**
     * Indice d'attaque spécial (1 attaque sur 10)
     */
    private static final int ATTACKS_BEFORE_SPECIAL_ATTACK = 2;

    public MageAttack(Unit attaquant) {
        super(attaquant);
    }

    /**
     * Pendant l'attaque du mage
     *
     * Chaque attaque fait des dégâts à la cible
     * et applique un débuff de feu pendants 5 secondes
     *
     * 1 attaque sur 2,
     * le mage buff les alliés en vitesse de déplacement pendant 2 secondes
     * (seulement les alliés sans debuffs sur la vitesse)
     */
    @Override
    public void execute(long deltaTime) {
        if (target == null||attaquant==null) {
            return;
        }

        if (((Mage) attaquant).getNbAttacks() == ATTACKS_BEFORE_SPECIAL_ATTACK) {
            // buff allies speed
            List<Unit> allies = Battle.getInstance().findAllies(attaquant);
            for (Unit unit : allies) {
                unit.addBuffs(new SlowDebuff(SPEED_BUFF_MULTIPLIER, SPEED_BUFF_DURATION));
            }
        } else if (attaquant.attackSuccess()) {
            target.receiveDamage(attaquant.getStats().getStatisticValue(Statistic.DAMAGE));
            target.addBuffs(new FireDebuff(FIRE_TIC_DAMAGE, FIRE_TIC_DURATION));
        }
        ((Mage) attaquant).updateNbAttacks();
    }
}
