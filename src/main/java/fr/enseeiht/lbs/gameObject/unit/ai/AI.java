package main.java.fr.enseeiht.lbs.gameObject.unit.ai;

import main.java.fr.enseeiht.lbs.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.gameObject.unit.Unit;
import main.java.fr.enseeiht.lbs.gameObject.unit.action.Action;

import java.util.List;

public interface AI {
    List<Action> getActions(Unit self, Battle context, long deltaTime);
}
