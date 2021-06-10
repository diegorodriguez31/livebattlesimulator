package main.java.fr.enseeiht.lbs.model.game.object.unit.ai;

import main.java.fr.enseeiht.lbs.model.battle.simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.Action;
import main.java.fr.enseeiht.lbs.model.game.object.unit.Unit;

import java.util.List;

/**
 * Intelligence artificielle des unités.
 * Elle donne les actions à exécuter à l'unité à chaque "boucle" lors de la bataille.
 */
public interface AI {
    List<Action> getActions(Unit self, Battle context, long deltaTime);
}
