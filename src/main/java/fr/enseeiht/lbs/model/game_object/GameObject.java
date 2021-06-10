package main.java.fr.enseeiht.lbs.model.game_object;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;

/**
 * Concept inspiré du moteur de jeu Unity.
 * Élément présent sur le terrain lors de la simulation de bataille ou qui intéragit avec.
 */
public abstract class GameObject {

    /**
     * Concept inspiré du moteur de jeu Unity
     * Prépare l'élément au cycle de vie de l'utilisateur.
     */
    public void setReady(){
        Battle.getInstance().addGameObject(this);
    }

    /**
     * Retire l'élément d'une bataille.
     */
    public void removeFromBattle(){
        Battle.getInstance().removeGameObject(this);
    }

    /**
     * Concepts inspirés du moteur de jeu Unity
     * start : une action particulière à la création de l'élément
     * update : une action récurrente assimilable au comportement de l'élément
     * end : une action particulière à la destruction de l'élément
     *
     * @param context élément (ici la bataille) contenant toutes les informations sur les éléments présents lors de la simulation
     */
    abstract public void start(Battle context);
    abstract public void update(Battle context, long deltaTime);
    abstract public void end(Battle context);
}
