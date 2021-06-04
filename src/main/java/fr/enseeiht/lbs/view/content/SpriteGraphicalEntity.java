package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.model.game_object.Vector2;
import main.java.fr.enseeiht.lbs.view.adapter.GraphicalEntity;

import java.awt.*;
import java.io.IOException;

public class SpriteGraphicalEntity extends GraphicalEntity {

    private String spritePath;

    public SpriteGraphicalEntity(Vector2 position, String spritePath, Color color) {
        super(position, color);
        this.spritePath = spritePath;
    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        try {
            Image image = SpriteBuffer.getSprite(this.spritePath);
            graphics.drawImage(image, (int) position.x - 5, (int) position.y - 5, null);
            paintLabel(graphics);
        } catch (IOException e) {
            e.printStackTrace();
            super.paint(graphics);
        }
    }
}