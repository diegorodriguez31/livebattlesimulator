package main.java.fr.enseeiht.lbs.view.adapter;

import main.java.fr.enseeiht.lbs.utils.Vector2;

import java.awt.*;

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
        graphics.drawRect(((int) position.getX()) - (SUPER_PIXEL_SIZE/2), ((int) position.getY()) - (SUPER_PIXEL_SIZE/2), SUPER_PIXEL_SIZE, SUPER_PIXEL_SIZE);
        paintLabel(graphics);
    }

    protected void paintLabel(Graphics graphics) {
        if (labelColor == null) return;
        int x = (int) position.getX();
        int y = (int) position.getY();
        graphics.setColor(labelColor);
        graphics.fillPolygon(
                new int[]{x - 3, x + 3, x},
                new int[]{y - 10, y - 10, y - 7},
                3
        );
    }
}
