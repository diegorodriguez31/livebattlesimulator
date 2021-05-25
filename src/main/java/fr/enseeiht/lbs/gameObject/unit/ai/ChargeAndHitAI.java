package main.java.fr.enseeiht.lbs.gameObject.unit.ai;

import main.java.fr.enseeiht.lbs.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.gameObject.Statistic;
import main.java.fr.enseeiht.lbs.gameObject.unit.Unit;
import main.java.fr.enseeiht.lbs.gameObject.unit.action.Action;
import main.java.fr.enseeiht.lbs.gameObject.unit.action.IAttackAction;
import main.java.fr.enseeiht.lbs.gameObject.unit.action.IMovementAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChargeAndHitAI implements AI {

    private long cdr;

    private IAttackAction attack;
    private IMovementAction movement;

    private Unit target;

    public ChargeAndHitAI(IAttackAction attack, IMovementAction movement) {
        this.attack = attack;
        this.movement = movement;
        cdr=0;
    }

    @Override
    public List<Action> getActions(Unit self, Battle context, long deltaTime) {
        if (target==null || target.isDead()){
            target = context.findClosestEnemy(self);
            attack.setTarget(target);
        }
        if (target.getPosition().sub(self.getPosition()).sqrSize()<self.getStats().getStatisticValue(Statistic.REACH)){
            if (cdr<0){
                cdr = (long) self.getStats().getStatisticValue(Statistic.COOLDOWN);
                return Arrays.asList(attack);
            }else{
                cdr-=deltaTime;
                return new ArrayList<>();
            }
        }else{
            movement.setTarget(target.getPosition());
            return Arrays.asList(movement);
        }
    }
}
