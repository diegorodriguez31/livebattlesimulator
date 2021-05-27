package main.java.fr.enseeiht.lbs.model.world;

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

	private WorldElement mainElem;

	public World(int sizeX, int sizeY, int percentDesert, int percentWater, int percentRocks, int percentForest) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		worldElements = new WorldElement[sizeX][sizeY];
		generateWorld(percentDesert, percentWater, percentRocks, percentForest);
	}

	private void generateWorld(final int percentDesert, final int percentWater, int percentRocks, int percentForest) {

		final int nbTiles = sizeX * sizeY;
		mainElem = WorldElement.FOREST; //idealement un calcul du plus gros pourcentage

		// Error detections. If percentages are too bigs, we limit them !!
		if (percentRocks > 33) percentRocks = 33;

		int sumPercent = percentForest+percentDesert+percentWater+percentRocks;
		final int nbDesert = (percentDesert * nbTiles) / sumPercent;
		final int nbWater = (percentWater * nbTiles) / sumPercent;
		final int nbRocks = (percentRocks * nbTiles) / sumPercent;

		// We fill with this element
		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				this.worldElements[x][y]=mainElem;
			}
		}

		//Create the shapes over the world
		createShapes(WorldElement.DESERT, nbDesert);


		createShapes(WorldElement.WATER, nbWater);
		createShapes(WorldElement.ROCK, nbRocks);
	}

	private void createShapes(final WorldElement value, int nbTiles) {
		int curentNbTiles = 0;
		int nextShapeSize = 0;
		while (nbTiles > curentNbTiles) {
			nextShapeSize = 1 + random.nextInt(nbTiles - curentNbTiles);
			curentNbTiles = curentNbTiles + ( createRandomShape(value, nextShapeSize));
		}
		boolean[][] cellmap;
		cellmap = initialiseMap(worldElements, value);
		affichcells(cellmap);
		int numberOfSteps = 3;
		int deathLimit = 3;
		int birthLimit = 3;
		for(int i=0; i<numberOfSteps; i++){
			cellmap = doSimulationStep(cellmap, deathLimit, birthLimit);
		}
		affichcells(cellmap);
		finaliseMap(worldElements,cellmap,value,mainElem);
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
		if (maxX >=sizeX) maxX = sizeX-1;
		int maxY = minY + height;
		if (maxY >=sizeY) maxY = sizeY-1;


		// We create the shape
		boolean end = false;
		int y = minY;
		int x = minX;
		while (y <= maxY &&!end) {
			while (x <= maxX &&!end) {
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

	public boolean[][] initialiseMap(WorldElement[][] map, WorldElement elem) {

		int ny = 0;
		boolean[][] newmap = new boolean[sizeX][sizeY];
		for (int y = 0; y < sizeY; y++) {
			int nx = 0;
			for (int x = 0; x < sizeX; x++) {
				if (elem == map[x][y]) {
					newmap[nx][ny] = true;
				}
				else {
					newmap[nx][ny] = false;
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

	public void finaliseMap(WorldElement[][] map, boolean[][] oldmap, WorldElement elem, WorldElement mainElem ){
		int y = 0;
		for (int oy = 0; oy < sizeY; oy++) {
			int x = 0;
			for (int ox = 0; ox < sizeX; ox++) {
				if (oldmap[ox][oy]) {
					map[x][y] = elem;
				}

				else {
					if (map[x][y] == elem){
						map[x][y] = mainElem;
					}
				}
				x++;
			}
			y++;
		}
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
	
	public WorldElement getTile(int xx, int yy){
		return this.worldElements[xx][yy];
	}

}
