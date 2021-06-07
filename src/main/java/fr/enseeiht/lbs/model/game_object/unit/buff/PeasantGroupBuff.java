package main.java.fr.enseeiht.lbs.model.game_object.unit.buff;

import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.BuffVisitor;

/**
 * Buff de rapidité de déplacement
 */
public class PeasantGroupBuff implements Buff {

    /**
     * Le buff multiplié par 2
     */
    private static final double MULTIPLIER = 2;

    /**
     * Le buff divisé par 2
     */
    private static final double REDUCER = 0.5;


    @Override
    public void accept(BuffVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Récupère l'accélération du buff de vitesse
     * @return le multiplicateur de vitesse
     */
    public double getSpeedMultiplier() {
        return MULTIPLIER; }

    public double getCooldownReducer() { return REDUCER; }
}
