package main.java.fr.enseeiht.lbs.gameObject.unit.buff;

import main.java.fr.enseeiht.lbs.gameObject.unit.visitors.BuffVisitor;
import main.java.fr.enseeiht.lbs.gameObject.unit.Unit;

public class FreezeDebuff implements Buff {

    private static final double TIC_DAMAGE = 5;

    @Override
    public void accept(BuffVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void update(Unit unit, double deltaTime) {
        unit.receiveDamage(TIC_DAMAGE * deltaTime);
    }
}
