package main.java.fr.enseeiht.lbs.model.gameObject.unit.ai;

import main.java.fr.enseeiht.lbs.model.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.model.gameObject.Statistic;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.Unit;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.action.Action;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.action.IAttackAction;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.action.IMovementAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static main.java.fr.enseeiht.lbs.LiveBattleSimulator.SUPER_PIXEL_SIZE;

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
        if (target==null || target.isDead()){
            target = context.findClosestEnemy(self);
            attack.setTarget(target);
        }
        if (target.getPosition().sub(self.getPosition()).sqrSize()<self.getStats().getStatisticValue(Statistic.RANGE)*SUPER_PIXEL_SIZE){
            if (cooldown < 0){
                cooldown = (long) self.getStats().getStatisticValue(Statistic.COOLDOWN)*1000;
                return Arrays.asList(attack);
            }else{
                cooldown -= deltaTime;
                return new ArrayList<>();
            }
        }else{
            movement.setTarget(target.getPosition());
            return Arrays.asList(movement);
        }
    }
}
