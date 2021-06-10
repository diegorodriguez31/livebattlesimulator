package main.java.fr.enseeiht.lbs.model.game.object.unit.soldier.standard.unit;

import main.java.fr.enseeiht.lbs.model.battle.simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game.object.Stats;
import main.java.fr.enseeiht.lbs.model.game.object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.FlightMovementAction;
import main.java.fr.enseeiht.lbs.model.game.object.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.dot.visitor.BasicTicVisitor;
import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.dot.visitor.ImmuneTicVisitor;
import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.stat.modifier.visitor.BasicStatModifierBuffVisitor;
import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.stat.modifier.visitor.ImmuneStatModifierVisitor;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.AlienAttack;
import main.java.fr.enseeiht.lbs.utils.Vector2;

/**
 * L'unité Alien
 */
public class Alien extends Unit {

    private int nbShotDone;

    public Alien(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
        nbShotDone = 1;
        ai = new ChargeAndHitAI(new AlienAttack(this), new FlightMovementAction(this));
    }

    public void updateNbShotDone(){
        nbShotDone = nbShotDone == 3 ? 1 : nbShotDone + 1;
    }

    public int getNbShotDone(){
        return nbShotDone;
    }

    /**
     * L'alien est immunisé débuffs qui modifient les statistiques
     */
    @Override
    protected BasicStatModifierBuffVisitor getStatModifierVisitor() {
        return new ImmuneStatModifierVisitor(stats, this);
    }

    /**
     * L'alien est immunisé aux tics de dégâts
     */
    @Override
    protected BasicTicVisitor getTicVisitor(long deltaTime) {
        return new ImmuneTicVisitor(deltaTime, this);
    }

    @Override
    public void start(Battle context) {

    }

    @Override
    public void end(Battle context) {

    }
}
