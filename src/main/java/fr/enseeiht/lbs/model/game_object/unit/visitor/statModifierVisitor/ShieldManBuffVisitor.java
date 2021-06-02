package main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.statModifierVisitor;

import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.FreezeDebuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.BasicStatModifierBuffVisitor;

public class ShieldManBuffVisitor extends BasicStatModifierBuffVisitor {

    public ShieldManBuffVisitor(Stats stats) {
        super(stats);
    }

    @Override
    public void visit(FreezeDebuff buff) {
        // do nothing
    }
}
