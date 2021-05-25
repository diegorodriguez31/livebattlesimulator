package main.java.fr.enseeiht.lbs.gameObject.unit;

import main.java.fr.enseeiht.lbs.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.gameObject.Statistic;
import main.java.fr.enseeiht.lbs.gameObject.Stats;
import main.java.fr.enseeiht.lbs.gameObject.Vector2;
import main.java.fr.enseeiht.lbs.gameObject.unit.action.BuffAction;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.FireDebuff;

public class Infantryman extends Unit {

    protected long cooldown;

    public Infantryman(Vector2 position, double health, double speed, double damage, long cooldown) {
    	super(position);
    	
        this.health = health;

        stats = new Stats();
        stats.addStat(Statistic.SPEED, speed);
        stats.addStat(Statistic.DAMAGE, damage);
        stats.addStat(Statistic.COOLDOWN, cooldown);
        this.cooldown = cooldown;
    }

    public void status(){
        System.out.println("Infantryman status :");
        System.out.println("health : " + getHealth());
        System.out.println("speed : " + getStats().getStatisticValue(Statistic.SPEED));
        System.out.println("damage : " + getStats().getStatisticValue(Statistic.DAMAGE));
        System.out.println("-----------------------------------------");
    }

    @Override
    public void start(Battle context) {

    }

    @Override
    public void update(Battle context, long deltaTime) {
        super.update(context, deltaTime);

        // actions made by the unit
        cooldown-=deltaTime;
        if (cooldown<0){
            new BuffAction(context.getEnnemyArmies(this).get(0).getUnits().get(0), new FireDebuff()).execute();
            cooldown = (long) getStats().getStatisticValue(Statistic.COOLDOWN);
        }
        status();
    }

    @Override
    public void end(Battle context) {

    }
}
