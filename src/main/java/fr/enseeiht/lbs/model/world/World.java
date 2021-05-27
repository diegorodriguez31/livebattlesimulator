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

	public World(int sizeX, int sizeY, int percentDesert, int percentWater, int percentRocks, int percentForest) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		worldElements = new WorldElement[sizeX][sizeY];
		generateWorld(percentDesert, percentWater, percentRocks, percentForest);
	}

	private void generateWorld(final int percentDesert, final int percentWater, int percentRocks, int percentForest) {

		final int nbTiles = sizeX * sizeY;

		// Error detections. If percentages are too bigs, we limit them !!
		if (percentRocks > 33) percentRocks = 33;

		int sumPercent = percentForest+percentDesert+percentWater+percentRocks;
		final int nbDesert = (percentDesert * nbTiles) / sumPercent;
		final int nbWater = (percentWater * nbTiles) / sumPercent;
		final int nbRocks = (percentRocks * nbTiles) / sumPercent;

		// We fill with this element
		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				this.worldElements[x][y]=WorldElement.FOREST;
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
