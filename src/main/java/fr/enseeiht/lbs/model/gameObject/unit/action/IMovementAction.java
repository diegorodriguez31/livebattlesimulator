package main.java.fr.enseeiht.lbs.model.gameObject.unit.action;

import main.java.fr.enseeiht.lbs.utils.Vector2;

public interface IMovementAction extends Action {
    void setTarget(Vector2 target);
}
