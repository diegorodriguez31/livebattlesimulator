package main.java.fr.enseeiht.lbs.model.game_object.unit.visitor;

import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.FireDebuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.FreezeDebuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.SlowDebuff;

public interface BuffVisitor {
    void visit(FireDebuff buff);

    void visit(FreezeDebuff buff);

    void visit(SlowDebuff buff);
}
