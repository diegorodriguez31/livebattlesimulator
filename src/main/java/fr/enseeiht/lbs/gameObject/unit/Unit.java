package main.java.fr.enseeiht.lbs.gameObject.unit;

import main.java.fr.enseeiht.lbs.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.gameObject.Statistic;
import main.java.fr.enseeiht.lbs.gameObject.Stats;
import main.java.fr.enseeiht.lbs.gameObject.unit.ai.AI;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.Buff;
import main.java.fr.enseeiht.lbs.gameObject.Entity;
import main.java.fr.enseeiht.lbs.gameObject.unit.visitors.StatModifierBuffVisitor;
import main.java.fr.enseeiht.lbs.gameObject.unit.visitors.TicBuffVisitor;

import java.util.ArrayList;
import java.util.List;

public abstract class Unit extends Entity {
    private AI ai;
    List<Buff> buffs = new ArrayList<>();
    protected long cooldown;

    // create basic fighting unit
    public Unit(double health, double damage, long cooldown, double speed, double range, double accuracy, double agility) {
        this.health = health;

        stats = new Stats();
        stats.addStat(Statistic.DAMAGE, damage);
        stats.addStat(Statistic.COOLDOWN, cooldown);
        stats.addStat(Statistic.SPEED, speed);
        stats.addStat(Statistic.RANGE, range);
        stats.addStat(Statistic.ACCURACY, accuracy);
        stats.addStat(Statistic.AGILITY, agility);
        this.cooldown = cooldown;
    }

    // create basic structures
    public Unit(double health) {
        this.health = health;
    }

    @Override
    public Stats getStats() {
        StatModifierBuffVisitor visitor = getStatVisitor();
        for (Buff buff : buffs) {
            buff.accept(visitor);
        }
        return visitor.getStats();
    }

    // method inspired by Unity Game Motor
    // this is the behaviour of the unit !!!
    @Override
    public void update(Battle context, long deltaTime) {

        // update buffs
        TicBuffVisitor visitor = getUpdateVisitor(deltaTime);
        for (Buff buff : buffs) {
            buff.accept(visitor);
        }
    }

    public void addBuffs(Buff buff) {
        buffs.add(buff);
    }

    protected StatModifierBuffVisitor getStatVisitor(){
        return new StatModifierBuffVisitor(stats);
    }
    protected TicBuffVisitor getUpdateVisitor(long deltaTime){
        return new TicBuffVisitor(deltaTime,this);
    }

    public Stats getRawStats() {
        return stats;
    }
}
