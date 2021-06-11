package main.java.fr.enseeiht.lbs.model.game.object.creators;

import main.java.fr.enseeiht.lbs.model.game.object.Entity;
import main.java.fr.enseeiht.lbs.model.game.object.EntityCreator;
import main.java.fr.enseeiht.lbs.model.game.object.Stats;
import main.java.fr.enseeiht.lbs.model.game.object.unit.soldier.standard.unit.Giant;
import main.java.fr.enseeiht.lbs.utils.Vector2;

/**
 * Factory du géant
 */
public class GiantCreator implements EntityCreator {

    @Override
    public Entity createEntity(String name, Vector2 position, Stats stats) {
        return new Giant(name, stats, position);
    }
}
