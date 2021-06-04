package main.java.fr.enseeiht.lbs.model.game_object;

import main.java.fr.enseeiht.lbs.utils.Vector2;

public interface EntityCreator {
    Entity createEntity(String name, Vector2 position, Stats stats);
}
