package main.java.fr.enseeiht.lbs.model.game_object.unit.visitor;

import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.FireDebuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.FreezeDebuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.SlowDebuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.PeasantGroupBuff;

public class BasicDotVisitor implements BuffVisitor {

    private static final double FIRE_DEBUFF_DAMAGE = 20.0;
    private static final double FREEZE_DEBUFF_DAMAGE = 10.0;

    protected long deltaTime;
    protected Unit unit;

    public BasicDotVisitor(long deltaTime, Unit unit) {
        this.deltaTime = deltaTime;
        this.unit = unit;
    }

    @Override
    public void visit(FireDebuff buff) {
        unit.receiveDamage(FIRE_DEBUFF_DAMAGE*deltaTime/1000);
    }

    @Override
    public void visit(FreezeDebuff buff) {
        unit.receiveDamage(FREEZE_DEBUFF_DAMAGE*deltaTime/1000);
    }

    @Override
    public void visit(SlowDebuff buff) {
        // do nothing
    }

    @Override
    public void visit(PeasantGroupBuff buff) {
        // do nothing
    }
}
