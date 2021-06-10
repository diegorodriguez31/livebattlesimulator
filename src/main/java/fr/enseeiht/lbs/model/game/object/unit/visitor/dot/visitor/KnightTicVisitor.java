package main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.dot.visitor;

import main.java.fr.enseeiht.lbs.model.game.object.unit.buff.FireDebuff;
import main.java.fr.enseeiht.lbs.model.game.object.unit.buff.PoisonDebuff;
import main.java.fr.enseeiht.lbs.model.game.object.unit.soldier.armored.unit.ArmoredUnit;
import main.java.fr.enseeiht.lbs.model.game.object.unit.soldier.armored.unit.Knight;

/**
 * Comportements du Knight face aux tics de buffs
 */
public class KnightTicVisitor extends BasicTicVisitor {

    public KnightTicVisitor(long deltaTime, Knight unit) {
        super(deltaTime, unit);
    }

    /**
     * On ignore le buff de feu si on a de l'armure
     */
    @Override
    public void visit(FireDebuff buff) {
        if (!((ArmoredUnit)unit).hasArmor()){
            super.visit(buff);
        }
    }

    /**
     * On ignore le buff de poison si on a de l'armure
     */
    @Override
    public void visit(PoisonDebuff buff) {
        if (!((ArmoredUnit)unit).hasArmor()){
            super.visit(buff);
        }
    }
}
