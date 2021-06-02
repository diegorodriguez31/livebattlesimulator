package main.java.fr.enseeiht.lbs.model.game_object.unit.soldier;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game_object.Statistic;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.Vector2;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.AttackAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.FlightMovementAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.ai.ChargeAndHitAI;

public class Infantryman extends Unit {

    public Infantryman(String name, Vector2 position, double health, double speed, double damage, long cooldown, double reach) {
        super(name, health, position);

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
