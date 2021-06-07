package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Army;
import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game_object.Entity;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.Knight;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.Peasant;
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

    private final List<GraphicalEntity> graphicalEntities;

    private final static HashMap<Class<? extends Entity>, String> ENTITY_SPRITE = new HashMap<>();
    public final static List<Color> TEAM_COLORS = new ArrayList<>();
    public final static Map<Color, String> COLORS_NAME = new HashMap<>(); //     :-/

    static {
        ENTITY_SPRITE.put(Knight.class, "Knight.png");
        ENTITY_SPRITE.put(Peasant.class, "Peasant.png");

        TEAM_COLORS.add(Color.BLUE);
        COLORS_NAME.put(TEAM_COLORS.get(0), "bleue");
        TEAM_COLORS.add(Color.RED);
        COLORS_NAME.put(TEAM_COLORS.get(1), "rouge");
        TEAM_COLORS.add(Color.GREEN);
        COLORS_NAME.put(TEAM_COLORS.get(2), "verte");
        TEAM_COLORS.add(Color.YELLOW);
        COLORS_NAME.put(TEAM_COLORS.get(3), "jaune");
        TEAM_COLORS.add(Color.PINK);
        COLORS_NAME.put(TEAM_COLORS.get(4), "rose");
        TEAM_COLORS.add(Color.BLACK);
        COLORS_NAME.put(TEAM_COLORS.get(5), "dark");
    }

    protected BattleView() {
        this.setVisible(true);
        this.setPreferredSize(new Dimension(500, 500));
        this.graphicalEntities = new LinkedList<>();
        //this.startObserving();
    }

    protected Vector2 worldToPixel(Vector2 world) {
        return world.scale(GraphicalEntity.SUPER_PIXEL_SIZE);
    }

    protected Vector2 pixelToWorld(Vector2 pixel) {
        return pixel.scale(1f / GraphicalEntity.SUPER_PIXEL_SIZE);
    }

    public Vector2 pixelToWorld(int x, int y) {
        return new Vector2(x, y).scale(1f / GraphicalEntity.SUPER_PIXEL_SIZE);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (propertyChangeEvent.getPropertyName().equals(Battle.PROPERTY_GAME_OBJECTS)) {
            modifiedGameObjectTreatment(propertyChangeEvent);
        } else if (propertyChangeEvent.getPropertyName().equals(Battle.PROPERTY_RESULTS)) {
            endGameTreatment(propertyChangeEvent);
        }
        this.repaint();
        Toolkit.getDefaultToolkit().sync();
    }

    protected void modifiedGameObjectTreatment(PropertyChangeEvent propertyChangeEvent) {
        graphicalEntities.clear();
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
                    this.graphicalEntities.add(new SpriteGraphicalEntity(worldToPixel(entity.getPosition()), sprite, color));
                } else {
                    this.graphicalEntities.add(new GraphicalEntity(worldToPixel(entity.getPosition()), color));
                }

            }
        }
    }

    protected void endGameTreatment(PropertyChangeEvent propertyChangeEvent) {
        Army winner = (Army) propertyChangeEvent.getNewValue();
        Color color = BattleView.TEAM_COLORS.get(winner.getArmyIndex());
        String colorName = BattleView.COLORS_NAME.get(color);
        String result = Battle.getInstance().getName() +
                "\nL'armée " + colorName + " a gagné";
        JOptionPane.showMessageDialog(null, result);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        for (GraphicalEntity entityGraphic : this.graphicalEntities) {
            entityGraphic.paint(graphics);
        }
    }

    public static String getCorrespondingSprite(Entity entity) {
        return ENTITY_SPRITE.get(entity.getClass());
    }

    protected void startObserving(){
        Battle.addObserver(this, Battle.PROPERTY_GAME_OBJECTS);
        this.modifiedGameObjectTreatment(new PropertyChangeEvent(this, Battle.PROPERTY_GAME_OBJECTS, null, Battle.getInstance().getObjects()));

        Battle.addObserver(this, Battle.PROPERTY_RESULTS);
    }

    protected void stopObserving(){
        Battle.removeObserver(this, Battle.PROPERTY_GAME_OBJECTS);
        Battle.removeObserver(this, Battle.PROPERTY_RESULTS);
    }

}
