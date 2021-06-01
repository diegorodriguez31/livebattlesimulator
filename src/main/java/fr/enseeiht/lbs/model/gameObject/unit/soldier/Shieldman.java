package main.java.fr.enseeiht.lbs.model.gameObject.unit.soldier;

import main.java.fr.enseeiht.lbs.model.gameObject.Statistic;
import main.java.fr.enseeiht.lbs.model.gameObject.Stats;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.action.BuffAction;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.action.FlightMovementAction;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.buff.FireDebuff;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.visitor.BasicStatModifierBuffVisitor;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.visitor.statModifierVisitor.ShieldManBuffVisitor;
import main.java.fr.enseeiht.lbs.utils.Vector2;

public class Shieldman extends Infantryman {

    public Shieldman(String name, Vector2 position, double health, double speed, double damage, double armor, long cooldown, double reach) {
        super(name, position, health, speed, damage, cooldown, reach);
        stats.addStat(Statistic.ARMOR, armor);
        ai = new ChargeAndHitAI(new BuffAction(new FireDebuff()), new FlightMovementAction(this));
    }

    public Shieldman(Stats stats, Vector2 position) {
        super(stats, position);
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
