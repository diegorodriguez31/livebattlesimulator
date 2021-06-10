package main.java.fr.enseeiht.lbs.model.game.object.unit.soldier.standard.unit;

import main.java.fr.enseeiht.lbs.model.battle.simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game.object.Stats;
import main.java.fr.enseeiht.lbs.model.game.object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.GroundMovementAction;
import main.java.fr.enseeiht.lbs.model.game.object.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.ArcherAttack;
import main.java.fr.enseeiht.lbs.utils.Vector2;

/**
 * L'unité Archer
 */
public class Archer extends Unit {

    /**
     * Compteur de flèches tirées
     */
    private int nbArrowsShot;

    public Archer(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
        nbArrowsShot = 1;
        ai = new ChargeAndHitAI(new ArcherAttack(this), new GroundMovementAction(this));
    }

    /**
     * Incrémente modulo 3 le compteur de flèches tirées
     */
    public void updateNbArrowsShot(){
        nbArrowsShot = nbArrowsShot == 3 ? 1 : nbArrowsShot + 1;
    }

    public int getNbArrowsShot(){
        return nbArrowsShot;
    }

    @Override
    public void start(Battle context) {

    }

    @Override
    public void end(Battle context) {

    }
}
