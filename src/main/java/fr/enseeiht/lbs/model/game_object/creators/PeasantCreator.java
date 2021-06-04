package main.java.fr.enseeiht.lbs.model.game_object.creators;

import main.java.fr.enseeiht.lbs.model.game_object.Entity;
import main.java.fr.enseeiht.lbs.model.game_object.EntityCreator;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.Peasant;
import main.java.fr.enseeiht.lbs.utils.Vector2;

public class PeasantCreator implements EntityCreator {
    @Override
    public Entity createEntity(String name, Vector2 position, Stats stats) {
        return new Peasant(name, stats, position);
    }
}
