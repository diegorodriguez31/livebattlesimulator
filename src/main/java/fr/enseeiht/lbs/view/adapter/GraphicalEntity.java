package main.java.fr.enseeiht.lbs.view.adapter;

import main.java.fr.enseeiht.lbs.model.game_object.Vector2;

import java.awt.*;

@SuppressWarnings("serial")
public class GraphicalEntity extends Component {

    public static final int SUPER_PIXEL_SIZE = 11;
    protected Vector2 position;
    private Color labelColor;

    public GraphicalEntity(Vector2 position, Color labelColor) {
        this.position = new Vector2(position);
        this.labelColor = labelColor;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        ((Graphics2D) graphics).drawRect(((int) position.x) - (SUPER_PIXEL_SIZE/2), ((int) position.y) - (SUPER_PIXEL_SIZE/2), SUPER_PIXEL_SIZE, SUPER_PIXEL_SIZE);
        paintLabel(graphics);
    }

    protected void paintLabel(Graphics graphics) {
        if (labelColor == null) return;
        int x = (int) position.x;
        int y = (int) position.y;
        graphics.setColor(labelColor);
        graphics.fillPolygon(
                new int[]{x - 3, x + 3, x},
                new int[]{y - 10, y - 10, y - 7},
                3
        );
    }
}
