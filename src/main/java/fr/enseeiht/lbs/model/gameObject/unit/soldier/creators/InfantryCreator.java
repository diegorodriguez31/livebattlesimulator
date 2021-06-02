package main.java.fr.enseeiht.lbs.model.gameObject.unit.soldier.creators;

import main.java.fr.enseeiht.lbs.model.gameObject.Entity;
import main.java.fr.enseeiht.lbs.model.gameObject.EntityCreator;
import main.java.fr.enseeiht.lbs.model.gameObject.Stats;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.soldier.Infantryman;
import main.java.fr.enseeiht.lbs.utils.Vector2;

public class InfantryCreator implements EntityCreator {
    @Override
    public Entity createEntity(String name, Vector2 position, Stats stats) {
        return new Infantryman(name, stats, position);
    }
}
