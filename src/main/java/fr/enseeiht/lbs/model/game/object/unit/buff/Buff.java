package main.java.fr.enseeiht.lbs.model.game.object.unit.buff;

import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.BuffVisitor;

/**
 * Un Buff est un élément pouvant être appliqué à une unité via un visiteur.
 * Il peut modifier ses statistiques ou appliquer des dégâts à chaque seconde.
 */
public interface Buff {
    void accept(BuffVisitor visitor);

    /**
     * Savoir si la durée du buff a expiré
     * @return vrai si le buff ne doit plus être actif et faux sinon
     */
    default boolean isFinished(){
        return false;
    };
}
