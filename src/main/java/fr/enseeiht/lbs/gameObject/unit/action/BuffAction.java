package main.java.fr.enseeiht.lbs.gameObject.unit.action;

import main.java.fr.enseeiht.lbs.gameObject.unit.Unit;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.Buff;

public class BuffAction implements Action {
    Unit victime;
    Buff buff;

    public BuffAction(Unit victime, Buff buff) {
        this.buff = buff;
        this.victime = victime;
    }

    @Override
    public void execute() {
        System.out.println("execute debuff");
        victime.addBuffs(buff);
    }
}
