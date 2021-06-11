package main.java.fr.enseeiht.lbs.view.adapter;

import main.java.fr.enseeiht.lbs.controller.UnitPlacementController;
import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.utils.Vector2;
import main.java.fr.enseeiht.lbs.view.content.BattleSimulationView;
import main.java.fr.enseeiht.lbs.view.gui.LiveBattleSimulatorGUI;

import java.awt.*;

/**
 * Elément graphique associé à une entité.
 * Par exemple l'élément affiché pour représenter une unité.
 */
public class GraphicalEntity extends Component {

    public static final int SUPER_PIXEL_SIZE = 11;
    protected Vector2 position;
    private final Color labelColor;

    public GraphicalEntity(Vector2 position, Color labelColor) {
        this.position = new Vector2(position);
        this.labelColor = labelColor;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        Dimension viewDimension = this.getViewSize();
        int positionX = Math.round((position.getX() * viewDimension.width ) / World.MAX_POSITION_X);
        int positionY = Math.round((position.getY() * viewDimension.height ) / World.MAX_POSITION_Y);
        graphics.drawRect(positionX - (SUPER_PIXEL_SIZE/2), positionY - (SUPER_PIXEL_SIZE/2), SUPER_PIXEL_SIZE, SUPER_PIXEL_SIZE);
        paintLabel(graphics, positionX, positionY);
    }

    protected Dimension getViewSize() {
        Dimension dimension = null;
        if (LiveBattleSimulatorGUI.getInstance().isBattleSimulationViewActive()) {
            dimension = BattleSimulationView.getInstance().getBattleWorldView().getSize();
        }else if(LiveBattleSimulatorGUI.getInstance().isUnitPlacementControllerActive()){
            dimension = UnitPlacementController.getInstance().getBattleWorldView().getSize();
        }
        return dimension;
    }

    protected void paintLabel(Graphics graphics, int positionX, int positionY) {
        if (labelColor == null) return;
        graphics.setColor(labelColor);
        graphics.fillPolygon(
                new int[]{positionX - 3, positionX + 3, positionX},
                new int[]{positionY - 10, positionY - 10, positionY - 7},
                3
        );
    }
}
