package main.java.fr.enseeiht.lbs.gameObject.unit.buff;

import main.java.fr.enseeiht.lbs.gameObject.unit.visitors.BuffVisitor;
import main.java.fr.enseeiht.lbs.gameObject.unit.Unit;

public class SlowDebuff implements Buff {

    private final double SLOW_AMOUNT = 0.5;

    public SlowDebuff() {}

    @Override
    public void accept(BuffVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void update(Unit unit, double deltaTime) {
    }

    public double getSlow() {
        return SLOW_AMOUNT;
    }
}
