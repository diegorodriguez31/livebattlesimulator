package main.java.fr.enseeiht.lbs.model.game_object.unit.buff;

import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.BuffVisitor;

public class SlowDebuff implements Buff {

    private final double SLOW_AMOUNT = 0.5;

    public SlowDebuff() {}

    @Override
    public void accept(BuffVisitor visitor) {
        visitor.visit(this);
    }

    public double getSlow() {
        return SLOW_AMOUNT;
    }
}
