package main.java.fr.enseeiht.lbs.model.gameObject;

import main.java.fr.enseeiht.lbs.utils.Vector2;

public interface EntityCreator {
    Entity createEntity(String name, Vector2 position, Stats stats);
}
