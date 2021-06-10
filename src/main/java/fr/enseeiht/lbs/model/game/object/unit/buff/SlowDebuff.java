package main.java.fr.enseeiht.lbs.model.game.object.unit.buff;

import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.BuffVisitor;

/**
 * Débuff de ralentissement de mouvement
 * pouvant être appliqué à une unité via un visiteur
 */
public class SlowDebuff implements Buff {

    /**
     * Multiplicateur de réduction de vitesse de déplacement
     */
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
