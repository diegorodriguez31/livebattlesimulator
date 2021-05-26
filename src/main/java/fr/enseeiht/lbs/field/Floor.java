package main.java.fr.enseeiht.lbs.field;

import java.awt.Color;

public class Floor {


    /** Type of the background (forest, desert, water...) */
    private WorldElem backgroundType;

    public Floor(WorldElem backgroundType) {//constructeur
        this.backgroundType= backgroundType;
    }
    /**
            * Get the type of the element
     * @return  the type
     */
    public WorldElem getType() {
        return backgroundType;
    }

    public Color getColor(){//affecte la couleur avec un case, à voir pour modifier si on fait de l'héritage
        Color color = new Color(93,250,53);
        switch (backgroundType) {
            case WATER:
                color = new Color(0, 0, 255);
                break;
            case DESERT:
                color = new Color(252, 196, 0);
                break;
            case FOREST:
                color = new Color(34, 184, 36);
                break;
            case ROCK:
                color = new Color(138, 86, 21);
                break;
            default:
        }
        return color;

    }
}
