package main.java.fr.enseeiht.lbs.model.game_object.unit.buff;

import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.BuffVisitor;

public class BreakArmorDebuff implements Buff {

    @Override
    public void accept(BuffVisitor visitor) {
        visitor.visit(this);
    }
}
