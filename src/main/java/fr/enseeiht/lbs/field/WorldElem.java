package main.java.fr.enseeiht.lbs.field;

import java.awt.*;
import java.util.HashMap;

public enum WorldElem {

    @Ascii('O') PLAIN,
    @Ascii('1') DESERT,
    @Ascii('2') WATER,
    @Ascii('3') FOREST,
    @Ascii('A') ROCK;


    /** Map allowing to retrieve a WorldElem when we have the associated char */

    public static HashMap<Character, WorldElem> mapAscii;

     //Initialization of the map
    static {
        mapAscii = new HashMap<>();
        for(WorldElem elem : WorldElem.class.getEnumConstants()) {
            Character curChar = null;
            try {
                curChar = elem.getClass().getField(elem.name()).getAnnotation(Ascii.class).value();
            } catch (Exception e) {
                curChar = ' ';
            }
            mapAscii.put(curChar, elem);
        }
    }


    /**
     *  Give the ascii char associated with the WorldElem
     * @param elem the element to check
     * @return the corresponding char, or ' ' if it was not found
     */
    public char getValue(WorldElem elem) {
        Character curChar = null;
        try {
            curChar = elem.getClass().getField(elem.name()).getAnnotation(Ascii.class).value();
        } catch (Exception e) {
            curChar = ' ';
        }
        return curChar;
    }

    public static WorldElem getValueChar(char elem) {
        return mapAscii.get(elem);
    }





}