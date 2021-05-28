package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.model.gameObject.Entity;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public class BattleView extends JPanel implements PropertyChangeListener {

    private static final int WORLD_TO_PIXEL = 11;

    private List<GraphicalEntity> graphicalEntities;

    public BattleView() {
        this.setVisible(true);
        this.graphicalEntities = new LinkedList<>();
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (propertyChangeEvent.getPropertyName().equals("gameObjects"))
            modifiedGameObjectTreatement(propertyChangeEvent);

        this.repaint();
        Toolkit.getDefaultToolkit().sync();
    }

    private void modifiedGameObjectTreatement(PropertyChangeEvent propertyChangeEvent) {
        graphicalEntities.clear();
        for (Object gameObject : (List<?>) propertyChangeEvent.getNewValue()) {
            if (gameObject instanceof Entity) {
                Entity entity = (Entity) gameObject;
                this.graphicalEntities.add(new GraphicalEntity(entity.getPosition().scale(WORLD_TO_PIXEL)));
            }
        }
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        for (GraphicalEntity entityGraphic : this.graphicalEntities) {
            entityGraphic.paint(graphics);
        }
    }
}
