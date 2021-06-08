package main.java.fr.enseeiht.lbs.model.game_object.unit.visitor;

import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.*;

public interface BuffVisitor {
    void visit(FireDebuff buff);

    void visit(PoisonDebuff buff);

    void visit(SlowDebuff buff);

    void visit(PeasantGroupBuff buff);

    void visit(BreakArmorDebuff buff);

    void visit(StunDebuff buff);
}
