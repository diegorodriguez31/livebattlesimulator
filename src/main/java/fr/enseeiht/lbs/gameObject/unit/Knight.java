package main.java.fr.enseeiht.lbs.gameObject.unit;

import main.java.fr.enseeiht.lbs.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.gameObject.Vector2;
import main.java.fr.enseeiht.lbs.gameObject.unit.action.Action;
import main.java.fr.enseeiht.lbs.gameObject.unit.action.AttackAction;
import main.java.fr.enseeiht.lbs.gameObject.unit.action.FlightMovementAction;
import main.java.fr.enseeiht.lbs.gameObject.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.Buff;
import main.java.fr.enseeiht.lbs.gameObject.unit.visitors.KnightTicBuffVisitor;
import main.java.fr.enseeiht.lbs.gameObject.unit.visitors.StatModifierBuffVisitor;
import main.java.fr.enseeiht.lbs.gameObject.unit.visitors.TicBuffVisitor;

import static main.java.fr.enseeiht.lbs.gameObject.Statistic.*;
import static main.java.fr.enseeiht.lbs.gameObject.unit.RawStatsManager.*;

public class Knight extends Unit {

    public Knight(String name, double health, Vector2 vector, double damage, long cooldown, double speed, double range, double accuracy, double agility, double armor) {
        super(name, health, vector, damage, cooldown, speed, range, accuracy, agility);
        stats.addStat(ARMOR, armor);
        ai = new ChargeAndHitAI(new AttackAction(this), new FlightMovementAction(this));
    }

    public Knight(Vector2 vector) {
        this(KNIGHT_NAME, KNIGHT_HEALTH, vector, KNIGHT_DAMAGE, KNIGHT_COOLDOWN, KNIGHT_SPEED, KNIGHT_RANGE, KNIGHT_ACCURACY,KNIGHT_AGILITY, KNIGHT_ARMOR);
    }

    @Override
    public void start(Battle context) {

    }

    @Override
    public void status(){
        super.status();
        System.out.println("\tArmor : " + getStats().getStatisticValue(ARMOR));
        System.out.println("\n");
    }

    @Override
    public void end(Battle context) {

    }

    @Override
    public void receiveDamage(double damage) {
        if (hasArmor()) {
            double reducedDamage = damage * (getStats().getStatisticValue(ARMOR) / 100);
            super.receiveDamage(reducedDamage);
        } else {
            super.receiveDamage(damage);
        }
    }

    protected TicBuffVisitor getUpdateVisitor(long deltaTime){
        return new KnightTicBuffVisitor(deltaTime,this);
    }

    public boolean hasArmor(){
        return getStats().getStatisticValue(ARMOR) > 0;
    }
}
