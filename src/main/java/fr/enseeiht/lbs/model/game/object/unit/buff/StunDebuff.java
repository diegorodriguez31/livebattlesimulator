package main.java.fr.enseeiht.lbs.model.game.object.unit.buff;

import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.BuffVisitor;

/**
 * Débuff de ralentissement entre chaque attaque
 * pouvant être appliqué à une unité via un visiteur
 */
public class StunDebuff implements Buff {

    /**
     * Durée en seconde ajoutée entre chaque attaque
     */
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
