package main.java.fr.enseeiht.lbs.model.game_object.unit.action;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Army;
import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game_object.Entity;
import main.java.fr.enseeiht.lbs.model.game_object.EntityFactory;
import main.java.fr.enseeiht.lbs.model.game_object.Statistic;
import main.java.fr.enseeiht.lbs.model.game_object.creators.AlienCreator;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.standard_unit.Alien;

public class AlienAttack extends AttackAction {

    private static final int SHOTS_BEFORE_SPECIAL_SHOT = 3;

    public AlienAttack(Unit attaquant) {
        super(attaquant);
    }

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
