package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.model.world.WorldElement;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WorldView extends JPanel implements PropertyChangeListener {

    public WorldView() {
        //this.setPreferredSize(new Dimension(700,700));
        this.setLayout(new GridLayout(World.NB_TILES_X, World.NB_TILES_Y));//construit une grille de la même taille que le tableau de char
        startObserving();
    }

    public static Color getCorrespondingColor(WorldElement worldElement) {
        Color color;
        switch (worldElement) {
            case WATER:
                color = new Color(0, 150, 255);
                break;
            case DESERT:
                color = new Color(252, 196, 0);
                break;
            case FOREST:
                color = new Color(34, 184, 36);
                break;
            case ROCK:
                color = new Color(111, 111, 106);
                break;
            case LAVA:
                color = new Color(245,85,74);
                break;
            case SNOW:
                color = new Color(209,198,197);
                break;
            default:
                color = new Color(93, 250, 53);
                break;
        }
        return color;
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        Object propertyName = propertyChangeEvent.getPropertyName();
        if (propertyName == World.PROPERTY_RELOAD_MAP){
            repaintWorld();
        }
        this.updateUI();
    }

    public void startObserving(){
        repaintWorld();
        World.addObserver(this, World.PROPERTY_RELOAD_MAP);
    }

    public void stopObserving(){
        World.removeObserver(this, World.PROPERTY_RELOAD_MAP);
    }

    public void repaintWorld(){
        this.removeAll();
        World world = World.getInstance();
        for (int y = 0; y < World.NB_TILES_Y; y++) {
            for (int x = 0; x < World.NB_TILES_X; x++) {
                JLabel worldCase = new JLabel();
                WorldElement worldElement = world.getTile(x, y); //c prend le character du tableau qui est à sa place
                worldCase.setBackground(getCorrespondingColor(worldElement)); // la case est remplie de la couleur correspondante
                worldCase.setOpaque(true);
                this.add(worldCase);
            }
        }
        this.updateUI();

    }
}
