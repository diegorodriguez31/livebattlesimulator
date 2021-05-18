package main.java.fr.enseeiht.lbs.gameObject.unit;

import main.java.fr.enseeiht.lbs.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.gameObject.Stats;
import main.java.fr.enseeiht.lbs.gameObject.unit.ai.AI;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.Buff;
import main.java.fr.enseeiht.lbs.gameObject.Entity;
import main.java.fr.enseeiht.lbs.gameObject.unit.visitors.SBuffVisitor;
import main.java.fr.enseeiht.lbs.gameObject.unit.visitors.UBuffVisitor;

import java.util.ArrayList;
import java.util.List;

public abstract class Unit extends Entity {
    private AI ai;
    List<Buff> buffs = new ArrayList<>();

    @Override
    public Stats getStats() {
        SBuffVisitor visitor = getStatVisitor();
        for (Buff buff : buffs) {
            buff.accept(visitor);
        }
        return visitor.getStats();
    }

    @Override
    public void update(Battle context, float deltaTime) {
        UBuffVisitor visitor = getUpdateVisitor(deltaTime);
        for (Buff buff : buffs) {
            buff.accept(visitor);
        }
    }

    public void addBuffs(Buff buff) {
        buffs.add(buff);
    }

    protected SBuffVisitor getStatVisitor(){
        return new SBuffVisitor(stats);
    }
    protected UBuffVisitor getUpdateVisitor(float deltaTime){
        return new UBuffVisitor(deltaTime,this);
    }

    public Stats getRawStats() {
        return stats;
    }
}
