package main.java.fr.enseeiht.lbs.model.gameObject.unit;

import main.java.fr.enseeiht.lbs.model.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.model.gameObject.Statistic;
import main.java.fr.enseeiht.lbs.model.gameObject.Vector2;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.action.AttackAction;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.buff.Buff;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.visitors.ShieldManSBuffVisitor;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.visitors.StatModifierBuffVisitor;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.visitors.TicBuffVisitor;

public class Shieldman extends Infantryman {

    public Shieldman(Vector2 position, double health, double speed, double damage, double armor, long cooldown) {
        super(position, health, speed, damage, cooldown);
        stats.addStat(Statistic.ARMOR, armor);
    }

    @Override
    public void status(){
        System.out.println("Shieldman status :");
        System.out.println("health : " + getHealth());
        System.out.println("armure : " + getStats().getStatisticValue(Statistic.ARMOR));
        System.out.println("speed : " + getStats().getStatisticValue(Statistic.SPEED));
        System.out.println("damage : " + getStats().getStatisticValue(Statistic.DAMAGE));
        System.out.println("-----------------------------------------");
    }

    @Override
    public void update(Battle context, long deltaTime) {
        TicBuffVisitor visitor = getUpdateVisitor(deltaTime);
        for (Buff buff : buffs) {
            buff.accept(visitor);
        }

        // actions made by the unit
        cooldown-=deltaTime;
        if (cooldown<0){
            new AttackAction(this, context.getEnnemyArmies(this).get(0).getUnits().get(0)).execute();
            cooldown = (long) getStats().getStatisticValue(Statistic.COOLDOWN);
        }
        status();
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
