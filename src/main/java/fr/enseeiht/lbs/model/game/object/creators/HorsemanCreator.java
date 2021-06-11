package main.java.fr.enseeiht.lbs.model.game.object.creators;

import main.java.fr.enseeiht.lbs.model.game.object.Entity;
import main.java.fr.enseeiht.lbs.model.game.object.EntityCreator;
import main.java.fr.enseeiht.lbs.model.game.object.Stats;
import main.java.fr.enseeiht.lbs.model.game.object.unit.soldier.armored.unit.Horseman;
import main.java.fr.enseeiht.lbs.utils.Vector2;

/**
 * Factory du horseman
 */
public class HorsemanCreator implements EntityCreator {

    @Override
    public Entity createEntity(String name, Vector2 position, Stats stats) {
        return new Horseman(name, stats, position);
    }
}
