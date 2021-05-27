package main.java.fr.enseeiht.lbs.gameObject.unit.buff;

import main.java.fr.enseeiht.lbs.gameObject.unit.visitor.BuffVisitor;

public class FireDebuff implements Buff {

    private static final double TIC_DAMAGE = 20;

    @Override
    public void accept(BuffVisitor visitor) {
        visitor.visit(this);
    }

}
