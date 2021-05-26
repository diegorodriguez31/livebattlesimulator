
package main.java.fr.enseeiht.lbs.model.gameObject.unit;

import main.java.fr.enseeiht.lbs.model.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.model.gameObject.Entity;
import main.java.fr.enseeiht.lbs.model.gameObject.Stats;
import main.java.fr.enseeiht.lbs.model.gameObject.Vector2;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.action.Action;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.ai.AI;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.buff.Buff;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.visitors.StatModifierBuffVisitor;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.visitors.TicBuffVisitor;

import java.util.ArrayList;
import java.util.List;

public abstract class Unit extends Entity {
    protected AI ai;
    List<Buff> buffs = new ArrayList<>();

    public Unit(double health, Vector2 position) {
        super(health, position);
    }

    @Override
    public Stats getStats() {
        StatModifierBuffVisitor visitor = getStatVisitor();
        for (Buff buff : buffs) {
            buff.accept(visitor);
        }
        return visitor.getStats();
    }

    public abstract void status();

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
