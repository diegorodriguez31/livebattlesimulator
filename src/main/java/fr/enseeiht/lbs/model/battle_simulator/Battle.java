package main.java.fr.enseeiht.lbs.model.battle_simulator;


import main.java.fr.enseeiht.lbs.model.game_object.GameObject;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Battle {

    private long lastTime;
    private String name;

    private PropertyChangeSupport propertyChangeSupport;
    public static final String PROPERTY_GAME_OBJECTS = "gameObjects";
    public static final String PROPERTY_RESULTS = "results";

    Objectif objectif;
    List<Army> armies;
    List<GameObject> objects;
    List<GameObject> endObjects;

    public static final float DEFAULT_DELTA_TIME_MULTIPLIER = 1.0f;
    public static final float STOPPED_DELTA_TIME_MULTIPLIER = 0.00f;
    public static final float MAX_DELTA_TIME_MULTIPLIER = 10.0f;
    public static final float MIN_DELTA_TIME_MULTIPLIER = 0.01f;
    
    private float deltaTimeMultiplier = STOPPED_DELTA_TIME_MULTIPLIER;
    

    private Battle() {
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    static Battle instance;

    public static Battle getInstance() {
        if (instance == null) {
            instance = new Battle();
        }
        return instance;
    }

    /**
     * Init battle, does not create Units.
     * @param objectif the objectif of the game
     * @param nbArmies the number of armies to create
     */
    public void init(Objectif objectif, int nbArmies) {
        this.objectif = objectif;
        this.armies = new ArrayList<>();
        for (int armyIndex = 0; armyIndex < nbArmies; armyIndex++) {
            this.armies.add(new Army(armyIndex));
        }
        objects = new ArrayList<>();
        endObjects = new ArrayList<>();
    }

    public static void reset() {
        instance = null;
    }

    public void run() {
        lastTime = System.currentTimeMillis();
        long tempTotal = 0;

        //notify Observers that battle is starting
        this.propertyChangeSupport.firePropertyChange(PROPERTY_GAME_OBJECTS, null, this.objects);

        while (objectif.getWinner(this) == null) {
            long deltaTime = System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            tempTotal += deltaTime;
            System.out.println("delta time" + deltaTime);
            System.out.println("total time" + tempTotal);
            for (GameObject object : objects) {
                object.update(this, (long) (deltaTime * deltaTimeMultiplier));
            }
            for (Iterator<GameObject> it = endObjects.iterator(); it.hasNext(); ) {
                GameObject o = it.next();
                o.end(this);
                objects.remove(o);
                it.remove();
            }

            //notify Observers
            this.propertyChangeSupport.firePropertyChange(PROPERTY_GAME_OBJECTS, null, this.objects);

            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
        Army winner = objectif.getWinner(this);
        setDeltaTimeMultiplier(STOPPED_DELTA_TIME_MULTIPLIER);

        // Notify observers that the battle is finished
        this.propertyChangeSupport.firePropertyChange(PROPERTY_RESULTS, null, winner);
    }

    public void setDeltaTimeMultiplier(float deltaTimeMultiplier) {
        this.deltaTimeMultiplier = deltaTimeMultiplier;
    }

    public float getDeltaTimeMultiplier() {
        return deltaTimeMultiplier;
    }

    public void addObserver(PropertyChangeListener propertyChangeListener, String propertyName) {
        //Only adds the listener once
        if (!Arrays.asList(propertyChangeSupport.getPropertyChangeListeners(propertyName)).contains(propertyChangeListener)) {
            propertyChangeSupport.addPropertyChangeListener(propertyName, propertyChangeListener);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Army> getArmies() {
        return armies;
    }

    public List<Army> getEnemyArmies(Unit unit) {
        return armies.stream().filter(army -> !army.getUnits().contains(unit)).collect(Collectors.toList());
    }

    public Unit findClosestEnemy(Unit unit) {
        return getEnemyArmies(unit).stream()
                .flatMap(army -> army.getUnits().stream())
                .reduce(null,
                        (selected, it) -> {
                            if (it.isDead()) return selected;
                            if (selected == null || it.getPosition().sub(unit.getPosition()).sqrSize() < selected.getPosition().sub(unit.getPosition()).sqrSize())
                                return it;
                            return selected;
                        }
                );
    }

    public void addGameObject(GameObject gameObject) {
        objects.add(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        endObjects.add(gameObject);
    }
}
