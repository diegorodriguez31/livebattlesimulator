package main.java.fr.enseeiht.lbs.model.game_object;

import main.java.fr.enseeiht.lbs.model.game_object.creators.KnightCreator;
import main.java.fr.enseeiht.lbs.model.game_object.creators.PeasantCreator;

/**
 * Enum that list the primitive entity types
 */
public enum EntityPrimitiveTypes {
    KNIGHT(new KnightCreator()), PEASANT(new PeasantCreator());
    private final EntityCreator creator;

    EntityPrimitiveTypes(EntityCreator creator) {
        this.creator = creator;
    }

    /**
     * Gives the creator that allows to create the entity of a given type
     *
     * @return the creator
     */
    public EntityCreator getCreator() {
        return creator;
    }
}
