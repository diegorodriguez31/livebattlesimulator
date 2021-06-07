package main.java.fr.enseeiht.lbs.model.game_object.unit.buff;

import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.BuffVisitor;

/**
 * Buff de rapidité de déplacement
 */
public class PeasantGroupBuff implements Buff {

    private double speedMultiplier;
    private double cooldownReducer;

    public PeasantGroupBuff(double speedMultiplier, double cooldownReducer) {
        this.speedMultiplier = speedMultiplier;
        this.cooldownReducer = cooldownReducer;
    }

    @Override
    public void accept(BuffVisitor visitor) {
        visitor.visit(this);
    }

    public double getSpeedMultiplier() {
        return speedMultiplier;
    }

    public double getCooldownReducer() {
        return cooldownReducer;
    }
}
