package main.java.fr.enseeiht.lbs.model.world;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
    private final int sizeX;

    /**
     * Size Y of the game
     */
    private final int sizeY;

    private static final Random random = new Random();

    private WorldElement mainElement;

    public World(int sizeX, int sizeY, int percentDesert, int percentWater, int percentRocks, int percentForest, int percentPlain) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        worldElements = new WorldElement[sizeX][sizeY];
        generateWorld(percentDesert, percentWater, percentRocks, percentForest, percentPlain);
    }

    private void generateWorld(final int percentDesert, final int percentWater, int percentRocks, int percentForest, int percentPlain) {

        final int totalTiles = sizeX * sizeY;

        mainElement = getMainElement(percentDesert, percentWater, percentRocks, percentForest, percentPlain); //idealement un calcul du plus gros pourcentage

        // Error detections. If percentages are too bigs, we limit them !!
        if (percentRocks > 33) percentRocks = 33;

        int sumPercent = percentForest + percentDesert + percentWater + percentRocks + percentPlain;
        final int nbDesert = (percentDesert * totalTiles) / sumPercent;
        final int nbWater = (percentWater * totalTiles) / sumPercent;
        final int nbRocks = (percentRocks * totalTiles) / sumPercent;
        final int nbPlain = (percentPlain * totalTiles) / sumPercent;
        final int nbForest = (percentForest * totalTiles) / sumPercent;
        // We fill with this element

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                this.worldElements[x][y] = mainElement;
            }
        }


        //Create the shapes over the world
        createShapes(WorldElement.DESERT, nbDesert);
        createShapes(WorldElement.WATER, nbWater);
        //createShapes(WorldElement.PLAIN, nbPlain);
        createShapes(WorldElement.ROCK, nbRocks);
        //createShapes(WorldElement.FOREST, nbForest);
    }

    private void createShapes(final WorldElement value, int nbTiles) {

		if (value != mainElement) {
			boolean[][] cellmap;
			cellmap = initialiseMap(worldElements, value, nbTiles);
			affichcells(cellmap);
			int numberOfSteps = 7;
			int deathLimit = 2;
			int birthLimit = 4;

			for (int i = 0; i < numberOfSteps; i++) {
				cellmap = doSimulationStep(cellmap, deathLimit, birthLimit);
			}
			affichcells(cellmap);
			finaliseMap(worldElements, cellmap, value);
		}
	}

    private int createRandomShape(final WorldElement backElem, final int maxTiles) {
        // We don't create shape if there is no need
        if (maxTiles <= 0)
            return 0;

        // Initialization
        int nbTiles = 0;
        int ratio = Double.valueOf(Math.sqrt(maxTiles)).intValue();
        int width = random.nextInt(ratio * 2);
        if (width <= 1) width = 4;
        int height = maxTiles / width;
        if (height <= 1) height = 4;

        final int minX = random.nextInt(sizeX);
        final int minY = random.nextInt(sizeY);
        int maxX = minX + width;
        if (maxX >= sizeX) maxX = sizeX - 1;
        int maxY = minY + height;
        if (maxY >= sizeY) maxY = sizeY - 1;


        // We create the shape
        boolean end = false;
        int y = minY;
        int x = minX;
        while (y <= maxY && !end) {
            while (x <= maxX && !end) {
                if (nbTiles >= maxTiles) {
                    end = true;
                }
                // We add the new element only if there isn't already the same
                if (x >= 0 && x < sizeX || y >= 0 && y < sizeY) {
                    this.worldElements[x][y] = backElem;
                    x++;
                    nbTiles++;
                }
            }
            y++;
        }
        return nbTiles;
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

    public boolean[][] initialiseMap(WorldElement[][] map, WorldElement elem, float Nbtiles) {

        int ny = 0;
        boolean[][] newmap = new boolean[sizeX][sizeY];
        for (int y = 0; y < sizeY; y++) {
            int nx = 0;
            for (int x = 0; x < sizeX; x++) {
                if (elem == map[x][y]) {
                    newmap[nx][ny] = true;
                } else {
                    newmap[nx][ny] = false;
                }
                nx++;
            }
            ny++;
        }
        ny = 0;
        for (int y = 0; y < sizeY; y++) {
            int nx = 0;
            for (int x = 0; x < sizeX; x++) {
                double n = Math.random();
                if (n < Nbtiles / (sizeY * sizeX)) {
                    newmap[nx][ny] = true;
                }
                nx++;
            }
            ny++;
        }

        return newmap;
    }

    public boolean[][] doSimulationStep(boolean[][] oldMap, double deathLimit, double birthLimit) {
        boolean[][] newMap = new boolean[sizeX][sizeY];
        //Loop over each row and column of the map
        for (int x = 0; x < oldMap.length; x++) {
            for (int y = 0; y < oldMap[0].length; y++) {
                int nbs = countAliveNeighbours(oldMap, x, y);
                //The new value is based on our simulation rules
                //First, if a cell is alive but has too few neighbours, kill it.
                if (oldMap[x][y]) {
                    if (nbs < deathLimit) {
                        newMap[x][y] = false;
                    } else {
                        newMap[x][y] = true;
                    }
                } //Otherwise, if the cell is dead now, check if it has the right number of neighbours to be 'born'
                else {
                    if (nbs > birthLimit) {
                        newMap[x][y] = true;
                    } else {
                        newMap[x][y] = false;
                    }
                }
            }
        }
        return newMap;
    }

    public void finaliseMap(WorldElement[][] map, boolean[][] oldmap, WorldElement elem) {
        int y = 0;
        for (int oy = 0; oy < sizeY; oy++) {
            int x = 0;
            for (int ox = 0; ox < sizeX; ox++) {
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

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public WorldElement[][] getWorldElements() {
        return worldElements;
    }

    public WorldElement getTile(int xx, int yy) {
        return this.worldElements[xx][yy];
    }

}
