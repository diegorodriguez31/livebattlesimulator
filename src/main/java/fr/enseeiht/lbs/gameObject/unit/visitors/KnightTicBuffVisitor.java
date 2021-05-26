package main.java.fr.enseeiht.lbs.gameObject.unit.visitors;

import main.java.fr.enseeiht.lbs.gameObject.unit.Knight;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.FireDebuff;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.FreezeDebuff;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.SlowDebuff;

public class KnightTicBuffVisitor extends TicBuffVisitor{

    public KnightTicBuffVisitor(long deltaTime, Knight unit) {
        super(deltaTime, unit);
    }

    @Override
    public void visit(FireDebuff buff) {
        if (!((Knight)unit).hasArmor()){
            super.visit(buff);
        }
    }

    @Override
    public void visit(FreezeDebuff buff) {
        if (!((Knight)unit).hasArmor()){
            super.visit(buff);
        }
    }

    @Override
    public void visit(SlowDebuff buff) {
        if (!((Knight)unit).hasArmor()){
            super.visit(buff);
        }
    }

}
