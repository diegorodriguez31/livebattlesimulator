package main.java.fr.enseeiht.lbs.model.game_object.unit.buff;

import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.BuffVisitor;

/**
 * Buff de rapidité de déplacement
 */
public class SpeedBuff implements Buff {

    /**
     * Le buff multiplie la vitesse par 2
     */
    private static final double MULTIPLIER = 2;

    @Override
    public void accept(BuffVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Récupère l'accélération du buff de vitesse
     * @return le multiplicateur de vitesse
     */
    public double getSpeed() { return MULTIPLIER; }
}
