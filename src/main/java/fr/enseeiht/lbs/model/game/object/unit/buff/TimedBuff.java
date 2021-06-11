package main.java.fr.enseeiht.lbs.model.game.object.unit.buff;

import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.BuffVisitor;

/**
 * Un buff a une certaine durée, ce comportement abstrait permet de gérer la durée
 */
public abstract class TimedBuff implements Buff{
    private long duration;

    /**
     * Buff défini avec une durée
     * @param duration durée du buff en millisecondes
     */
    public TimedBuff(long duration) {
        this.duration = duration;
    }

    @Override
    public void accept(BuffVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Vérifie si le buff est arrivé à expiration
     * @return vrai si le buff est fini et faux sinon
     */
    @Override
    public boolean isFinished() {
        return duration <= 0;
    }

    /**
     * Décrémente la durée de x millisecondes
     * @param dec les millisecondes à décrémenter
     */
    public void decDuration(long dec) {
        duration-=dec;
    }
}
