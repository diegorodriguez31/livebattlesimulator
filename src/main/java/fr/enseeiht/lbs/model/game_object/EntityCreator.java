package main.java.fr.enseeiht.lbs.model.game_object;

import main.java.fr.enseeiht.lbs.utils.Vector2;

/**
 * Creates an entity with a certain behaviour
 */
public interface EntityCreator {
    /**
     * Creates the entit
     *
     * @param name
     * @param position
     * @param stats
     * @return
     */
    Entity createEntity(String name, Vector2 position, Stats stats);
}
