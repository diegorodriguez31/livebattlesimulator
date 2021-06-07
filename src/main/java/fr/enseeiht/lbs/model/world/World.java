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
     * Size X of the game
     */
    private static final int SIZE_X = 20;

    /**
     * Size Y of the game
     */
    private static final int SIZE_Y = 20;


    private WorldElement mainElement;

    private final PropertyChangeSupport propertyChangeSupport;

    public static final String PROPERTY_RELOAD_MAP = "Reload";

    private static World instance;

    private World() {
        worldElements = new WorldElement[SIZE_X][SIZE_Y];
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public static World getInstance() {
        if (instance == null) {
            instance = new World();
        }
        return instance;
    }

    public void generateWorld(final int percentDesert, final int percentWater, int percentRocks, int percentForest, int percentPlain) {

        final int totalTiles = SIZE_X * SIZE_Y;

        int sumPercent = percentForest + percentDesert + percentWater + percentRocks + percentPlain;
        final int nbDesert = (percentDesert * totalTiles) / sumPercent;
        final int nbWater = (percentWater * totalTiles) / sumPercent;
        final int nbRocks = (percentRocks * totalTiles) / sumPercent;
        final int nbPlain = (percentPlain * totalTiles) / sumPercent;
        final int nbForest = (percentForest * totalTiles) / sumPercent;

        //récupère l'élément qui a le plus gros pourcentage
        mainElement = getMainElement(percentDesert, percentWater, percentRocks, percentForest, percentPlain);

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

        propertyChangeSupport.firePropertyChange(PROPERTY_RELOAD_MAP, null, this.worldElements);
    }

    private void createShapes(final WorldElement value, int nbTiles) {

        if (value != mainElement) {
            boolean[][] cellmap;
            cellmap = initialiseMap(worldElements, value, nbTiles);
            //affichcells(cellmap);
            int numberOfSteps = 7;//nombre de fois que l'algo "game of life" est run
            int deathLimit = 2; //si moins de 2 true adjacents, la cellule devient false
            int birthLimit = 4; //si plus de 4 true à côté, la cellule devient true

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

        ny = 0;
        for (int y = 0; y < SIZE_Y; y++) {
            int nx = 0;
            for (int x = 0; x < SIZE_X; x++) {
                double n = Math.random();
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

    public WorldElement getMainElement(int percentDesert, int percentWater, int percentRocks, int percentForest, int percentPlain) {
        HashMap<WorldElement, Integer> WHmap = new HashMap<>();
        WHmap.put(WorldElement.PLAIN, percentPlain);
        WHmap.put(WorldElement.DESERT, percentDesert);
        WHmap.put(WorldElement.WATER, percentWater);
        WHmap.put(WorldElement.FOREST, percentForest);
        WHmap.put(WorldElement.ROCK, percentRocks);
        WorldElement maxElement = null;
        int max = 0;
        for (Map.Entry<WorldElement, Integer> entry : WHmap.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                maxElement = entry.getKey();
            }
        }
        System.out.println(maxElement);
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

    public void addObserver(PropertyChangeListener propertyChangeListener, String propertyName) {
        //Only adds the listener once
        if (!Arrays.asList(propertyChangeSupport.getPropertyChangeListeners(propertyName)).contains(propertyChangeListener)) {
            propertyChangeSupport.addPropertyChangeListener(propertyName, propertyChangeListener);
        }
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

}

