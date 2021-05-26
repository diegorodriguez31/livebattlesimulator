package main.java.fr.enseeiht.lbs.model.gameObject.unit.ai;

import main.java.fr.enseeiht.lbs.model.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.Unit;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.action.Action;

import java.util.List;

public interface AI {
    List<Action> getActions(Unit self, Battle context, long deltaTime);
}
