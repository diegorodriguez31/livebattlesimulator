package main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.statModifierVisitor;

import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.*;

public class ImmuneStatModifierVisitor extends BasicStatModifierBuffVisitor {

    public ImmuneStatModifierVisitor(Stats stats, Unit unit) {
        super(stats, unit);
    }

    @Override
    public void visit(FireDebuff buff) {
        // do nothing
    }

    @Override
    public void visit(PoisonDebuff buff) {
        // do nothing
    }

    @Override
    public void visit(SlowDebuff buff) {
        // do nothing
    }

    @Override
    public void visit(PeasantGroupBuff buff) {
        // do nothing
    }

    @Override
    public void visit(BreakArmorDebuff buff) {
        // do nothing
    }

    @Override
    public void visit(StunDebuff buff) {
        // do nothing
    }
}
