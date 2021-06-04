package main.java.fr.enseeiht.lbs.model.game_object.unit.soldier;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.AttackAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.FlightMovementAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.utils.Vector2;

import static main.java.fr.enseeiht.lbs.model.game_object.unit.RawStatsManager.*;

public class Peasant extends Unit {

    public Peasant(Vector2 vector, String name, double health, double damage, double cooldown, double speed, double range, double accuracy, double agility) {
        super(vector, name, health, damage, cooldown, speed, range, accuracy, agility);
        ai = new ChargeAndHitAI(new AttackAction(this), new FlightMovementAction(this));
    }

    public Peasant(Vector2 vector) {
        this(vector, PEASANT_NAME, PEASANT_HEALTH, PEASANT_DAMAGE, PEASANT_COOLDOWN, PEASANT_SPEED, PEASANT_RANGE, PEASANT_ACCURACY, PEASANT_AGILITY);
    }

    public Peasant(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
        ai = new ChargeAndHitAI(new AttackAction(this), new FlightMovementAction(this));
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
