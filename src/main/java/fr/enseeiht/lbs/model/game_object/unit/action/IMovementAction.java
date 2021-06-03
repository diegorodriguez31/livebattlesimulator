package main.java.fr.enseeiht.lbs.model.game_object.unit.action;

import main.java.fr.enseeiht.lbs.model.game_object.Vector2;

public interface IMovementAction extends Action{
    void setTarget(Vector2 target);
}
