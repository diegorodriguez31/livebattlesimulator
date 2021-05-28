package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.model.world.WorldElement;

import java.awt.*;
import main.java.fr.enseeiht.lbs.model.gameObject.Entity;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public class BattleWorldView extends BattleView implements PropertyChangeListener {

    private static final int WORLD_TO_PIXEL = 11;

    private List<GraphicalEntity> graphicalEntities;

    public BattleWorldView(World world) {
        this.graphicalEntities = new LinkedList<>();
        this.setLayout(new GridLayout(world.getSizeX(), world.getSizeY()));//construit une grille de la même taille que le tableau de char
        this.setVisible(true);


        for (int x = 0; x < world.getSizeX(); x++) {
            for (int y = 0; y < world.getSizeY(); y++) {
                JLabel worldCase = new JLabel();
                WorldElement worldElement = world.getTile(x, y); //c prend le character du tableau qui est à sa place
                worldCase.setBackground(MapView.getCorrespondingColor(worldElement)); // la case est remplie de la couleur correspondante
                worldCase.setOpaque(true);
                this.add(worldCase);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (propertyChangeEvent.getPropertyName().equals("gameObjects"))
            modifiedGameObjectTreatement(propertyChangeEvent);

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
