package main.java.fr.enseeiht.lbs.gameObject.unit.visitors;

import main.java.fr.enseeiht.lbs.gameObject.Statistic;
import main.java.fr.enseeiht.lbs.gameObject.Stats;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.FireDebuff;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.FreezeDebuff;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.SlowDebuff;

public class StatModifierBuffVisitor implements IBuffVisitor {
    Stats stats;

    public StatModifierBuffVisitor(Stats stats){
        this.stats = new Stats(stats);
    }

    @Override
    public void visit(FireDebuff buff) {
        stats.addStat(Statistic.ARMOR, 0);
    }

    @Override
    public void visit(FreezeDebuff buff) {
        stats.addStat(Statistic.SPEED, 0);
    }

    @Override
    public void visit(SlowDebuff buff) {
        stats.addStat(Statistic.SPEED, buff.getSlow() * getStats().getStatisticValue(Statistic.SPEED));
    }

    public Stats getStats() {
        return stats;
    }
}
