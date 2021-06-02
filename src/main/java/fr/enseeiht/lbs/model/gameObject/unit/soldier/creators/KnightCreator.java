package main.java.fr.enseeiht.lbs.model.gameObject.unit.soldier.creators;

import main.java.fr.enseeiht.lbs.model.gameObject.Entity;
import main.java.fr.enseeiht.lbs.model.gameObject.EntityCreator;
import main.java.fr.enseeiht.lbs.model.gameObject.Stats;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.soldier.Knight;
import main.java.fr.enseeiht.lbs.utils.Vector2;

public class KnightCreator implements EntityCreator {
    @Override
    public Entity createEntity(String name, Vector2 position, Stats stats) {
        return new Knight(name, stats, position);
    }
}