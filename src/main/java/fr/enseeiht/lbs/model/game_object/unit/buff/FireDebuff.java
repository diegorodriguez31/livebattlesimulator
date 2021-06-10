package main.java.fr.enseeiht.lbs.model.game_object.unit.buff;

import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.BuffVisitor;

/**
 * Débuff de feu pouvant être appliqué à une unité via un visiteur
 */
public class FireDebuff extends TimedBuff {

    /**
     * Montant des dégâts de feu par secondes
     */
    private double ticDamage;

    /**
     * Met en place la durée et les dégâts du debuff de feu
     * @param ticDamage dégâts par seconde
     * @param fireDuration durée du buff
     */
    public FireDebuff(double ticDamage, long fireDuration) {
        super(fireDuration);
        this.ticDamage = ticDamage;
    }

    @Override
    public void accept(BuffVisitor visitor) {
        super.accept(visitor);
        visitor.visit(this);
    }

    public double getTicDamage() {
        return ticDamage;
    }
}
