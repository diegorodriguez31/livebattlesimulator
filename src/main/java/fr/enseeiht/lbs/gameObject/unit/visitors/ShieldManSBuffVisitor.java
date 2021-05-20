package main.java.fr.enseeiht.lbs.gameObject.unit.visitors;

import main.java.fr.enseeiht.lbs.gameObject.Stats;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.FreezeDebuff;

public class ShieldManSBuffVisitor extends StatModifierBuffVisitor {

    public ShieldManSBuffVisitor(Stats stats) {
        super(stats);
    }

    @Override
    public void visit(FreezeDebuff buff) {}
}
