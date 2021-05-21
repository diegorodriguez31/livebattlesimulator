package main.java.fr.enseeiht.lbs.gameObject.unit.visitors;

import main.java.fr.enseeiht.lbs.gameObject.unit.Unit;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.FireDebuff;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.FreezeDebuff;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.SlowDebuff;

public class TicBuffVisitor implements IBuffVisitor{

    private static final double FIRE_DEBUFF_DAMAGE = 20.0;
    private static final double FREEZE_DEBUFF_DAMAGE = 10.0;

    long deltaTime;
    Unit unit;

    public TicBuffVisitor(long deltaTime, Unit unit) {
        this.deltaTime = deltaTime;
        this.unit = unit;
    }

    @Override
    public void visit(FireDebuff buff) {
        unit.receiveDamage(FIRE_DEBUFF_DAMAGE*deltaTime/1000);
    }

    @Override
    public void visit(FreezeDebuff buff) {
        unit.receiveDamage(FREEZE_DEBUFF_DAMAGE*deltaTime/1000);
    }

    @Override
    public void visit(SlowDebuff buff) {

    }
}
