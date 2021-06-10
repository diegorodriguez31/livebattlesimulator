package main.java.fr.enseeiht.lbs.model.game.object.unit.visitor;

import main.java.fr.enseeiht.lbs.model.game.object.unit.buff.*;

/**
<<<<<<< HEAD:src/main/java/fr/enseeiht/lbs/model/game_object/unit/visitor/BuffVisitor.java
 * Spécifie les comportements à définir en fonction de chaque type de Buff
=======
 * Spécifie les comportments à définir
 * en fonction de chaque type de Buff appliqué à une unité
>>>>>>> origin/develop:src/main/java/fr/enseeiht/lbs/model/game/object/unit/visitor/BuffVisitor.java
 */
public interface BuffVisitor {
    void visit(FireDebuff buff);

    void visit(PoisonDebuff buff);

    void visit(SlowDebuff buff);

    void visit(PeasantGroupBuff buff);

    void visit(BreakArmorDebuff buff);

    void visit(StunDebuff buff);

    void visit(TimedBuff buff);
}
