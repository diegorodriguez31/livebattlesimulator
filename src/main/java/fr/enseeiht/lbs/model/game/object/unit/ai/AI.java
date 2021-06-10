package main.java.fr.enseeiht.lbs.model.game.object.unit.ai;

import main.java.fr.enseeiht.lbs.model.battle.simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.Action;
import main.java.fr.enseeiht.lbs.model.game.object.unit.Unit;

import java.util.List;

public interface AI {
    List<Action> getActions(Unit self, Battle context, long deltaTime);
}
