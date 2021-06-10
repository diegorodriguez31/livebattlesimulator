package main.java.fr.enseeiht.lbs.model.game.object.unit.buff;

import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.BuffVisitor;

/**
 * Débuff de poison pouvant être appliqué à une unité via un visiteur
 */
public class PoisonDebuff extends TimedBuff {

    /**
     * Montant des dégâts de poison par secondes
     */
    private double ticDamage;

    /**
     * Mulitplicateur d'augmentation du temps entre chaque attaque
     */
    private double cooldownIncreaseMulitplier;

    /**
     * Met en place la durée et les dégâts et la baisse de cooldown du debuff de poison
     * @param ticDamage dégâts par seconde
     * @param cooldownIncreaseMulitplier multiplieur qui baisse le cooldown
     * @param poisonDuration durée du poison
     */
    public PoisonDebuff(double ticDamage, double cooldownIncreaseMulitplier, long poisonDuration) {
        super(poisonDuration);
        this.ticDamage = ticDamage;
        this.cooldownIncreaseMulitplier = cooldownIncreaseMulitplier;
    }

    @Override
    public void accept(BuffVisitor visitor) {
        super.accept(visitor);
        visitor.visit(this);
    }

    public double getTicDamage() {
        return ticDamage;
    }

    public double getCooldownIncreaseMulitplier() {
        return cooldownIncreaseMulitplier;
    }
}
