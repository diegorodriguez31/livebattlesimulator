package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.model.gameObject.Entity;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.soldier.Knight;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.soldier.Peasant;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public class BattleView extends JPanel implements PropertyChangeListener {

    private List<GraphicalEntity> graphicalEntities;

    private final static HashMap<Class<? extends Entity>, String> entitySprites = new HashMap<>();

    static {
        entitySprites.put(Knight.class, "Knight.png");
        entitySprites.put(Peasant.class, "Peasant.png");
    }

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

    protected void modifiedGameObjectTreatement(PropertyChangeEvent propertyChangeEvent) {
        graphicalEntities.clear();
        for (Object gameObject : (List<?>) propertyChangeEvent.getNewValue()) {
            if (gameObject instanceof Entity) {
                Entity entity = (Entity) gameObject;
                String sprite = getCorrespondingSprite(entity);
                if (sprite != null) {
                    this.graphicalEntities.add(new SpriteGraphicalEntity(entity.getPosition(), sprite));
                } else {
                    this.graphicalEntities.add(new GraphicalEntity(entity.getPosition()));
                }
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

    public static String getCorrespondingSprite(Entity entity) {
        return entitySprites.get(entity.getClass());
    }
}
