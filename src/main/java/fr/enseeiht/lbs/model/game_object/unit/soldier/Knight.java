package main.java.fr.enseeiht.lbs.model.game_object.unit.soldier;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.AttackAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.FlightMovementAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.BasicDotVisitor;
import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.dotVisitor.KnightDotVisitor;
import main.java.fr.enseeiht.lbs.utils.Vector2;

import static main.java.fr.enseeiht.lbs.model.game_object.Statistic.ARMOR;
import static main.java.fr.enseeiht.lbs.model.game_object.unit.RawStatsManager.*;

public class Knight extends Unit {

    public Knight(Vector2 vector, String name, double health, double damage, double cooldown, double speed, double range, double accuracy, double agility, double armor) {
        super(vector, name, health, damage, cooldown, speed, range, accuracy, agility);
        stats.addStat(ARMOR, armor);
        ai = new ChargeAndHitAI(new AttackAction(this), new FlightMovementAction(this));
    }

    public Knight(Vector2 vector) {
        this(vector, KNIGHT_NAME, KNIGHT_HEALTH, KNIGHT_DAMAGE, KNIGHT_COOLDOWN, KNIGHT_SPEED, KNIGHT_RANGE, KNIGHT_ACCURACY, KNIGHT_AGILITY, KNIGHT_ARMOR);
    }

    public Knight(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
        ai = new ChargeAndHitAI(new AttackAction(this), new FlightMovementAction(this));
    }

    @Override
    public void status() {
        super.status();
        System.out.println("\tArmor : " + getStats().getStatisticValue(ARMOR));
        System.out.println("\n");
    }

    @Override
    public void receiveDamage(double damage) {
        double reducedDamage = damage - (damage * (getStats().getStatisticValue(ARMOR) / 100));
        super.receiveDamage(reducedDamage);
    }

    protected BasicDotVisitor getUpdateVisitor(long deltaTime) {
        return new KnightDotVisitor(deltaTime, this);
    }

    public boolean hasArmor() {
        return getStats().getStatisticValue(ARMOR) > 0;
    }

    @Override
    public void start(Battle context) {

    }

    @Override
    public void end(Battle context) {

    }
}
