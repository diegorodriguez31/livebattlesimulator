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

import static main.java.fr.enseeiht.lbs.LiveBattleSimulator.VERBOSE;


public class Battle {


    private String name;

    private final static PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(Battle.class);
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
        this.armies = new ArrayList<>();
        this.objects = new ArrayList<>();
        this.endObjects = new ArrayList<>();
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
     *
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
        propertyChangeSupport.firePropertyChange(Battle.PROPERTY_GAME_OBJECTS, null, objects);
    }

    public void run() {
        long lastTime = System.currentTimeMillis();
        long tempTotal = 0;

        //notify Observers that battle is starting
        propertyChangeSupport.firePropertyChange(PROPERTY_GAME_OBJECTS, null, this.objects);

        while (objectif.getWinner(this) == null) {
            long deltaTime = System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            tempTotal += deltaTime;
            if (VERBOSE >= 1) {
                System.out.println("delta time" + deltaTime);
                if (VERBOSE >= 2) {
                    System.out.println("total time" + tempTotal);
                }
            }
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
            propertyChangeSupport.firePropertyChange(PROPERTY_GAME_OBJECTS, null, this.objects);

            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
        Army winner = objectif.getWinner(this);
        setDeltaTimeMultiplier(STOPPED_DELTA_TIME_MULTIPLIER);

        // Notify observers that the battle is finished
        propertyChangeSupport.firePropertyChange(PROPERTY_RESULTS, null, winner);
    }

    public void setDeltaTimeMultiplier(float deltaTimeMultiplier) {
        this.deltaTimeMultiplier = deltaTimeMultiplier;
    }

    public float getDeltaTimeMultiplier() {
        return deltaTimeMultiplier;
    }

    public static void addObserver(PropertyChangeListener propertyChangeListener, String propertyName) {
        //Only adds the listener once
        if (!Arrays.asList(propertyChangeSupport.getPropertyChangeListeners(propertyName)).contains(propertyChangeListener)) {
            propertyChangeSupport.addPropertyChangeListener(propertyName, propertyChangeListener);
        }
    }

    public static void removeObserver(PropertyChangeListener propertyChangeListener, String propertyName) {
        propertyChangeSupport.removePropertyChangeListener(propertyName, propertyChangeListener);
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
        propertyChangeSupport.firePropertyChange(PROPERTY_GAME_OBJECTS, null, this.objects);
    }

    public void removeGameObject(GameObject gameObject) {
        endObjects.add(gameObject);
        propertyChangeSupport.firePropertyChange(PROPERTY_GAME_OBJECTS, null, this.objects);
    }

    public List<GameObject> getObjects() {
        return objects;
    }
}
