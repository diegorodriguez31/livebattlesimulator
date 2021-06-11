package main.java.fr.enseeiht.lbs.view.adapter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

/**
 * Gère les sprites en ressources pour afficher les unités.
 */
public class SpriteBuffer {
    private final static HashMap<String, Image> buffer = new HashMap<>();

    public static Image getSprite(String path) throws IOException {
        Image sprite = buffer.get(path);
        if (sprite == null) {
            sprite = ImageIO.read(Objects.requireNonNull(SpriteBuffer.class.getClassLoader().getResource("sprites/" + path), "Resource should be non null"));
            buffer.put(path, sprite);
        }
        return sprite;
    }
}
