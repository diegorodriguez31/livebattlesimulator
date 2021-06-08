package main.java.fr.enseeiht.lbs.model.game_object.creators;

import main.java.fr.enseeiht.lbs.model.game_object.Entity;
import main.java.fr.enseeiht.lbs.model.game_object.EntityCreator;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.armored_unit.Viking;
import main.java.fr.enseeiht.lbs.utils.Vector2;

public class VikingCreator implements EntityCreator {
    /**
     * Creates the entity of a certain primitive type given its position name and stats
     *
     * @param name     of the entity type
     * @param position of the entity
     * @param stats    of the entity type
     * @return the entity
     */
    @Override
    public Entity createEntity(String name, Vector2 position, Stats stats) {
        return new Viking(name, stats, position);
    }
}
