package main.java.fr.enseeiht.lbs.model.game_object.unit.visitor;

import main.java.fr.enseeiht.lbs.model.game_object.Statistic;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.FireDebuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.FreezeDebuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.SlowDebuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.PeasantGroupBuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.Peasant;

public class BasicStatModifierBuffVisitor implements BuffVisitor {
    private Stats stats;
    private Unit unit;

    public BasicStatModifierBuffVisitor(Stats stats, Unit unit){
        this.stats = new Stats(stats);
        this.unit = unit;
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

    @Override
    public void visit(PeasantGroupBuff buff) {
        if (((Peasant) unit).isInAGroupOf3()) {
            stats.addStat(Statistic.SPEED, buff.getSpeedMultiplier() * getStats().getStatisticValue(Statistic.SPEED));
            stats.addStat(Statistic.COOLDOWN, buff.getCooldownReducer() * getStats().getStatisticValue(Statistic.COOLDOWN));
        }
    }

    public Stats getStats() {
        return stats;
    }
}
