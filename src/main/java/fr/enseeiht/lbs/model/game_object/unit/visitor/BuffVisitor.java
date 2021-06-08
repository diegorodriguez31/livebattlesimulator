package main.java.fr.enseeiht.lbs.model.game_object.unit.visitor;

import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.*;

/**
 * Spécifie les comportments à définir en fonction de chaque type de Buff
 */
public interface BuffVisitor {
    void visit(FireDebuff buff);

    void visit(PoisonDebuff buff);

    void visit(SlowDebuff buff);

    void visit(PeasantGroupBuff buff);

    void visit(BreakArmorDebuff buff);

    void visit(StunDebuff buff);
}
