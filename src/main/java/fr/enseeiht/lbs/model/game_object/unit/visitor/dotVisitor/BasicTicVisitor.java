package main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.dotVisitor;

import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.*;
import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.BuffVisitor;

public class BasicTicVisitor implements BuffVisitor {

    protected long deltaTime;
    protected Unit unit;

    public BasicTicVisitor(long deltaTime, Unit unit) {
        this.deltaTime = deltaTime;
        this.unit = unit;
    }

    @Override
    public void visit(FireDebuff buff) {
        unit.receiveDamage(buff.getTicDamage() * deltaTime/1000);
    }

    @Override
    public void visit(PoisonDebuff buff) {
        unit.receiveDamage(buff.getTicDamage() * deltaTime/1000);
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
