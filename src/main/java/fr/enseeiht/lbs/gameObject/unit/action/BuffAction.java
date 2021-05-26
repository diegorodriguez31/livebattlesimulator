package main.java.fr.enseeiht.lbs.gameObject.unit.action;

import main.java.fr.enseeiht.lbs.gameObject.Statistic;
import main.java.fr.enseeiht.lbs.gameObject.unit.Unit;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.Buff;

public class BuffAction implements IAttackAction {
    Unit target;
    Buff buff;

    public BuffAction(Buff buff) {
        this.buff = buff;
        this.target = null;
    }

    @Override
    public void execute(long deltaTime) {
        if (target == null||buff==null) {
            return;
        }
        target.addBuffs(buff);
        System.out.println("DEBUFF !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @Override
    public void setTarget(Unit unit) {
        target = unit;
    }
}
