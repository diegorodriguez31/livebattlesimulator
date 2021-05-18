package main.java.fr.enseeiht.lbs.gameObject.unit.visitors;

import main.java.fr.enseeiht.lbs.gameObject.unit.Unit;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.FireDebuff;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.FreezeDebuff;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.SlowDebuff;

public class UBuffVisitor implements IBuffVisitor{

    private static final int FIRE_DEBUFF_DAMAGE = 20;
    private static final int FREEZE_DEBUFF_DAMAGE = 10;

    float deltaTime;
    Unit unit;

    public UBuffVisitor(float deltaTime, Unit unit) {
        this.deltaTime = deltaTime;
        this.unit = unit;
    }

    @Override
    public void visit(FireDebuff buff) {
        unit.receiveDamage(FIRE_DEBUFF_DAMAGE*deltaTime);
    }

    @Override
    public void visit(FreezeDebuff buff) {
        unit.receiveDamage(FREEZE_DEBUFF_DAMAGE*deltaTime);
    }

    @Override
    public void visit(SlowDebuff buff) {

    }
}
