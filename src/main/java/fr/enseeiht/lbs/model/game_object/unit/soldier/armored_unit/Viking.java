package main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.armored_unit;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.AttackAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.FlightMovementAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.utils.Vector2;

import static main.java.fr.enseeiht.lbs.model.game_object.Statistic.ARMOR;

public class Viking extends ArmoredUnit {

    private double shield;

    public Viking(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
        ai = new ChargeAndHitAI(new AttackAction(this), new FlightMovementAction(this));
        shield = 100;
    }

    @Override
    public void receiveDamage(double damage) {
        if (hasShield()) {
            shield -= damage;
        } else {
            super.receiveDamage(damage);
        }
    }

    public boolean hasShield() {
        return shield > 0;
    }

    @Override
    public void status() {
        super.status();
        System.out.println("\tShield : " + shield);
        System.out.println("\n");
    }

    @Override
    public void start(Battle context) {

    }

    @Override
    public void end(Battle context) {

    }
}
