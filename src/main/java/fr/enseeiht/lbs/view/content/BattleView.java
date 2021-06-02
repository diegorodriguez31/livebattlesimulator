package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.model.battleSimulator.Army;
import main.java.fr.enseeiht.lbs.model.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.model.gameObject.Entity;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.Unit;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.soldier.Knight;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.soldier.Peasant;
import main.java.fr.enseeiht.lbs.utils.Vector2;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.*;

@SuppressWarnings("serial")
public class BattleView extends JPanel implements PropertyChangeListener {

    private static final int WORLD_TO_PIXEL = 11;

    private List<GraphicalEntity> graphicalEntities;

    private final static HashMap<Class<? extends Entity>, String> entitySprites = new HashMap<>();
    public final static List<Color> teamColors = new ArrayList<>();
    public final static Map<Color, String> colorsNames = new HashMap<>();

    static {
        entitySprites.put(Knight.class, "Knight.png");
        entitySprites.put(Peasant.class, "Peasant.png");

        teamColors.add(Color.BLUE);
        colorsNames.put(teamColors.get(0), "bleue");
        teamColors.add(Color.RED);
        colorsNames.put(teamColors.get(1), "rouge");
        teamColors.add(Color.GREEN);
        colorsNames.put(teamColors.get(2), "verte");
        teamColors.add(Color.YELLOW);
        colorsNames.put(teamColors.get(3), "jaune");
    }

    public BattleView() {
        this.setVisible(true);
        this.setPreferredSize(new Dimension(500, 500));
        this.graphicalEntities = new LinkedList<>();
    }

    protected Vector2 worldToPixel(Vector2 world) {
        return world.scale(WORLD_TO_PIXEL);
    }

    protected Vector2 pixelToWorld(Vector2 pixel) {
        return pixel.scale(1f / WORLD_TO_PIXEL);
    }

    public Vector2 pixelToWorld(int x, int y) {
        return new Vector2(x, y).scale(1f / WORLD_TO_PIXEL);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (propertyChangeEvent.getPropertyName().equals(Battle.PROPERTY_GAME_OBJECTS))
            modifiedGameObjectTreatment(propertyChangeEvent);
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
                    if (unit.getTeam() != null && unit.getTeam().getArmyIndex() < teamColors.size()) {
                        color = teamColors.get(unit.getTeam().getArmyIndex() % teamColors.size());
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
        Color color = BattleView.teamColors.get(winner.getArmyIndex());
        String colorName = BattleView.colorsNames.get(color);
        String result = "L'armée " + colorName + " a gagnée";
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
        return entitySprites.get(entity.getClass());
    }
}
