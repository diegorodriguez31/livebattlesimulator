package main.java.fr.enseeiht.lbs.model.game_object.unit.ai;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.Action;

import java.util.List;

public interface AI {
    List<Action> getActions(Unit self, Battle context, long deltaTime);
}
