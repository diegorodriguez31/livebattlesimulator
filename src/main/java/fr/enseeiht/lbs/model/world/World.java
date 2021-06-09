package main.java.fr.enseeiht.lbs.model.world;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that generate a world to use with World
 * You can make different kind of worlds, like plains, mountains, water, desert, rocks etc.
 */
public class World {
    /**
     * Matrix of Floor elements
     */
    private final WorldElement[][] worldElements;

    /**
     * Size X of the map
     */
    private static final int SIZE_X = 50;

    /**
     * Size Y of the map
     */
    private static final int SIZE_Y = 50;


    private WorldElement mainElement;

    private static final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(World.class);

    public static final String PROPERTY_RELOAD_MAP = "Reload";

    private static World instance;

    private World() {
        worldElements = new WorldElement[SIZE_X][SIZE_Y];
    }

    public static World getInstance() {
        if (instance == null) {
            instance = new World();
        }
        return instance;
    }

    public void generateWorld(int percentDesert, int percentWater, int percentRocks, int percentForest, int percentPlain, int percentLava, int percentSnow) {

        final int totalTiles = SIZE_X * SIZE_Y;

        int sumPercent = percentForest + percentDesert + percentWater + percentRocks + percentPlain + percentLava + percentSnow;
        int nbDesert = (percentDesert * totalTiles) / sumPercent;
        int nbWater = (percentWater * totalTiles) / sumPercent;
        int nbRocks = (percentRocks * totalTiles) / sumPercent;
        int nbPlain = (percentPlain * totalTiles) / sumPercent;
        int nbForest = (percentForest * totalTiles) / sumPercent;
        int nbLava = (percentLava * totalTiles) / sumPercent;
        int nbSnow = (percentSnow * totalTiles) / sumPercent;

        //récupère l'élément qui a le plus gros pourcentage
        mainElement = findMainElement(percentDesert, percentWater, percentRocks, percentForest, percentPlain, percentLava, percentSnow);

        //on remplit le tableau avec le mainElement
        for (int x = 0; x < SIZE_X; x++) {
            for (int y = 0; y < SIZE_Y; y++) {
                this.worldElements[x][y] = mainElement;
            }
        }

        //Create the shapes over the world
        createShapes(WorldElement.DESERT, nbDesert);
        createShapes(WorldElement.WATER, nbWater);
        createShapes(WorldElement.PLAIN, nbPlain);
        createShapes(WorldElement.ROCK, nbRocks);
        createShapes(WorldElement.FOREST, nbForest);
        createShapes(WorldElement.LAVA, nbLava);
        createShapes(WorldElement.SNOW, nbSnow);
        affichWorldElements();

        propertyChangeSupport.firePropertyChange(PROPERTY_RELOAD_MAP, null, this.worldElements);
    }

    private void createShapes(final WorldElement value, int nbTiles) {

        if (value != mainElement) {
            boolean[][] cellmap;
            cellmap = initialiseMap(worldElements, value, nbTiles);
            //affichcells(cellmap);
            int numberOfSteps = 10;//nombre de fois que l'algo "game of life" est run
            int deathLimit = 3; //si moins de 2 true adjacents, la cellule devient false
            int birthLimit = 3; //si plus de 4 true à côté, la cellule devient true

            for (int i = 0; i < numberOfSteps; i++) {
                cellmap = doSimulationStep(cellmap, deathLimit, birthLimit);
            }
            //affichcells(cellmap);
            finaliseMap(worldElements, cellmap, value);
        }
    }


