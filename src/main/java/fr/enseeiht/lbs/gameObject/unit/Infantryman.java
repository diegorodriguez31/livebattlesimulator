package main.java.fr.enseeiht.lbs.gameObject.unit;

import main.java.fr.enseeiht.lbs.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.gameObject.Stats;
import main.java.fr.enseeiht.lbs.gameObject.Statistic;

public class Infantryman extends Unit {

    public Infantryman(double health, double speed, double damage) {
        this.health = health;

        stats = new Stats();
        stats.addStat(Statistic.SPEED, speed);
        stats.addStat(Statistic.DAMAGE, damage);
    }

    public void status(){
        System.out.println("\nInfantryman status :");
        System.out.println("health : " + getHealth());
        System.out.println("speed : " + getStats().getStatisticValue(Statistic.SPEED));
        System.out.println("damage : " + getStats().getStatisticValue(Statistic.DAMAGE));
    }

    @Override
    public void start(Battle context) {

    }

    @Override
    public void update(Battle context, float deltaTime) {
        super.update(context, deltaTime);
    }

    @Override
    public void end(Battle context) {

    }
}
