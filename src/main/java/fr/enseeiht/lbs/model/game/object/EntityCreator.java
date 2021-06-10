package main.java.fr.enseeiht.lbs.model.game.object;

import main.java.fr.enseeiht.lbs.utils.Vector2;

/**
 * Creates an entity with a certain behaviour
 */
public interface EntityCreator {
    /**
     * Creates the entity of a certain primitive type given its position name and stats
     *
     * @param name     of the entity type
     * @param position of the entity
     * @param stats    of the entity type
     * @return the entity
     */
    Entity createEntity(String name, Vector2 position, Stats stats);
}
