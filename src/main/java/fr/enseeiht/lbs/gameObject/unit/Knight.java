package main.java.fr.enseeiht.lbs.gameObject.unit;

import main.java.fr.enseeiht.lbs.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.gameObject.Statistic;
import main.java.fr.enseeiht.lbs.gameObject.unit.action.BuffAction;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.FireDebuff;
import main.java.fr.enseeiht.lbs.gameObject.unit.visitors.ShieldManSBuffVisitor;
import main.java.fr.enseeiht.lbs.gameObject.unit.visitors.StatModifierBuffVisitor;

import static main.java.fr.enseeiht.lbs.gameObject.Statistic.*;
import static main.java.fr.enseeiht.lbs.gameObject.unit.RawStatsManager.*;

public class Knight extends Unit{

    public Knight(double health, double damage, long cooldown, double speed, double range, double accuracy, double agility, double armor) {
        super(health, damage, cooldown, speed, range, accuracy, agility);
        stats.addStat(ARMOR, armor);
    }

    public Knight() {
        this(KNIGHT_HEALTH, KNIGHT_DAMAGE, KNIGHT_COOLDOWN, KNIGHT_SPEED, KNIGHT_RANGE, KNIGHT_ACCURACY,KNIGHT_AGILITY, KNIGHT_ARMOR);
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

    public void status(){
        System.out.println("Infantryman status :");
        System.out.println("health : " + getHealth());
        System.out.println("speed : " + getStats().getStatisticValue(Statistic.SPEED));
        System.out.println("damage : " + getStats().getStatisticValue(Statistic.DAMAGE));
        System.out.println("-----------------------------------------");
    }


    @Override
    public void end(Battle context) {

    }

    @Override
    public void receiveDamage(double damage) {
        double reducedDamage = damage * (getStats().getStatisticValue(ARMOR)/100);
        super.receiveDamage(reducedDamage);
    }

    @Override
    protected StatModifierBuffVisitor getStatVisitor() {
        return new ShieldManSBuffVisitor(stats);
    }
}
