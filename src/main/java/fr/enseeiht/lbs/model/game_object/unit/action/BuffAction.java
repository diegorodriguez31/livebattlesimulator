package main.java.fr.enseeiht.lbs.model.game_object.unit.action;

import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.Buff;

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
