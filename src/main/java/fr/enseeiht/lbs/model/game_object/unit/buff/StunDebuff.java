package main.java.fr.enseeiht.lbs.model.game_object.unit.buff;

import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.BuffVisitor;

public class StunDebuff implements Buff {

    private double seconds;

    public StunDebuff(double seconds) {
        this.seconds = seconds;
    }

    @Override
    public void accept(BuffVisitor visitor) {
        visitor.visit(this);
    }

    public double getDuration() {
        return seconds;
    }
}
