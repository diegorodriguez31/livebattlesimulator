package main.java.fr.enseeiht.lbs.model.game_object.unit.soldier;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.Action;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.AttackAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.FlightMovementAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.Buff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.PeasantGroupBuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.BasicDotVisitor;
import main.java.fr.enseeiht.lbs.utils.Vector2;

import java.util.List;

import static main.java.fr.enseeiht.lbs.LiveBattleSimulator.VERBOSE;
import static main.java.fr.enseeiht.lbs.model.game_object.unit.RawStatsManager.*;

public class Peasant extends Unit {

    public static final int GROUP_RADIUS = 2;

    public Peasant(Vector2 vector, String name, double health, double damage, double cooldown, double speed, double range, double accuracy, double agility) {
        super(vector, name, health, damage, cooldown, speed, range, accuracy, agility);
        ai = new ChargeAndHitAI(new AttackAction(this), new FlightMovementAction(this));
        this.addBuffs(new PeasantGroupBuff());
    }

    public Peasant(Vector2 vector) {
        this(vector, PEASANT_NAME, PEASANT_HEALTH, PEASANT_DAMAGE, PEASANT_COOLDOWN, PEASANT_SPEED, PEASANT_RANGE, PEASANT_ACCURACY, PEASANT_AGILITY);
    }

    public Peasant(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
        ai = new ChargeAndHitAI(new AttackAction(this), new FlightMovementAction(this));
        this.addBuffs(new PeasantGroupBuff());
    }

    @Override
    public void update(Battle context, long deltaTime) {
        for (Action a :
                ai.getActions(this, context, deltaTime)) {
            a.execute(deltaTime);
        }
        if (VERBOSE >= 2) {
            status();
        }
        // update buffs
        BasicDotVisitor visitor = getUpdateVisitor(deltaTime);
        for (Buff buff : buffs) {
            buff.accept(visitor);
        }
    }

    /**
     * Vérifie si le paysant se situe dans un groupe d'au moins 3 unités
     * @return vrai si le paysant se situe dans un groupe d'au moins 3 unités et faux sinon
     */
    public boolean isInAGroupOf3() {
        // On cherche la troisième car la première est "this"
        List<Unit> allies = Battle.getInstance().findAllies(this);
        if (allies.size() > 2) {
            Unit secondNearestUnit = allies.get(2);
            return secondNearestUnit.getPosition().sub(this.getPosition()).sqrSize() <= GROUP_RADIUS;
        }
        return false;
    }

    @Override
    public void status() {
        super.status();
        System.out.println("\n");
    }

    @Override
    public void receiveDamage(double damage) {
        super.receiveDamage(damage);
    }

    @Override
    public void start(Battle context) {

    }

    @Override
    public void end(Battle context) {

    }
}
