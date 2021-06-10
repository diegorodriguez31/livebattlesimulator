package main.java.fr.enseeiht.lbs.model.game.object;

import main.java.fr.enseeiht.lbs.model.game.object.creators.*;

/**
 * Enum that list the primitive entity types
 */
public enum EntityPrimitiveTypes {
    KNIGHT(new KnightCreator()),
    PEASANT(new PeasantCreator()),
    ARCHER(new ArcherCreator()),
    MAGE(new MageCreator()),
    GIANT(new GiantCreator()),
    ALIEN(new AlienCreator()),
    NINJA(new NinjaCreator()),
    HORSEMAN(new HorsemanCreator()),
    GOLEM(new GolemCreator()),
    TROLL(new TrollCreator()),
    SAMURAI(new SamuraiCreator()),
    VIKING(new VikingCreator());

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
