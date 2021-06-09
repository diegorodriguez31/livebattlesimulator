package main.java.fr.enseeiht.lbs.view.adapter;

import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.utils.Vector2;

import java.awt.*;
import java.io.IOException;

public class SpriteGraphicalEntity extends GraphicalEntity {

    private final String spritePath;

    public SpriteGraphicalEntity(Vector2 position, String spritePath, Color color) {
        super(position, color);
        this.spritePath = spritePath;
    }

    @Override
    public void paint(Graphics graphics) {
        try {
            Image image = SpriteBuffer.getSprite(this.spritePath);
            Dimension viewDimension = this.getViewSize();
            int positionX = Math.round((position.getX() * viewDimension.width ) / World.MAX_POSITION_X);
            int positionY = Math.round((position.getY() * viewDimension.height ) / World.MAX_POSITION_Y);
            graphics.drawImage(image, positionX - (SUPER_PIXEL_SIZE/2), positionY - (SUPER_PIXEL_SIZE/2), null);
            paintLabel(graphics, positionX, positionY);
        } catch (IOException e) {
            e.printStackTrace();
            super.paint(graphics);
        }
    }
}
