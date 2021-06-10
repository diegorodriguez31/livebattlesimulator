package main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.standard_unit;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.AttackAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.FlightMovementAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.utils.Vector2;

public class Mage extends Unit {

    /**
     * Compteur d'attaques
     */
    private int nbAttacks;

    public Mage(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
        nbAttacks = 1;
        ai = new ChargeAndHitAI(new AttackAction(this), new FlightMovementAction(this));
    }

    /**
     * Incrémente modulo 10 le compteur d'attaques. Toutes les 10 attaques, le mage fait
     */
    public void updateNbAttacks() {
        nbAttacks = nbAttacks == 10 ? 1 : nbAttacks + 1;
    }

    @Override
    public void start(Battle context) {

    }

    @Override
    public void end(Battle context) {

    }
}
