package main.java.fr.enseeiht.lbs.model.game.object.unit.buff;

import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.BuffVisitor;

/**
 * Débuff de ralentissement de mouvement
 * pouvant être appliqué à une unité via un visiteur
 */
public class SlowDebuff extends TimedBuff {

    /**
     * Multiplicateur de réduction de vitesse de déplacement
     */
    private double slowMultiplier;

    public SlowDebuff(double slowMultiplier, long duration) {
        super(duration);
        this.slowMultiplier = slowMultiplier;
    }

    @Override
    public void accept(BuffVisitor visitor) {
        super.accept(visitor);
        visitor.visit(this);
    }

    public double getSlowMultiplier() {
        return slowMultiplier;
    }
}
