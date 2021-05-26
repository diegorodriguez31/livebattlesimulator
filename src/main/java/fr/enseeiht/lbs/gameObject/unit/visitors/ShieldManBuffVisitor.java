package main.java.fr.enseeiht.lbs.gameObject.unit.visitors;

import main.java.fr.enseeiht.lbs.gameObject.Stats;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.FreezeDebuff;

public class ShieldManBuffVisitor extends StatModifierBuffVisitor {

    public ShieldManBuffVisitor(Stats stats) {
        super(stats);
    }

    @Override
    public void visit(FreezeDebuff buff) {}
}
