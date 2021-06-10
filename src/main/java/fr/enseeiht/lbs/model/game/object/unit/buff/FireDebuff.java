package main.java.fr.enseeiht.lbs.model.game.object.unit.buff;

import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.BuffVisitor;

/**
 * Débuff de feu pouvant être appliqué à une unité via un visiteur
 */
public class FireDebuff implements Buff {

    /**
     * Montant des dégâts de feu par secondes
     */
    private double ticDamage;

    public FireDebuff(double ticDamage) {
        this.ticDamage = ticDamage;
    }

    @Override
    public void accept(BuffVisitor visitor) {
        visitor.visit(this);
    }

    public double getTicDamage() {
        return ticDamage;
    }
}
