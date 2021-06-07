package main.java.fr.enseeiht.lbs.model.game_object.unit.soldier;

import main.java.fr.enseeiht.lbs.model.game_object.Statistic;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.BuffAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.FlightMovementAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.GroundMovementAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.FireDebuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.BasicStatModifierBuffVisitor;
import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.statModifierVisitor.ShieldManBuffVisitor;
import main.java.fr.enseeiht.lbs.utils.Vector2;

public class Shieldman extends Infantryman {

    public Shieldman(String name, Vector2 position, double health, double speed, double damage, double armor, long cooldown, double reach) {
        super(name, position, health, speed, damage, cooldown, reach);
        stats.addStat(Statistic.ARMOR, armor);
        ai = new ChargeAndHitAI(new BuffAction(new FireDebuff()), new GroundMovementAction(this));
    }

    public Shieldman(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
        ai = new ChargeAndHitAI(new BuffAction(new FireDebuff()), new GroundMovementAction(this));
    }

    @Override
    public void status() {
        System.out.println("Shieldman status :");
        System.out.println("position : " + getPosition());
        System.out.println("health : " + getHealth());
        System.out.println("armure : " + getStats().getStatisticValue(Statistic.ARMOR));
        System.out.println("speed : " + getStats().getStatisticValue(Statistic.SPEED));
        System.out.println("damage : " + getStats().getStatisticValue(Statistic.DAMAGE));
        System.out.println("-----------------------------------------");
    }

    @Override
    public void receiveDamage(double damage) {
        double reducedDamage = damage * (1 - getStats().getStatisticValue(Statistic.ARMOR) / 100);
        super.receiveDamage(reducedDamage);
    }

    @Override
    protected BasicStatModifierBuffVisitor getStatVisitor() {
        return new ShieldManBuffVisitor(stats);
    }
}
