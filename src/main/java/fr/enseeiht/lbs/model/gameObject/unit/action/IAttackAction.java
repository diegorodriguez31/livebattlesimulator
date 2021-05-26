package main.java.fr.enseeiht.lbs.model.gameObject.unit.action;

import main.java.fr.enseeiht.lbs.model.gameObject.unit.Unit;

public interface IAttackAction extends Action{
    void setTarget(Unit unit);
}
