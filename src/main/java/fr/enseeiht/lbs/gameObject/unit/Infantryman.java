package main.java.fr.enseeiht.lbs.gameObject.unit;

import main.java.fr.enseeiht.lbs.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.gameObject.Statistic;
import main.java.fr.enseeiht.lbs.gameObject.Stats;
import main.java.fr.enseeiht.lbs.gameObject.Vector2;
import main.java.fr.enseeiht.lbs.gameObject.unit.action.AttackAction;
import main.java.fr.enseeiht.lbs.gameObject.unit.action.FlightMovementAction;
import main.java.fr.enseeiht.lbs.gameObject.unit.ai.ChargeAndHitAI;

public class Infantryman extends Unit {
    protected long cooldown;

    public Infantryman(Vector2 position, double health, double speed, double damage, long cooldown, double reach) {
        super(health, position);

        stats = new Stats();
        stats.addStat(Statistic.SPEED, speed);
        stats.addStat(Statistic.DAMAGE, damage);
        stats.addStat(Statistic.COOLDOWN, cooldown);
        stats.addStat(Statistic.RANGE, reach);
        ai = new ChargeAndHitAI(new AttackAction(this), new FlightMovementAction(this));
    }

    public void status(){
        System.out.println("Infantryman status :");
        System.out.println("position : "+ getPosition());
        System.out.println("health : " + getHealth());
        System.out.println("speed : " + getStats().getStatisticValue(Statistic.SPEED));
        System.out.println("damage : " + getStats().getStatisticValue(Statistic.DAMAGE));
        System.out.println("-----------------------------------------");
    }

    @Override
    public void start(Battle context) {

    }



    @Override
    public void end(Battle context) {

    }
}
