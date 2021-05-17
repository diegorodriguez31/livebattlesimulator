package main.java.fr.enseeiht.lbs.gameObject.unit;

import main.java.fr.enseeiht.lbs.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.gameObject.Stats;
import main.java.fr.enseeiht.lbs.gameObject.unit.ai.AI;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.Buff;
import main.java.fr.enseeiht.lbs.gameObject.Entity;
import main.java.fr.enseeiht.lbs.gameObject.unit.visitors.BuffVisitor;

import java.util.ArrayList;
import java.util.List;

public abstract class Unit extends Entity {
    private AI ai;
    List<Buff> buffs = new ArrayList<>();

    @Override
    public Stats getStats() {
        BuffVisitor visitor = getVisitor();
        for (Buff buff : buffs) {
            buff.accept(visitor);
        }
        return visitor.getStats();
    }

    @Override
    public void update(Battle context, float deltaTime) {
        for (Buff buff : buffs) {
            buff.update(this, deltaTime);
        }
    }

    public void addBuffs(Buff buff) {
        buffs.add(buff);
    }

    protected BuffVisitor getVisitor(){
        return new BuffVisitor(stats);
    }
    
    public Stats getRawStats() {
        return stats;
    }
}
