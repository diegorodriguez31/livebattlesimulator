package main.java.fr.enseeiht.lbs.battleSimulator;

import main.java.fr.enseeiht.lbs.gameObject.Entity;
import main.java.fr.enseeiht.lbs.gameObject.GameObject;
import main.java.fr.enseeiht.lbs.gameObject.unit.Unit;
import main.java.fr.enseeiht.lbs.gameObject.unit.action.AttackAction;
import main.java.fr.enseeiht.lbs.gameObject.unit.action.BuffAction;
import main.java.fr.enseeiht.lbs.gameObject.unit.buff.FreezeDebuff;
import main.java.fr.enseeiht.lbs.gameObject.unit.Infantryman;
import main.java.fr.enseeiht.lbs.gameObject.unit.Shieldman;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Battle {

    private long lastTime;

    Objectif objectif;
    List<Army> armies;
    List<GameObject> objects;

    private float speedMut = 1;

    private Battle() {
    }

    static Battle instance;

    public static Battle getInstance(){
        if (instance == null) {
            instance = new Battle();
        }
        return instance;
    }

    public void init(Objectif objectif, List<Army> armies){
        this.objectif = objectif;
        this.armies = armies;
        objects = new ArrayList<>();
    }

    public void run(){
        lastTime = System.currentTimeMillis();
        long tempTotal = 0;
        while(objectif.getWinner(this) == null){
            long deltaTime = System.currentTimeMillis()-lastTime;
            lastTime = System.currentTimeMillis();
            tempTotal += deltaTime;
            System.out.println("delta time" + deltaTime);
            System.out.println("total time" + tempTotal);
            Iterator<GameObject> it = objects.stream().iterator();
            while(it.hasNext()){
                it.next().update(this, (long) (deltaTime*speedMut));
            }

            try {
                Thread.sleep(1000/60);
            }catch (InterruptedException e){
                System.err.println(e.getMessage());
            }
        }
    }

    public void setSpeedMut(float speedMut) {
        this.speedMut = speedMut;
    }

    public float getSpeedMut() {
        return speedMut;
    }

    /*public void run2() {
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
    }*/

    public List<Army> getArmies() {
        return armies;
    }

    public List<Army> getEnnemyArmies(Unit unit) {
        return armies.stream().filter(army -> !army.getUnits().contains(unit)).collect(Collectors.toList());
    }

    public void addGameObject(GameObject gameObject) {
        objects.add(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        objects.remove(gameObject);
    }
}
