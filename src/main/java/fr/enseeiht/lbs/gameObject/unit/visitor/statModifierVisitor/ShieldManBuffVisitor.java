package main.java.fr.enseeiht.lbs.gameObject.unit.visitor.statModifierVisitor;

import main.java.fr.enseeiht.lbs.gameObject.Stats;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.FreezeDebuff;
import main.java.fr.enseeiht.lbs.gameObject.unit.visitor.BasicStatModifierBuffVisitor;

public class ShieldManBuffVisitor extends BasicStatModifierBuffVisitor {

    public ShieldManBuffVisitor(Stats stats) {
        super(stats);
    }

    @Override
    public void visit(FreezeDebuff buff) {}
}
