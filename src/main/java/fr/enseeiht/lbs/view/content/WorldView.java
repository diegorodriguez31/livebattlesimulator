package main.java.fr.enseeiht.lbs.view.content;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.model.world.WorldElement;
import main.java.fr.enseeiht.lbs.view.gui.WorldGUI;

@SuppressWarnings("serial")
public class WorldView extends JPanel implements PropertyChangeListener {

	public WorldView(World world) {
		this.setLayout(new GridLayout(world.getSIZE_X(), world.getSIZE_Y()));//construit une grille de la même taille que le tableau de char
        for (int y = 0; y < world.getSIZE_Y(); y++) {
            for (int x = 0; x < world.getSIZE_X(); x++) {
                JLabel worldCase = new JLabel();
                WorldElement worldElement = world.getTile(x,y); //c prend le character du tableau qui est à sa place 
                worldCase.setBackground(getCorrespondingColor(worldElement)); // la case est remplie de la couleur correspondante
                worldCase.setOpaque(true);
                this.add(worldCase);
            }
        }
        world.addObserver(this, World.PROPERTY_RELOAD_MAP);
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
            default:
            	color = new Color(93,250,53);
            	break;
        }
        return color;
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
	    Object source = propertyChangeEvent.getSource();
        if (source==World.PROPERTY_RELOAD_MAP)
            System.out.println(World.PROPERTY_RELOAD_MAP);
        this.removeAll();
        World world = World.getInstance();
        for (int y = 0; y < world.getSIZE_Y(); y++) {
            for (int x = 0; x < world.getSIZE_X(); x++) {
                JLabel worldCase = new JLabel();
                WorldElement worldElement = world.getTile(x,y); //c prend le character du tableau qui est à sa place
                worldCase.setBackground(getCorrespondingColor(worldElement)); // la case est remplie de la couleur correspondante
                worldCase.setOpaque(true);
                this.add(worldCase);
            }
        }
        //this.revalidate();
        //this.repaint();
        this.updateUI();

    }



}
