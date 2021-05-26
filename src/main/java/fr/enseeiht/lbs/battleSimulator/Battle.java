package main.java.fr.enseeiht.lbs.battleSimulator;

import main.java.fr.enseeiht.lbs.gameObject.GameObject;
import main.java.fr.enseeiht.lbs.gameObject.unit.Unit;
import main.java.fr.enseeiht.lbs.gameObject.GameObject;
import main.java.fr.enseeiht.lbs.gameObject.unit.Unit;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Battle {

    private long lastTime;

    private PropertyChangeSupport propertyChangeSupport;
    private String propertyGameObjects = "gameObjects";

    Objectif objectif;
    List<Army> armies;
    List<GameObject> objects;
    List<GameObject> endObjects;

    private float deltaTimeMultiplier = 1.0f;

    private Battle() {
    	this.propertyChangeSupport = new PropertyChangeSupport(this);
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
        endObjects = new ArrayList<>();
    }

    public void run(){
        lastTime = System.currentTimeMillis();
        long tempTotal = 0;

        //notify Observers that battle is starting
        this.propertyChangeSupport.firePropertyChange(propertyGameObjects, null, this.objects);

        while(objectif.getWinner(this) == null){
            long deltaTime = System.currentTimeMillis()-lastTime;
            lastTime = System.currentTimeMillis();
            tempTotal += deltaTime;
            System.out.println("delta time" + deltaTime);
            System.out.println("total time" + tempTotal);
            for (GameObject object : objects) {
                object.update(this, (long) (deltaTime * deltaTimeMultiplier));
            }
            for (Iterator<GameObject> it = endObjects.iterator(); it.hasNext();) {
                GameObject o = it.next();
                o.end(this);
                objects.remove(o);
                it.remove();
            }

            //notify Observers
            this.propertyChangeSupport.firePropertyChange(propertyGameObjects, null, this.objects);

            try {
                Thread.sleep(1000/60);
            }catch (InterruptedException e){
                System.err.println(e.getMessage());
            }
        }
    }

    public void setDeltaTimeMultiplier(float deltaTimeMultiplier) {
        this.deltaTimeMultiplier = deltaTimeMultiplier;
    }

    public float getDeltaTimeMultiplier() {
        return deltaTimeMultiplier;
    }

    public void addGameObjectsObserver(PropertyChangeListener propertyChangeListener) {
    	//Only adds the listener once
    	if (! Arrays.asList(propertyChangeSupport.getPropertyChangeListeners(propertyGameObjects)).contains(propertyChangeListener)) {
    		propertyChangeSupport.addPropertyChangeListener(propertyGameObjects, propertyChangeListener);
    	}
	}



    public List<Army> getArmies() {
        return armies;
    }

    public List<Army> getEnnemyArmies(Unit unit) {
        return armies.stream().filter(army -> !army.getUnits().contains(unit)).collect(Collectors.toList());
    }

    public Unit findClosestEnemy(Unit unit){
        return getEnnemyArmies(unit).stream()
                .flatMap(army -> army.getUnits().stream())
                .reduce(getEnnemyArmies(unit).get(0).getUnits().get(0),
                        (unit1, unit2) -> (unit.getPosition().sub(unit1.getPosition()).sqrSize() < unit.getPosition().sub(unit2.getPosition()).sqrSize() || unit2.isDead() ?
                                unit1 :
                                unit2)
                );
    }

    public void addGameObject(GameObject gameObject) {
        objects.add(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        endObjects.add(gameObject);
    }
}
