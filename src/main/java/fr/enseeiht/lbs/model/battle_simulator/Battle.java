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
import java.util.stream.Stream;

import static main.java.fr.enseeiht.lbs.LiveBattleSimulator.VERBOSE;

/**
 * Définit le fonctionnement d'une bataille lors d'une simulation.
 * C'est un singleton.
 */
public class Battle {

    /**
     * Constante permetant de limiter le nombre de mise a jour par seconde
     */
    public static final int DELTA_TIME_MIN = 1000 / 60;
    
    private String name;

    /**
     * Objet permetant d'envoyer les events vis a vis de la battaile
     * En dessous il y a les identifiants des types d'event
     */
    private final static PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(Battle.class);
    public static final String PROPERTY_GAME_OBJECTS = "gameObjects";
    public static final String PROPERTY_RESULTS = "results";

    Objectif objectif;
    List<Army> armies;

    /**
     * Les game objetcs (unités, bâtiments, ...) sont manipulés par ces listes
     * lors du déroulement du cycle de vie de la bataille.
     */
    List<GameObject> objects;
    List<GameObject> startObjects;
    List<GameObject> endObjects;

    /**
     * Multiplicateurs de vitesse de déroulement de bataille.
     */
    public static final float DEFAULT_DELTA_TIME_MULTIPLIER = 1.0f;
    public static final float STOPPED_DELTA_TIME_MULTIPLIER = 0.00f;
    public static final float MAX_DELTA_TIME_MULTIPLIER = 10.0f;
    public static final float MIN_DELTA_TIME_MULTIPLIER = 0.01f;

    private float deltaTimeMultiplier = STOPPED_DELTA_TIME_MULTIPLIER;


    private Battle() {
        this.armies = new ArrayList<>();
        this.startObjects = new ArrayList<>();
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
        startObjects = new ArrayList<>();
        objects = new ArrayList<>();
        endObjects = new ArrayList<>();
        propertyChangeSupport.firePropertyChange(Battle.PROPERTY_GAME_OBJECTS, null, objects);
    }

    /**
     * Starts the battle in a new thread and check if the battle state is valid
     *
     * @throws InvalidBattleStateException thrown if no unit is present
     */
    public void runAsync() throws InvalidBattleStateException {
        if (armies.stream().mapToLong(army -> army.getUnits().size()).sum() == 0) {
            throw new InvalidBattleStateException("Cant start a battle without any unit in it");
        }
        new Thread(this::run).start();
    }

    /**
     * Contains the main game loop
     */
    private void run() {
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
            for (Iterator<GameObject> it = startObjects.iterator(); it.hasNext(); ) {
                GameObject o = it.next();
                o.start(this);
                objects.add(o);
                it.remove();
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

            if (System.currentTimeMillis() - lastTime < DELTA_TIME_MIN) {
                try {
                    Thread.sleep(-System.currentTimeMillis() + lastTime + DELTA_TIME_MIN);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
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

    /**
     * Cherche tous les alliés de l'unité courante et la liste retournée est triée
     * par rapport à la distance qui les sépare de l'unité courante
     *
     * @param unit l'unité qui cherche ses alliés
     * @return la liste des alliés de l'unité
     */
    public List<Unit> findAllies(Unit unit) {
        List<Unit> allies = unit.getTeam().getUnits().stream()
                .filter(itUnit -> !itUnit.isDead()).collect(Collectors.toList());
        return sortAlliesByDistance(allies, unit);
    }

    /**
     * Trie la liste d'alliés par rapport à leur position respective qui les sépare de l'unité donnée en paramètre
     *
     * @param units la liste d'alliés
     * @param unit  l'unité qui cherche ses alliés
     * @return la liste d'alliés triés par rapport à leur position respective qui les sépare de l'unité donnée en paramètre
     */
    public List<Unit> sortAlliesByDistance(List<Unit> units, Unit unit) {
        Stream<Unit> unitDistanceCompare = units.stream()
                .sorted((unit1, unit2)
                        -> Float.compare(unit1.getPosition().sub(unit.getPosition()).sqrSize(), unit2.getPosition().sub(unit.getPosition()).sqrSize()));
        return unitDistanceCompare.collect(Collectors.toList());
    }

    public void addGameObject(GameObject gameObject) {
        startObjects.add(gameObject);
        propertyChangeSupport.firePropertyChange(PROPERTY_GAME_OBJECTS, null, getObjects());
    }

    public void removeGameObject(GameObject gameObject) {
        endObjects.add(gameObject);
        propertyChangeSupport.firePropertyChange(PROPERTY_GAME_OBJECTS, null, getObjects());
    }

    public List<GameObject> getObjects() {
        List<GameObject> listObjects = new ArrayList<>();
        listObjects.addAll(objects);
        listObjects.addAll(startObjects);
        return listObjects;
    }
}
