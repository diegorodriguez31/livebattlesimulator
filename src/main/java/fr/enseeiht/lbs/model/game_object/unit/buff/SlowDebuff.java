package main.java.fr.enseeiht.lbs.model.game_object.unit.buff;

import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.BuffVisitor;

public class SlowDebuff implements Buff {

    private double slowAmount;

    public SlowDebuff(double slowAmount) {
        this.slowAmount = slowAmount;
    }

    @Override
    public void accept(BuffVisitor visitor) {
        visitor.visit(this);
    }

    public double getSlowAmount() {
        return slowAmount;
    }
}
