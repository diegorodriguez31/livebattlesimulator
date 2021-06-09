package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game_object.Entity;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.armored_unit.*;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.standard_unit.*;
import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.utils.Vector2;
import main.java.fr.enseeiht.lbs.view.adapter.GraphicalEntity;
import main.java.fr.enseeiht.lbs.view.adapter.SpriteGraphicalEntity;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.*;

public abstract class BattleView extends JPanel implements PropertyChangeListener {

    protected final List<GraphicalEntity> graphicalEntities;

    private final static HashMap<Class<? extends Entity>, String> ENTITY_SPRITE = new HashMap<>();
    public final static List<Color> TEAM_COLORS = new ArrayList<>();
    public final static Map<Color, String> COLORS_NAME = new HashMap<>(); //     :-/

    static {
        ENTITY_SPRITE.put(Knight.class, "Knight.png");
        ENTITY_SPRITE.put(Peasant.class, "Peasant.png");
        ENTITY_SPRITE.put(Archer.class, "Archer.png");
        ENTITY_SPRITE.put(Mage.class, "Mage.png");
        ENTITY_SPRITE.put(Giant.class, "Giant.png");
        ENTITY_SPRITE.put(Alien.class, "Alien.png");
        ENTITY_SPRITE.put(Ninja.class, "Ninja.png");
        ENTITY_SPRITE.put(Horseman.class, "Horseman.png");
        ENTITY_SPRITE.put(Golem.class, "Golem.png");
        ENTITY_SPRITE.put(Troll.class, "Troll.png");
        ENTITY_SPRITE.put(Samurai.class, "Samurai.png");
        ENTITY_SPRITE.put(Viking.class, "Viking.png");

        TEAM_COLORS.add(Color.BLUE);
        COLORS_NAME.put(TEAM_COLORS.get(0), "Bleue");
        TEAM_COLORS.add(Color.RED);
        COLORS_NAME.put(TEAM_COLORS.get(1), "Rouge");
        TEAM_COLORS.add(Color.GREEN);
        COLORS_NAME.put(TEAM_COLORS.get(2), "Verte");
        TEAM_COLORS.add(Color.YELLOW);
        COLORS_NAME.put(TEAM_COLORS.get(3), "Jaune");
        TEAM_COLORS.add(Color.PINK);
        COLORS_NAME.put(TEAM_COLORS.get(4), "Rose");
        TEAM_COLORS.add(Color.BLACK);
        COLORS_NAME.put(TEAM_COLORS.get(5), "Dark");
    }

    protected BattleView() {
        this.setVisible(true);
        //this.setPreferredSize(new Dimension(700, 700));
        //this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.graphicalEntities = new LinkedList<>();
        //this.startObserving();
        //setBackground(Color.RED);
    }

  /*  protected Vector2 worldToPixel(Vector2 world) {
        return world.scale(GraphicalEntity.SUPER_PIXEL_SIZE);
    }*/

    protected Vector2 pixelToWorld(Vector2 pixel) {
        return pixel.scale(1f / GraphicalEntity.SUPER_PIXEL_SIZE);
    }

    public Vector2 pixelCoordinatesToWorld(float x, float y) {
        Dimension viewDimension = this.getSize();
        float positionX = (x * World.MAX_POSITION_X) / viewDimension.width;
        float positionY = (y * World.MAX_POSITION_Y) / viewDimension.height;
        return new Vector2(positionX, positionY);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (propertyChangeEvent.getPropertyName().equals(Battle.PROPERTY_GAME_OBJECTS)) {
            modifiedGameObjectTreatment(propertyChangeEvent);
        }
        this.repaint();
        Toolkit.getDefaultToolkit().sync();
    }

    protected void modifiedGameObjectTreatment(PropertyChangeEvent propertyChangeEvent) {
        synchronized (graphicalEntities) {
            graphicalEntities.clear();
        }
        for (Object gameObject : (List<?>) propertyChangeEvent.getNewValue()) {
            if (gameObject instanceof Entity) {
                Entity entity = (Entity) gameObject;
                String sprite = getCorrespondingSprite(entity);
                Color color = null;
                if (entity instanceof Unit) {
                    Unit unit = (Unit) entity;
                    if (unit.getTeam() != null && unit.getTeam().getArmyIndex() < TEAM_COLORS.size()) {
                        color = TEAM_COLORS.get(unit.getTeam().getArmyIndex() % TEAM_COLORS.size());
                    }
                }
                if (sprite != null) {
                    this.graphicalEntities.add(new SpriteGraphicalEntity(entity.getPosition(), sprite, color));
                } else {
                    this.graphicalEntities.add(new GraphicalEntity(entity.getPosition(), color));
                }

            }
        }
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        synchronized (this.graphicalEntities) {
            for (GraphicalEntity entityGraphic : this.graphicalEntities) {
                entityGraphic.paint(graphics);
            }
        }
    }

    public static String getCorrespondingSprite(Entity entity) {
        return ENTITY_SPRITE.get(entity.getClass());
    }

    protected void startObserving() {
        Battle.addObserver(this, Battle.PROPERTY_GAME_OBJECTS);
        this.modifiedGameObjectTreatment(new PropertyChangeEvent(this, Battle.PROPERTY_GAME_OBJECTS, null, Battle.getInstance().getObjects()));
    }

    protected void stopObserving() {
        Battle.removeObserver(this, Battle.PROPERTY_GAME_OBJECTS);
    }

}


