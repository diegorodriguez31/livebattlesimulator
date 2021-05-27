package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.model.gameObject.Vector2;

import java.awt.*;
import java.io.IOException;

public class SpriteGraphicalEntity extends GraphicalEntity {

    private String spritePath;

    public SpriteGraphicalEntity(Vector2 position, String spritePath) {
        super(position);
        this.spritePath = spritePath;
    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        try {
            Image image = SpriteBuffer.getSprite(this.spritePath);
            graphics.drawImage(image, (int) position.x - 5, (int) position.y - 5, null);
        } catch (IOException e) {
            e.printStackTrace();
            super.paint(graphics);
        }
    }
}
