package main.java.fr.enseeiht.lbs.model.game_object.unit.action;

import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;

/**
 * Interface qui decrit une action d'attaque sur une cible
 */
public interface IAttackAction extends Action{
    void setTarget(Unit unit);
}
