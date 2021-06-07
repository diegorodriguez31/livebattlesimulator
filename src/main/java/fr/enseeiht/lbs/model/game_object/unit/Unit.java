package main.java.fr.enseeiht.lbs.model.game_object.unit;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Army;
import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game_object.Entity;
import main.java.fr.enseeiht.lbs.model.game_object.Statistic;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.Action;
import main.java.fr.enseeiht.lbs.model.game_object.unit.ai.AI;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.Buff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.dotVisitor.BasicTicVisitor;
import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.statModifierVisitor.BasicStatModifierBuffVisitor;
import main.java.fr.enseeiht.lbs.utils.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static main.java.fr.enseeiht.lbs.LiveBattleSimulator.VERBOSE;
import static main.java.fr.enseeiht.lbs.model.game_object.Statistic.ACCURACY;
import static main.java.fr.enseeiht.lbs.model.game_object.Statistic.AGILITY;

public abstract class Unit extends Entity {
    protected AI ai;
    protected List<Buff> buffs = new ArrayList<>();
    protected double cooldown;
    private Army team;

    // create basic fighting unit
    public Unit(Vector2 vector, String name, double health, double damage, double cooldown, double speed, double range, double accuracy, double agility) {
        super(name, health, vector);
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
        super(name, health, position);
    }

    public Unit(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
    }

    @Override
    public Stats getStats() {
        BasicStatModifierBuffVisitor visitor = getStatModifierVisitor();
        for (Buff buff : buffs) {
            buff.accept(visitor);
        }
        return visitor.getStats();
    }

    public void status() {
        System.out.println(getName() + " status :");
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
        if (VERBOSE >= 2) {
            status();
        }
        // update buffs
        BasicTicVisitor visitor = getTicVisitor(deltaTime);
        for (Buff buff : buffs) {
            buff.accept(visitor);
        }
    }

    public void receiveDamage(double damage) {
        if (!dodge()) {
            health -= damage;
            this.status();
            if (isDead()) {
                removeFromBattle();
            }
        }
    }

    public void addBuffs(Buff buff) {
        buffs.add(buff);
    }

    protected BasicStatModifierBuffVisitor getStatModifierVisitor() {
        return new BasicStatModifierBuffVisitor(stats, this);
    }

    protected BasicTicVisitor getTicVisitor(long deltaTime) {
        return new BasicTicVisitor(deltaTime, this);
    }

    public Stats getRawStats() {
        return stats;
    }

    public boolean attackSuccess() {
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
