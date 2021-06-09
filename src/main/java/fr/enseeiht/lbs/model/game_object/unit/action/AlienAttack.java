package main.java.fr.enseeiht.lbs.model.game_object.unit.action;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Army;
import main.java.fr.enseeiht.lbs.model.game_object.Entity;
import main.java.fr.enseeiht.lbs.model.game_object.EntityFactory;
import main.java.fr.enseeiht.lbs.model.game_object.Statistic;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.standard_unit.Alien;

/**
 * Actions effectuées lors d'une attaque d'alien
 */
public class AlienAttack extends AttackAction {

    /**
     * Indice de tir spécial (1 tir sur 3)
     */
    private static final int SHOTS_BEFORE_SPECIAL_SHOT = 3;

    public AlienAttack(Unit attaquant) {
        super(attaquant);
    }

    /**
     * Pendant l'attaque de l'alien
     * 1 tir normal fait des dégâts à la cible
     * 1 tir sur 3, la cible est transformée en alien allié
     */
    @Override
    public void execute(long deltaTime) {
        if (target == null||attaquant==null) {
            return;
        }

        if (((Alien) attaquant).getNbShotDone() == SHOTS_BEFORE_SPECIAL_SHOT) {
            Army alienArmy = attaquant.getTeam();
            Entity entity = EntityFactory.createEntity("Alien", target.getPosition());
            target.kill();
            alienArmy.addUnit((Unit) entity);
            entity.setReady();

        } else {
            if (attaquant.attackSuccess()) {
                target.receiveDamage(attaquant.getStats().getStatisticValue(Statistic.DAMAGE));
            }
        }

        ((Alien) attaquant).updateNbShotDone();
    }

    @Override
    public void setTarget(Unit unit) {
        target = unit;
    }
}
