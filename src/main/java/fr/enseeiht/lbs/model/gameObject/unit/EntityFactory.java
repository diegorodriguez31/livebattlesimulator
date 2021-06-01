package main.java.fr.enseeiht.lbs.model.gameObject.unit;

import main.java.fr.enseeiht.lbs.model.gameObject.Entity;
import main.java.fr.enseeiht.lbs.model.gameObject.EntityCreator;
import main.java.fr.enseeiht.lbs.model.gameObject.Statistic;
import main.java.fr.enseeiht.lbs.model.gameObject.Stats;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.soldier.creators.KnightCreator;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.soldier.creators.PeasantCreator;
import main.java.fr.enseeiht.lbs.utils.Pair;
import main.java.fr.enseeiht.lbs.utils.Vector2;

import java.util.HashMap;
import java.util.Set;

public class EntityFactory {
    private static final Stats PEASANT_STATS = new Stats();
    private static final Stats OLD_PEASANT_STATS = new Stats();
    private static final Stats KNIGHT_STATS = new Stats();
    private static final Stats BRUTE_STATS = new Stats();

    static final HashMap<String, Pair<EntityCreator, Stats>> entityTypes = new HashMap<>();

    static {
        //Creators
        EntityCreator PEASANT_CREATOR = new PeasantCreator();
        EntityCreator KNIGHT_CREATOR = new KnightCreator();

        //Peasant
        PEASANT_STATS.addStat(Statistic.HEALTH, 50);
        PEASANT_STATS.addStat(Statistic.DAMAGE, 10);
        PEASANT_STATS.addStat(Statistic.COOLDOWN, 1);
        PEASANT_STATS.addStat(Statistic.SPEED, 1);
        PEASANT_STATS.addStat(Statistic.RANGE, 1);
        PEASANT_STATS.addStat(Statistic.ACCURACY, 50);
        PEASANT_STATS.addStat(Statistic.AGILITY, 10);

        //Grandpa
        OLD_PEASANT_STATS.addStat(Statistic.HEALTH, 10);
        OLD_PEASANT_STATS.addStat(Statistic.DAMAGE, 10);
        OLD_PEASANT_STATS.addStat(Statistic.COOLDOWN, 0.5);
        OLD_PEASANT_STATS.addStat(Statistic.SPEED, 0.5);
        OLD_PEASANT_STATS.addStat(Statistic.RANGE, 1);
        OLD_PEASANT_STATS.addStat(Statistic.ACCURACY, 70);
        OLD_PEASANT_STATS.addStat(Statistic.AGILITY, 5);

        //Knight
        KNIGHT_STATS.addStat(Statistic.HEALTH, 100);
        KNIGHT_STATS.addStat(Statistic.DAMAGE, 30);
        KNIGHT_STATS.addStat(Statistic.COOLDOWN, 1);
        KNIGHT_STATS.addStat(Statistic.SPEED, 1);
        KNIGHT_STATS.addStat(Statistic.RANGE, 1);
        KNIGHT_STATS.addStat(Statistic.ACCURACY, 80);
        KNIGHT_STATS.addStat(Statistic.AGILITY, 50);
        KNIGHT_STATS.addStat(Statistic.ARMOR, 50);

        //Brute
        BRUTE_STATS.addStat(Statistic.HEALTH, 500);
        BRUTE_STATS.addStat(Statistic.DAMAGE, 400);
        BRUTE_STATS.addStat(Statistic.COOLDOWN, 4);
        BRUTE_STATS.addStat(Statistic.SPEED, 0.5);
        BRUTE_STATS.addStat(Statistic.RANGE, 2);
        BRUTE_STATS.addStat(Statistic.ACCURACY, 40);
        BRUTE_STATS.addStat(Statistic.AGILITY, 0);
        BRUTE_STATS.addStat(Statistic.ARMOR, 50);

        entityTypes.put("Farmer", new Pair<>(PEASANT_CREATOR, PEASANT_STATS));
        entityTypes.put("Grandpa", new Pair<>(PEASANT_CREATOR, OLD_PEASANT_STATS));
        entityTypes.put("Knight", new Pair<>(KNIGHT_CREATOR, KNIGHT_STATS));
        entityTypes.put("Brute", new Pair<>(KNIGHT_CREATOR, BRUTE_STATS));
    }

    public static Set<String> getEntityTypes() {
        return entityTypes.keySet();
    }

    public static Entity createEntity(String type, Vector2 position) {
        Pair<EntityCreator, Stats> pair = entityTypes.get(type);
        return pair.first.createEntity(position, pair.second);
    }
}
