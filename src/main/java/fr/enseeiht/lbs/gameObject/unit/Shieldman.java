package main.java.fr.enseeiht.lbs.gameObject.unit;

import main.java.fr.enseeiht.lbs.gameObject.unit.visitors.BuffVisitor;
import main.java.fr.enseeiht.lbs.gameObject.unit.visitors.ShieldManBuffVisitor;
import main.java.fr.enseeiht.lbs.gameObject.Statistic;

public class Shieldman extends Infantryman {

    public Shieldman(double health, double speed, double damage, double armor) {
        super(health, speed, damage);
        stats.addStat(Statistic.ARMOR, armor);
    }

    @Override
    public void status(){
        System.out.println("\nShieldman status :");
        System.out.println("health : " + getHealth());
        System.out.println("armure : " + getStats().getStatisticValue(Statistic.ARMOR));
        System.out.println("speed : " + getStats().getStatisticValue(Statistic.SPEED));
        System.out.println("damage : " + getStats().getStatisticValue(Statistic.DAMAGE));
    }

    @Override
    public void receiveDamage(double damage) {
        double reducedDamage = damage * (1 - getStats().getStatisticValue(Statistic.ARMOR)/100);
        super.receiveDamage(reducedDamage);
    }

    @Override
    protected BuffVisitor getVisitor() {
        return new ShieldManBuffVisitor(stats);
    }
}
