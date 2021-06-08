package main.java.fr.enseeiht.lbs.model.game_object.unit.buff;

import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.BuffVisitor;

/**
 * A debuff that can be applied to a unit via a visitor
 */
public class BreakArmorDebuff implements Buff {

    @Override
    public void accept(BuffVisitor visitor) {
        visitor.visit(this);
    }
}
