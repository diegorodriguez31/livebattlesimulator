package main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.dotVisitor;

import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.*;

public class ImmuneTicVisitor extends BasicTicVisitor {

    public ImmuneTicVisitor(long deltaTime, Unit unit) {
        super(deltaTime, unit);
    }

    @Override
    public void visit(FireDebuff buff) {
        // do nothing
    }

    @Override
    public void visit(FreezeDebuff buff) {
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
