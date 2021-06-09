package main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.armored_unit;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.AttackAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.GroundMovementAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.dotVisitor.BasicTicVisitor;
import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.dotVisitor.KnightTicVisitor;
import main.java.fr.enseeiht.lbs.utils.Vector2;

public class Knight extends ArmoredUnit {

    public Knight(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
        ai = new ChargeAndHitAI(new AttackAction(this), new GroundMovementAction(this));
    }

    /**
     * Le Knight réagit de manière unique aux tics de dégâts
     * (voir KnightTicVisitor)
     */
    public BasicTicVisitor getTicVisitor(long deltaTime) {
        return new KnightTicVisitor(deltaTime, this);
    }

    @Override
    public void start(Battle context) {

    }

    @Override
    public void end(Battle context) {

    }


}
