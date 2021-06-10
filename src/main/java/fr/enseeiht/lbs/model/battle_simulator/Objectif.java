package main.java.fr.enseeiht.lbs.model.battle_simulator;

/**
 * Interface des objectifs de bataille.
 */
public interface Objectif {

    /**
     * Renvoie le gagnant de la bataille
     * @param context la bataille a tester
     * @return le gagnant (null si la bataille n'est pas terminé)
     */
    Army getWinner(Battle context);
}