    public int countAliveNeighbours(boolean[][] map, int x, int y) {
        int count = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int neighbour_x = x + i;
                int neighbour_y = y + j;
                //If we're looking at the middle point
                if (i == 0 && j == 0) {
                    //Do nothing, we don't want to add ourselves in!
                }
                //In case the index we're looking at it off the edge of the map
                else if (neighbour_x < 0 || neighbour_y < 0 || neighbour_x >= map.length || neighbour_y >= map[0].length) {

                }
                //Otherwise, a normal check of the neighbour
                else if (map[neighbour_x][neighbour_y]) {
                    count = count + 1;
                }
            }
        }
        return count;
    }

    public boolean[][] initialiseMap(WorldElement[][] map, WorldElement elem, float nbtiles) {
        //remplit la map de boolean correctement en fonction des elements deja presents sur le terrain
        int ny = 0;
        boolean[][] newmap = new boolean[SIZE_X][SIZE_Y];
        for (int y = 0; y < SIZE_Y; y++) {
            int nx = 0;
            for (int x = 0; x < SIZE_X; x++) {
                newmap[nx][ny] = elem == map[x][y];
                nx++;
            }
            ny++;
        }
        //met des true à des endroits random en fonction de la proportion donnée de cases
        ny = 0;
        for (int y = 0; y < SIZE_Y; y++) {
            int nx = 0;
            for (int x = 0; x < SIZE_X; x++) {
                double n = Math.random();//entre 0 et 1
                if (n < nbtiles / (SIZE_Y * SIZE_X)) {
                    newmap[nx][ny] = true;
                }
                nx++;
            }
            ny++;
        }

        return newmap;
    }

    public boolean[][] doSimulationStep(boolean[][] oldMap, double deathLimit, double birthLimit) {
        boolean[][] newMap = new boolean[SIZE_X][SIZE_Y];
        //Loop over each row and column of the map
        for (int x = 0; x < oldMap.length; x++) {
            for (int y = 0; y < oldMap[0].length; y++) {
                int nbs = countAliveNeighbours(oldMap, x, y);
                //The new value is based on our simulation rules
                //First, if a cell is alive but has too few neighbours, kill it.
                if (oldMap[x][y]) {
                    newMap[x][y] = !(nbs < deathLimit);
                } //Otherwise, if the cell is dead now, check if it has the right number of neighbours to be 'born'
                else {
                    newMap[x][y] = nbs > birthLimit;
                }
            }
        }
        return newMap;
    }

    //fonction qui place l'élément en cours de shaping dans la map de worldelements
    public void finaliseMap(WorldElement[][] map, boolean[][] oldmap, WorldElement elem) {
        int y = 0;
        for (int oy = 0; oy < SIZE_Y; oy++) {
            int x = 0;
            for (int ox = 0; ox < SIZE_X; ox++) {
                if (oldmap[ox][oy]) {
                    map[x][y] = elem;
                } else {
                    if (map[x][y] == elem) {
                        map[x][y] = mainElement;
                    }
                }
                x++;
            }
            y++;
        }
    }

    //calcul le mainElement pour qu'il ne soit pas shaped dans la map vu que la map est déja remplie de cet element initialement
    public WorldElement findMainElement(int percentDesert, int percentWater, int percentRocks, int percentForest, int percentPlain, int percentLava, int percentSnow) {
        HashMap<WorldElement, Integer> WHmap = new HashMap<>();
        WHmap.put(WorldElement.PLAIN, percentPlain);
        WHmap.put(WorldElement.DESERT, percentDesert);
        WHmap.put(WorldElement.WATER, percentWater);
        WHmap.put(WorldElement.FOREST, percentForest);
        WHmap.put(WorldElement.ROCK, percentRocks);
        WHmap.put(WorldElement.LAVA, percentLava);
        WHmap.put(WorldElement.SNOW, percentSnow);
        WorldElement maxElement = null;
        int max = 0;
        for (Map.Entry<WorldElement, Integer> entry : WHmap.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                maxElement = entry.getKey();
            }
        }
        return maxElement;
    }

    public void affichcells(boolean[][] tab) {
        for (boolean[] characters : tab) {
            for (boolean character : characters) {
                System.out.print(character + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void affichWorldElements() {
        for (int y = 0; y < SIZE_Y; y++) {
            for (int x = 0; x < SIZE_X; x++) {
                System.out.print(worldElements[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println();
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

    public int getSizeX() {
        return SIZE_X;
    }

    public int getSizeY() {
        return SIZE_Y;
    }

    public WorldElement[][] getWorldElements() {
        return worldElements;
    }

    public WorldElement getTile(int xx, int yy) {
        return this.worldElements[xx][yy];
    }

    public WorldElement getMainElement() {
        return this.mainElement;
    }

}

