package main.java.fr.enseeiht.lbs.model.game.object.unit.ai;

import main.java.fr.enseeiht.lbs.model.battle.simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.Action;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.IAttackAction;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.IMovementAction;
import main.java.fr.enseeiht.lbs.model.game.object.Statistic;
import main.java.fr.enseeiht.lbs.model.game.object.unit.Unit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChargeAndHitAI implements AI {

    private long cooldown;
    private IAttackAction attack;
    private IMovementAction movement;
    private Unit target;

    public ChargeAndHitAI(IAttackAction attack, IMovementAction movement) {
        this.attack = attack;
        this.movement = movement;
        cooldown = 0;
    }

    @Override
    public List<Action> getActions(Unit self, Battle context, long deltaTime) {
        if (target == null || target.isDead()) {
            target = context.findClosestEnemy(self);
            attack.setTarget(target);
        }
        if (target == null) {
            return new ArrayList<>();
        }
        if (target.getPosition().sub(self.getPosition()).size() < self.getStats().getStatisticValue(Statistic.RANGE)) {
            if (cooldown < 0) {
                cooldown = (long) self.getStats().getStatisticValue(Statistic.COOLDOWN) * 1000;
                return Arrays.asList(attack);
            } else {
                cooldown -= deltaTime;
                return new ArrayList<>();
            }
        } else {
            movement.setTarget(target.getPosition());
            return Arrays.asList(movement);
        }
    }
}
