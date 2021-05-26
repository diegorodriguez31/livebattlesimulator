package main.java.fr.enseeiht.lbs.gameObject.unit.visitors;

import main.java.fr.enseeiht.lbs.gameObject.unit.buff.FireDebuff;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.FreezeDebuff;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.SlowDebuff;

public interface BuffVisitor {
    void visit(FireDebuff buff);

    void visit(FreezeDebuff buff);

    void visit(SlowDebuff buff);
}
