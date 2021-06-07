package main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.statModifierVisitor;

import main.java.fr.enseeiht.lbs.model.game_object.Statistic;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.FireDebuff;

public class HorsemanStatModifierVisitor extends BasicStatModifierBuffVisitor{

    public HorsemanStatModifierVisitor(Stats stats, Unit unit) {
        super(stats, unit);
    }

    @Override
    public void visit(FireDebuff buff) {
        getStats().addStat(Statistic.SPEED, 0);
    }
}
