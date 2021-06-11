package main.java.fr.enseeiht.lbs.model.game.object.unit.action;

import main.java.fr.enseeiht.lbs.utils.Vector2;

/**
 * Interface qui decris le une action de deplacement vers une cible
 */
public interface IMovementAction extends Action {
    void setTarget(Vector2 target);
}
