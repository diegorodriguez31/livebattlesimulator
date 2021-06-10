package main.java.fr.enseeiht.lbs.model.game.object.unit.buff;

import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.BuffVisitor;

/**
 * Débuff de poison pouvant être appliqué à une unité via un visiteur
 */
public class PoisonDebuff implements Buff {

    /**
     * Montant des dégâts de poison par secondes
     */
    private double ticDamage;

    /**
     * Mulitplicateur d'augmentation du temps entre chaque attaque
     */
    private double cooldownIncreaseMulitplier;

    public PoisonDebuff(double ticDamage, double cooldownIncreaseMulitplier) {
        this.ticDamage = ticDamage;
        this.cooldownIncreaseMulitplier = cooldownIncreaseMulitplier;
    }

    @Override
    public void accept(BuffVisitor visitor) {
        visitor.visit(this);
    }

    public double getTicDamage() {
        return ticDamage;
    }

    public double getCooldownIncreaseMulitplier() {
        return cooldownIncreaseMulitplier;
    }
}
