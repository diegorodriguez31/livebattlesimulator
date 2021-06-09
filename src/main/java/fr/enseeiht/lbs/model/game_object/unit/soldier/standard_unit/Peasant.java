package main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.standard_unit;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.AttackAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.FlightMovementAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.PeasantGroupBuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.statModifierVisitor.PeasantStatModifierVisitor;
import main.java.fr.enseeiht.lbs.utils.Vector2;

import java.util.List;

public class Peasant extends Unit {

    /**
     * Rayon dans lequel les alliés présent forment un groupe
     */
    private static final int GROUP_RADIUS = 2;

    /**
     * Multiplicateur de vitesse de déplacement à l'intérieur d'un groupe
     */
    private static final double GROUP_SPEED_MULTIPLIER = 2;

    /**
     * Multiplicateur de réduction du temps d'attente entre chaque attaque
     * à l'intérieur d'un groupe
     */
    private static final double GROUP_COOLDOWN_REDUCER = 0.5;

    public Peasant(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
        ai = new ChargeAndHitAI(new AttackAction(this), new FlightMovementAction(this));
        this.addBuffs(new PeasantGroupBuff(GROUP_SPEED_MULTIPLIER, GROUP_COOLDOWN_REDUCER));
    }

    /**
     * Vérifie si le paysant se situe dans un groupe d'au moins 3 unités
     * @return vrai si le paysant se situe dans un groupe d'au moins 3 unités et faux sinon
     */
    public boolean isInAGroupOfThree() {
        // On cherche la troisième car la première est "this"
        List<Unit> allies = Battle.getInstance().findAllies(this);
        if (allies.size() > 2) {
            Unit secondNearestUnit = allies.get(2);
            return secondNearestUnit.getPosition().sub(this.getPosition()).sqrSize() <= GROUP_RADIUS;
        }
        return false;
    }

    /**
     * En groupe de 3, le paysant marche plus vite et attaque plus souvent
     */
    @Override
    protected PeasantStatModifierVisitor getStatModifierVisitor() {
        return new PeasantStatModifierVisitor(stats, this);
    }


    @Override
    public void start(Battle context) {

    }

    @Override
    public void end(Battle context) {

    }
}
