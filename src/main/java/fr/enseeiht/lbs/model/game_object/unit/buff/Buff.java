package main.java.fr.enseeiht.lbs.model.game_object.unit.buff;

import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.BuffVisitor;

/**
 * Un Buff est un élément pouvant être appliqué à une unité via un visiteur.
 * Il peut modifier ses statistiques ou appliquer des dégâts à chaque seconde.
 */
public interface Buff {
    void accept(BuffVisitor visitor);
}
