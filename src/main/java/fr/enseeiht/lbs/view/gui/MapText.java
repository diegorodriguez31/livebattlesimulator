package main.java.fr.enseeiht.lbs.view.gui;

import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.model.world.WorldElement;

public class MapText {
	
    public static void writeWorld(World world) {
        for (WorldElement[] worldElems : world.getTab()) {
            for (WorldElement worldElement : worldElems) {
                System.out.print(worldElement.ordinal() + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
