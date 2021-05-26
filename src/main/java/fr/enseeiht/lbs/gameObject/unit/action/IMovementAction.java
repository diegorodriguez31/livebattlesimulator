package main.java.fr.enseeiht.lbs.gameObject.unit.action;

import main.java.fr.enseeiht.lbs.gameObject.Vector2;

public interface IMovementAction extends Action{
    void setTarget(Vector2 target);
}
