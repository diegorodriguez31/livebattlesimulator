package main.java.fr.enseeiht.lbs.gameObject.unit.action;

import main.java.fr.enseeiht.lbs.gameObject.unit.Unit;

public interface IAttackAction extends Action{
    void setTarget(Unit unit);
}
