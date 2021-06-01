package main.java.fr.enseeiht.lbs.model.gameObject.unit;

import main.java.fr.enseeiht.lbs.model.battleSimulator.Army;
import main.java.fr.enseeiht.lbs.model.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.model.gameObject.Entity;
import main.java.fr.enseeiht.lbs.model.gameObject.Statistic;
import main.java.fr.enseeiht.lbs.model.gameObject.Stats;
import main.java.fr.enseeiht.lbs.model.gameObject.Vector2;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.action.Action;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.ai.AI;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.buff.Buff;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.visitor.BasicDotVisitor;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.visitor.BasicStatModifierBuffVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static main.java.fr.enseeiht.lbs.model.gameObject.Statistic.ACCURACY;
import static main.java.fr.enseeiht.lbs.model.gameObject.Statistic.AGILITY;

public abstract class Unit extends Entity {
    protected AI ai;
    List<Buff> buffs = new ArrayList<>();
    protected double cooldown;
    private String name;
    private Army team;

    // create basic fighting unit
    public Unit(Vector2 vector, String name, double health, double damage, double cooldown, double speed, double range, double accuracy, double agility) {
        super(health, vector);
        this.name = name;
        stats = new Stats();
        stats.addStat(Statistic.DAMAGE, damage);
        stats.addStat(Statistic.COOLDOWN, cooldown);
        stats.addStat(Statistic.SPEED, speed);
        stats.addStat(Statistic.RANGE, range);
        stats.addStat(ACCURACY, accuracy);
        stats.addStat(Statistic.AGILITY, agility);
        this.cooldown = cooldown;
    }

    public Unit(String name, double health, Vector2 position) {
        super(health, position);
        this.name = name;
    }

    @Override
    public Stats getStats() {
        BasicStatModifierBuffVisitor visitor = getStatVisitor();
        for (Buff buff : buffs) {
            buff.accept(visitor);
        }
        return visitor.getStats();
    }

    public void status() {
        System.out.println(name + " status :");
        System.out.println("\tHealth : " + getHealth());
        System.out.println("\tDamage : " + getStats().getStatisticValue(Statistic.DAMAGE));
        System.out.println("\tCooldown : " + getStats().getStatisticValue(Statistic.COOLDOWN));
        System.out.println("\tSpeed : " + getStats().getStatisticValue(Statistic.SPEED));
        System.out.println("\tRange : " + getStats().getStatisticValue(Statistic.RANGE));
        System.out.println("\tAccuracy : " + getStats().getStatisticValue(ACCURACY));
        System.out.println("\tAgility : " + getStats().getStatisticValue(Statistic.AGILITY));
    }

    // method inspired by Unity Game Motor
    // this is the behaviour of the unit !!!
    @Override
    public void update(Battle context, long deltaTime) {
        for (Action a :
                ai.getActions(this, context, deltaTime)) {
            a.execute(deltaTime);
        }
        status();
        // update buffs
        BasicDotVisitor visitor = getUpdateVisitor(deltaTime);
        for (Buff buff : buffs) {
            buff.accept(visitor);
        }
    }

    public void receiveDamage(double damage) {
        if (!dodge()) {
            health -= damage;
            if (isDead()) {
                removeFromBattle();
            }
        }
    }

    public void addBuffs(Buff buff) {
        buffs.add(buff);
    }

    protected BasicStatModifierBuffVisitor getStatVisitor() {
        return new BasicStatModifierBuffVisitor(stats);
    }

    protected BasicDotVisitor getUpdateVisitor(long deltaTime) {
        return new BasicDotVisitor(deltaTime, this);
    }

    public Stats getRawStats() {
        return stats;
    }

    public boolean attackSucess() {
        Random random = new Random();
        return (random.nextInt(100) + 1) < getStats().getStatisticValue(ACCURACY);
    }

    public boolean dodge() {
        Random random = new Random();
        return (random.nextInt(100) + 1) < getStats().getStatisticValue(AGILITY);
    }

    public Army getTeam() {
        return team;
    }

    public void setTeam(Army team) {
        this.team = team;
    }
}
