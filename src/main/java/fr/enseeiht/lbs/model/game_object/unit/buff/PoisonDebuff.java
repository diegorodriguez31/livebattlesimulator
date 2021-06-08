package main.java.fr.enseeiht.lbs.model.game_object.unit.buff;

import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.BuffVisitor;

public class PoisonDebuff implements Buff {

    private double ticDamage;
    private double cooldownReducer;

    public PoisonDebuff(double ticDamage, double cooldownReducer) {
        this.ticDamage = ticDamage;
        this.cooldownReducer = cooldownReducer;
    }

    @Override
    public void accept(BuffVisitor visitor) {
        visitor.visit(this);
    }

    public double getTicDamage() {
        return ticDamage;
    }

    public double getCooldownReducer() {
        return cooldownReducer;
    }

    @Override
    public boolean equals(Object buff) {
        return buff instanceof PoisonDebuff;
    }
}
