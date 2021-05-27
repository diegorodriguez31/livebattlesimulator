package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.model.gameObject.Vector2;

import java.awt.*;

import static main.java.fr.enseeiht.lbs.LiveBattleSimulator.SUPER_PIXEL_SIZE;

@SuppressWarnings("serial")
public class GraphicalEntity extends Component {

    protected Vector2 position;

    public GraphicalEntity(Vector2 position) {
        this.position = new Vector2(position);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        ((Graphics2D) graphics).drawRect(((int) position.x) - 5, ((int) position.y) - 5, SUPER_PIXEL_SIZE, SUPER_PIXEL_SIZE);
    }
}
