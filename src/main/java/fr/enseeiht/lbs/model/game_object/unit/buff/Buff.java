package main.java.fr.enseeiht.lbs.model.game_object.unit.buff;

import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.BuffVisitor;

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
