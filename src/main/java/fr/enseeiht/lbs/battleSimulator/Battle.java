package main.java.fr.enseeiht.lbs.battleSimulator;

import main.java.fr.enseeiht.lbs.gameObject.unit.action.AttackAction;
import main.java.fr.enseeiht.lbs.gameObject.unit.action.BuffAction;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.FreezeDebuff;
import main.java.fr.enseeiht.lbs.gameObject.unit.Infantryman;
import main.java.fr.enseeiht.lbs.gameObject.unit.Shieldman;

public class Battle {

    public void run() {
        Infantryman attaquant = new Infantryman(100, 1, 10);
        attaquant.status();

        Shieldman victime = new Shieldman(100, 1, 10, 50);
        victime.status();
        System.out.println("Init OK\n");

        new AttackAction(attaquant, victime).execute();

        System.out.println("Victime attaquée !\n");
        victime.status();

        new BuffAction(victime, new FreezeDebuff()).execute();
        System.out.println("Victime FIRE débuff !\n");
        victime.status();

        victime.update(this, 1);
        victime.status();

        victime.update(this, 1);
        victime.status();

        victime.update(this, 1);
        victime.status();

        new BuffAction(attaquant, new FreezeDebuff()).execute();
        System.out.println("Freeze sur attquant débuff !\n");
        attaquant.status();
    }
}
