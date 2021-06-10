package main.java.fr.enseeiht.lbs.model.game.object.unit.soldier.standard.unit;

import main.java.fr.enseeiht.lbs.model.battle.simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game.object.Stats;
import main.java.fr.enseeiht.lbs.model.game.object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.FlightMovementAction;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.MageAttack;
import main.java.fr.enseeiht.lbs.model.game.object.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.utils.Vector2;

/**
 * L'unité Mage
 */
public class Mage extends Unit {

    /**
     * Compteur d'attaques
     */
    private int nbAttacks;

    public Mage(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
        nbAttacks = 2;
        ai = new ChargeAndHitAI(new MageAttack(this), new FlightMovementAction(this));
    }

    /**
     * Toutes les 2 attaques, le mage incrémente modulo 2 son compteur d'attaques
     */
    public void updateNbAttacks() {
        nbAttacks = nbAttacks == 2 ? 1 : nbAttacks + 1;
    }

    public int getNbAttacks() {
        return nbAttacks;
    }

    @Override
    public void start(Battle context) {

    }

    @Override
    public void end(Battle context) {

    }
}
