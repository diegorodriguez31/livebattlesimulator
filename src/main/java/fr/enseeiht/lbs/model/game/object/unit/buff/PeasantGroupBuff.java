package main.java.fr.enseeiht.lbs.model.game.object.unit.buff;

import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.BuffVisitor;

/**
 * Buff de rapidité de déplacement et d'attaque
 */
public class PeasantGroupBuff implements Buff {

    /**
     * Mulitplicateur de vitesse de déplacement
     */
    private double speedMultiplier;

    /**
     * Mulitplicateur de réduction du temps entre chaque attaque
     */
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
