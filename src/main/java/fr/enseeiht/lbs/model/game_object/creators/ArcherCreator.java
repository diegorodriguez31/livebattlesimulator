package main.java.fr.enseeiht.lbs.model.game_object.creators;

import main.java.fr.enseeiht.lbs.model.game_object.Entity;
import main.java.fr.enseeiht.lbs.model.game_object.EntityCreator;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.standard_unit.Archer;
import main.java.fr.enseeiht.lbs.utils.Vector2;

public class ArcherCreator implements EntityCreator {

    @Override
    public Entity createEntity(String name, Vector2 position, Stats stats) {
        return new Archer(name, stats, position);
    }
}
