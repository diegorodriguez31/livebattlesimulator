package main.java.fr.enseeiht.lbs.model.gameObject.unit.visitor.statModifierVisitor;

import main.java.fr.enseeiht.lbs.model.gameObject.Stats;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.buff.FreezeDebuff;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.visitor.BasicStatModifierBuffVisitor;

public class ShieldManBuffVisitor extends BasicStatModifierBuffVisitor {

    public ShieldManBuffVisitor(Stats stats) {
        super(stats);
    }

    @Override
    public void visit(FreezeDebuff buff) {
        // do nothing
    }
}
