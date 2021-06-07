package main.java.fr.enseeiht.lbs.model.game_object;

import main.java.fr.enseeiht.lbs.model.game_object.creators.KnightCreator;
import main.java.fr.enseeiht.lbs.model.game_object.creators.PeasantCreator;

public enum EntityPrimitiveTypes {
    KNIGHT(new KnightCreator()), PEASANT(new PeasantCreator());
    private final EntityCreator creator;

    EntityPrimitiveTypes(EntityCreator creator) {
        this.creator = creator;
    }

    public EntityCreator getCreator() {
        return creator;
    }
}
