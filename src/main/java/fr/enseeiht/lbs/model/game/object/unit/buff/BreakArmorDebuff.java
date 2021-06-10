package main.java.fr.enseeiht.lbs.model.game.object.unit.buff;

import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.BuffVisitor;

/**
 * Débuff de brissage d'armure pouvant être appliqué à une unité via un visiteur
 */
public class BreakArmorDebuff implements Buff {

    @Override
    public void accept(BuffVisitor visitor) {
        visitor.visit(this);
    }
}
