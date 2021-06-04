package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.model.world.WorldElement;
import main.java.fr.enseeiht.lbs.view.adapter.GraphicalEntity;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;

@SuppressWarnings("serial")
public class BattleWorldView extends BattleView implements PropertyChangeListener {

    private static final int WORLD_TO_PIXEL = 11;

    public BattleWorldView() {
        super();
        World world = World.getInstance();
        this.setLayout(new GridLayout(world.getSizeX(), world.getSizeY()));//construit une grille de la même taille que le tableau de char
        this.setVisible(true);


        Battle.getInstance().addObserver(this, Battle.PROPERTY_GAME_OBJECTS);
        Battle.getInstance().addObserver(this, Battle.PROPERTY_RESULTS);

        World.getInstance().addObserver(this, World.PROPERTY_RELOAD_MAP);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (propertyChangeEvent.getPropertyName().equals(World.PROPERTY_RELOAD_MAP)) {
            updateWorld();
            return;
        }
        super.propertyChange(propertyChangeEvent);
    }

    public void updateWorld() {
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
        updateUI();
    }
}
