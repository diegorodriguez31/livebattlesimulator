package main.java.fr.enseeiht.lbs.gameObject.unit.buff;

import main.java.fr.enseeiht.lbs.gameObject.unit.visitors.IBuffVisitor;

public class SlowDebuff implements Buff {

    private final double SLOW_AMOUNT = 0.5;

    public SlowDebuff() {}

    @Override
    public void accept(IBuffVisitor visitor) {
        visitor.visit(this);
    }

    public double getSlow() {
        return SLOW_AMOUNT;
    }
}
