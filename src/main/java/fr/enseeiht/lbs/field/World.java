package main.java.fr.enseeiht.lbs.field;

/**
 * Class that generate a world to use with World
 * You can make different kind of worlds, like plains, mountains, water, desert, rocks etc.
 */
public class World {
    /**
     * Matrix of Floor elements
     */
    private final Character[][] tab;

    /**
     * Size X of the game
     */
    private final int sizeX;

    /**
     * Size Y of the game
     */
    private final int sizeY;

    public World(int sizeX, int sizeY, int percentDesert, int percentWater, int percentRocks, int percentForest) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        tab = new Character[sizeY][sizeX];
        generateWorld(percentDesert, percentWater, percentRocks, percentForest);


    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public Character[][] getTab() {
        return tab;
    }
    public char getCase(Character[][] tab, int xx, int yy){
        return tab[xx][yy];
    }
    public static int max(int... n) {
        int i = 0;
        int max = n[i];

        while (++i < n.length)
            if (n[i] > max)
                max = n[i];

        return max;
    }
    private void generateWorld(final int percentDesert, final int percentWater, int percentRocks, int percentForest) {

        final int nbTiles = sizeX * sizeY;

        // Error detections. If percentages are too bigs, we limit them !!
        if (percentRocks > 33) percentRocks = 33;

        final int nbDesert = (percentDesert * nbTiles) / 100;
        final int nbWater = (percentWater * nbTiles) / 100;
        final int nbRocks = (percentRocks * nbTiles) / 100;
        final int nbForest = (percentForest * nbTiles) / 100;
        // What is the most important element ? Grass or desert ?
        WorldElem defValue;
        WorldElem whatNext;
        int qtyWhatNext;

        if (percentDesert > 50) {
            defValue = WorldElem.mapAscii.get('1');
            whatNext = WorldElem.mapAscii.get('3');
            qtyWhatNext = nbTiles - nbDesert;
        } else{
            defValue = WorldElem.mapAscii.get('3');
            whatNext = WorldElem.mapAscii.get('1');
            qtyWhatNext = nbTiles - nbForest;
        }

        // We fill with this element

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                this.tab[y][x] = defValue.getValue(defValue);
            }
        }

        // And we builds some shapes of all the others elements.
        //The main element after the most important (forest or desert)
        System.out.println("water " + nbWater);
        System.out.println("foret " + nbForest);
        System.out.println("rock "+nbRocks);
        System.out.println("desert " + nbDesert);
        createShapes(whatNext, qtyWhatNext);
        // We add special elements elements
        createShapes(WorldElem.mapAscii.get('2'), nbWater);
        createShapes(WorldElem.mapAscii.get('A'), nbRocks);
        affichtab(tab);
    }

    private void createShapes(final WorldElem value, int nbTiles) {
        int curNb = 0;
        while (nbTiles > 0) {
                curNb = 1+ Randomizer.getInt(nbTiles);
                nbTiles = nbTiles - ( createRandomShape(value, curNb));
        }
    }

    private int createRandomShape(final WorldElem backElem, final int maxTiles) {
        // We don't create shape if there is no need
        if (maxTiles <= 0)
            return 0;

        // Initialization
        int nbTiles = 0;
        int ratio = new Double(Math.sqrt(maxTiles)).intValue();
        int width = Randomizer.getInt(ratio * 2);
        if (width <= 1) width = 4;
        int height = maxTiles / width;
        if (height <= 1) height = 4;

        final int minX = Randomizer.getInt(sizeX);
        final int minY = Randomizer.getInt(sizeY);
        Point point = new Point(minX,minY);
         int maxX = minX + width;
        if (maxX >=sizeX) maxX = sizeX-1;
         int maxY = minY + height;
        if (maxY >=sizeY) maxY = sizeY-1;


        // We create the shape
        boolean end = false;
        int y = minY;
        int x = minX;
        while (y < maxY &&!end) {

            while (x < maxX &&!end) {
                if (nbTiles >= maxTiles) {
                    end = true;
                }
                // We add the new element only if there isn't already the same
                if (x >= 0 && x < sizeX || y >= 0 && y < sizeY) {
                    tab[y][x] = backElem.getValue(backElem);
                    x++;
                    nbTiles++;

                }
            }

            y++;
        }
        return nbTiles;
    }

    /**
     * Give the matrix
     *
     * @return the matrix
     */
    /*
    Character[][] getWorld() {
        return tab;
    }
    */

    public void affichtab(Character[][] tab) {
        for (Character[] characters : tab) {
            for (Character character : characters) {
                System.out.print(character + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }


}
