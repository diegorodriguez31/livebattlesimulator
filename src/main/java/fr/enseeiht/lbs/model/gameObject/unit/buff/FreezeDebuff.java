package main.java.fr.enseeiht.lbs.model.gameObject.unit.buff;

import main.java.fr.enseeiht.lbs.model.gameObject.unit.visitors.IBuffVisitor;

public class FreezeDebuff implements Buff {

    private static final double TIC_DAMAGE = 5;

    @Override
    public void accept(IBuffVisitor visitor) {
        visitor.visit(this);
    }

}
