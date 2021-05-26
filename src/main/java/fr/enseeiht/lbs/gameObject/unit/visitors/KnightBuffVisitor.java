package main.java.fr.enseeiht.lbs.gameObject.unit.visitors;

import main.java.fr.enseeiht.lbs.gameObject.Stats;
import main.java.fr.enseeiht.lbs.gameObject.unit.Knight;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.FireDebuff;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.FreezeDebuff;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.SlowDebuff;

public class KnightBuffVisitor extends StatModifierBuffVisitor {
    Knight target;

    public KnightBuffVisitor(Stats stats, Knight target) {
        super(stats);
        this.target = target;
    }

    @Override
    public void visit(FireDebuff buff) {
        if (!target.hasArmor()) {
            super.visit(buff);
        }
    }

    @Override
    public void visit(FreezeDebuff buff) {
        if (!target.hasArmor()) {
            super.visit(buff);
        }
    }

    @Override
    public void visit(SlowDebuff buff) {
        if (!target.hasArmor()) {
            super.visit(buff);
        }
    }

}
