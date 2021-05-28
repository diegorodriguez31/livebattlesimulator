package main.java.fr.enseeiht.lbs.model.gameObject.unit.action;

import main.java.fr.enseeiht.lbs.model.gameObject.unit.Unit;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.buff.Buff;

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
    }

    @Override
    public void setTarget(Unit unit) {
        target = unit;
    }
}
