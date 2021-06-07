package main.java.fr.enseeiht.lbs.model.game_object;

import main.java.fr.enseeiht.lbs.model.game_object.creators.KnightCreator;
import main.java.fr.enseeiht.lbs.model.game_object.creators.PeasantCreator;

public enum EntityPrimitiveTypes {
    KNIGHT("knight", new KnightCreator()), PEASANT("farmer", new PeasantCreator());
    String string;
    EntityCreator creator;

    EntityPrimitiveTypes(String string, EntityCreator creator) {
        this.string = string;
        this.creator = creator;
    }

    @Override
    public String toString() {
        return string;
    }

    public EntityCreator getCreator() {
        return creator;
    }
}
