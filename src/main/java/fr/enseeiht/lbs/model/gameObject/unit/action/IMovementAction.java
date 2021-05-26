package main.java.fr.enseeiht.lbs.model.gameObject.unit.action;

import main.java.fr.enseeiht.lbs.model.gameObject.Vector2;

public interface IMovementAction extends Action{
    void setTarget(Vector2 target);
}
