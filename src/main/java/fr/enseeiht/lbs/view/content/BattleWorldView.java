package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.model.world.WorldElement;
import main.java.fr.enseeiht.lbs.view.adapter.GraphicalEntity;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Vue qui affiche les unités lors de la bataille.
 */
public class BattleWorldView extends BattleView implements PropertyChangeListener {

    public BattleWorldView() {
        super();
        this.setLayout(new GridLayout(World.NB_TILES_X, World.NB_TILES_Y));//construit une grille de la même taille que le tableau de char
        this.setPreferredSize(new Dimension(700, 700));
        this.startObserving();
        this.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        super.propertyChange(propertyChangeEvent);
    }

    public void startObserving() {
        super.startObserving();
        World.addObserver(this, World.PROPERTY_RELOAD_MAP);
    }

    public void stopObserving() {
        super.stopObserving();
        World.removeObserver(this, World.PROPERTY_RELOAD_MAP);
    }

    @Override
    public void paint(Graphics graphics) {
        int tileSizeX = this.getSize().width / World.NB_TILES_X;
        int tileSizeY = this.getSize().height / World.NB_TILES_Y;
        for (int y = 0; y < World.NB_TILES_Y; y++) {
            for (int x = 0; x < World.NB_TILES_X; x++) {
                WorldElement worldElement = World.getInstance().getTile(x, y); // prend le character du tableau qui est à sa place
                graphics.setColor(WorldView.getCorrespondingColor(worldElement));
                graphics.fillRect(x * tileSizeX, y * tileSizeY, (x + 1) * tileSizeX, (y + 1) * tileSizeY);
            }
        }
        lock.readLock().lock();
        for (GraphicalEntity entityGraphic : this.graphicalEntities) {
            entityGraphic.paint(graphics);
        }
        lock.readLock().unlock();
    }
}
