package main.java.fr.enseeiht.lbs.model.game_object.creators;

import main.java.fr.enseeiht.lbs.model.game_object.Entity;
import main.java.fr.enseeiht.lbs.model.game_object.EntityCreator;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.armored_unit.Knight;
import main.java.fr.enseeiht.lbs.utils.Vector2;


/**
 * Factory du knight
 */
public class KnightCreator implements EntityCreator {

    @Override
    public Entity createEntity(String name, Vector2 position, Stats stats) {
        return new Knight(name, stats, position);
    }
}