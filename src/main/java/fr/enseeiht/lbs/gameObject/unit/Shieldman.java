package main.java.fr.enseeiht.lbs.gameObject.unit;

import main.java.fr.enseeiht.lbs.gameObject.Vector2;
import main.java.fr.enseeiht.lbs.gameObject.unit.action.BuffAction;
import main.java.fr.enseeiht.lbs.gameObject.unit.action.FlightMovementAction;
import main.java.fr.enseeiht.lbs.gameObject.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.FireDebuff;
import main.java.fr.enseeiht.lbs.gameObject.unit.visitors.StatModifierBuffVisitor;
import main.java.fr.enseeiht.lbs.gameObject.unit.visitors.ShieldManSBuffVisitor;
import main.java.fr.enseeiht.lbs.gameObject.Statistic;

public class Shieldman extends Infantryman {

    public Shieldman(Vector2 position, double health, double speed, double damage, double armor, long cooldown, double range) {
        super(position, health, speed, damage, cooldown, range);
        stats.addStat(Statistic.ARMOR, armor);
        ai = new ChargeAndHitAI(new BuffAction(new FireDebuff()), new FlightMovementAction(this));
    }

    @Override
    public void status(){
        System.out.println("Shieldman status :");
        System.out.println("position : "+ getPosition());
        System.out.println("health : " + getHealth());
        System.out.println("armure : " + getStats().getStatisticValue(Statistic.ARMOR));
        System.out.println("speed : " + getStats().getStatisticValue(Statistic.SPEED));
        System.out.println("damage : " + getStats().getStatisticValue(Statistic.DAMAGE));
        System.out.println("-----------------------------------------");
    }

    @Override
    public void receiveDamage(double damage) {
        double reducedDamage = damage * (1 - getStats().getStatisticValue(Statistic.ARMOR)/100);
        super.receiveDamage(reducedDamage);
    }

    @Override
    protected StatModifierBuffVisitor getStatVisitor() {
        return new ShieldManSBuffVisitor(stats);
    }
}
