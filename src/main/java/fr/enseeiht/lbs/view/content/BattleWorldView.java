package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.model.world.WorldElement;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BattleWorldView extends BattleView implements PropertyChangeListener {

    public BattleWorldView() {
        super();
        World world = World.getInstance();
        this.setLayout(new GridLayout(world.getSizeX(), world.getSizeY()));//construit une grille de la même taille que le tableau de char
        this.setPreferredSize(new Dimension(700, 700));
        this.startObserving();
        this.updateWorldRepresentation();
        this.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        super.propertyChange(propertyChangeEvent);
        if (propertyChangeEvent.getPropertyName().equals(World.PROPERTY_RELOAD_MAP)) {
            updateWorldRepresentation();
        }
    }

    private void updateWorldRepresentation() {
        World world = World.getInstance();
        removeAll();
        for (int y = 0; y < world.getSizeY(); y++) {
            for (int x = 0; x < world.getSizeX(); x++) {
                JLabel worldCase = new JLabel();
                WorldElement worldElement = world.getTile(x, y); // prend le character du tableau qui est à sa place
                worldCase.setBackground(WorldView.getCorrespondingColor(worldElement)); // la case est remplie de la couleur correspondante
                worldCase.setOpaque(true);
                this.add(worldCase);
            }
        }
    }

    public void startObserving(){
        super.startObserving();
        World.addObserver(this, World.PROPERTY_RELOAD_MAP);
        updateWorldRepresentation();
    }

    public void stopObserving(){
        super.stopObserving();
        World.removeObserver(this, World.PROPERTY_RELOAD_MAP);
    }

}
