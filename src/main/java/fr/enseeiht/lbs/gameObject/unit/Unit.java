package main.java.fr.enseeiht.lbs.gameObject.unit;

import main.java.fr.enseeiht.lbs.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.gameObject.Statistic;
import main.java.fr.enseeiht.lbs.gameObject.Stats;
import main.java.fr.enseeiht.lbs.gameObject.Vector2;
import main.java.fr.enseeiht.lbs.gameObject.unit.action.Action;
import main.java.fr.enseeiht.lbs.gameObject.unit.ai.AI;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.Buff;
import main.java.fr.enseeiht.lbs.gameObject.Entity;
import main.java.fr.enseeiht.lbs.gameObject.unit.visitors.StatModifierBuffVisitor;
import main.java.fr.enseeiht.lbs.gameObject.unit.visitors.TicBuffVisitor;

import java.util.ArrayList;
import java.util.List;

public abstract class Unit extends Entity {
    protected AI ai;
    List<Buff> buffs = new ArrayList<>();
    protected double cooldown;
    private String name;

    // create basic fighting unit
    public Unit(Vector2 vector, String name, double health, double damage, double cooldown, double speed, double range, double accuracy, double agility) {
        super(health, vector);
        this.name = name;
        stats = new Stats();
        stats.addStat(Statistic.DAMAGE, damage);
        stats.addStat(Statistic.COOLDOWN, cooldown);
        stats.addStat(Statistic.SPEED, speed);
        stats.addStat(Statistic.REACH, range);
        stats.addStat(Statistic.ACCURACY, accuracy);
        stats.addStat(Statistic.AGILITY, agility);
        this.cooldown = cooldown;
    }

    public Unit(String name, double health, Vector2 position) {
        super(health, position);
        this.name = name;
    }

    @Override
    public Stats getStats() {
        StatModifierBuffVisitor visitor = getStatVisitor();
        for (Buff buff : buffs) {
            buff.accept(visitor);
        }
        return visitor.getStats();
    }

    public void status() {
        System.out.println(this.name + " status :");
        System.out.println("\tHealth : " + getHealth());
        System.out.println("\tDamage : " + getStats().getStatisticValue(Statistic.DAMAGE));
        System.out.println("\tCooldown : " + getStats().getStatisticValue(Statistic.COOLDOWN));
        System.out.println("\tSpeed : " + getStats().getStatisticValue(Statistic.SPEED));
        System.out.println("\tRange : " + getStats().getStatisticValue(Statistic.REACH));
        System.out.println("\tAccuracy : " + getStats().getStatisticValue(Statistic.ACCURACY));
        System.out.println("\tAgility : " + getStats().getStatisticValue(Statistic.AGILITY));
    }

    // method inspired by Unity Game Motor
    // this is the behaviour of the unit !!!
    @Override
    public void update(Battle context, long deltaTime) {
        for (Action a :
                ai.getActions(this,context,deltaTime)) {
            a.execute(deltaTime);
        }
        status();
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
