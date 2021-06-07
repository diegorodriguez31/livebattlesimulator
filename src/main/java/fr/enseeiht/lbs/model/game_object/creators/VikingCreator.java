package main.java.fr.enseeiht.lbs.model.game_object.creators;

import main.java.fr.enseeiht.lbs.model.game_object.Entity;
import main.java.fr.enseeiht.lbs.model.game_object.EntityCreator;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.armored_unit.Viking;
import main.java.fr.enseeiht.lbs.utils.Vector2;

public class VikingCreator implements EntityCreator {

    @Override
    public Entity createEntity(String name, Vector2 position, Stats stats) {
        return new Viking(name, stats, position);
    }
}
