package main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.statModifierVisitor;

import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.PoisonDebuff;

public class TrollStatModifierVisitor extends BasicStatModifierBuffVisitor {

    public TrollStatModifierVisitor(Stats stats, Unit unit) {
        super(stats, unit);
    }

    @Override
    public void visit(PoisonDebuff buff) {
        // do nothing
    }
}
