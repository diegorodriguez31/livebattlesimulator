package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.model.world.WorldElement;
import main.java.fr.enseeiht.lbs.view.adapter.GraphicalEntity;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public class BattleWorldView extends BattleView implements PropertyChangeListener {

    private List<GraphicalEntity> graphicalEntities;

    public BattleWorldView(World world) {
        this.graphicalEntities = new LinkedList<>();
        this.setLayout(new GridLayout(world.getSizeX(), world.getSizeY()));// construit une grille de la même taille que le tableau de char
        this.setVisible(true);

        for (int x = 0; x < world.getSizeX(); x++) {
            for (int y = 0; y < world.getSizeY(); y++) {
                JLabel worldCase = new JLabel();
                WorldElement worldElement = world.getTile(x, y); // prend le character du tableau qui est à sa place
                worldCase.setBackground(WorldView.getCorrespondingColor(worldElement)); // la case est remplie de la couleur correspondante
                worldCase.setOpaque(true);
                this.add(worldCase);
            }
        }

        Battle.getInstance().addObserver(this, Battle.PROPERTY_GAME_OBJECTS);
        Battle.getInstance().addObserver(this, Battle.PROPERTY_RESULTS);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (propertyChangeEvent.getPropertyName().equals(Battle.PROPERTY_GAME_OBJECTS)) {
            modifiedGameObjectTreatment(propertyChangeEvent);
        }
        if (propertyChangeEvent.getPropertyName().equals(Battle.PROPERTY_RESULTS)) {
            endGameTreatment(propertyChangeEvent);
        }
        this.repaint();
        Toolkit.getDefaultToolkit().sync();
    }


    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        for (GraphicalEntity entityGraphic : this.graphicalEntities) {
            entityGraphic.paint(graphics);
        }
    }
}