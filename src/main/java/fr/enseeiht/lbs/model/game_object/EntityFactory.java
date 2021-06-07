package main.java.fr.enseeiht.lbs.model.game_object;

import main.java.fr.enseeiht.lbs.model.game_object.creators.*;
import main.java.fr.enseeiht.lbs.utils.Pair;
import main.java.fr.enseeiht.lbs.utils.Vector2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class EntityFactory {

    static class UnmodifiableTypeException extends Exception {
        public UnmodifiableTypeException(String types) {
            super("Cant modify entity type : " + types);
        }
    }

    private static final EntitySerializer SERIALIZER = null;
    private static final Set<String> INITIAL_UNIT = new HashSet<String>(
            Arrays.asList("Peasant", "Knight", "Archer", "Mage", "Giant", "Alien", "Ninja", "Horseman", "Golem", "Troll", "Samurai", "Viking")
    );

    private static final Stats PEASANT_STATS = new Stats();
    private static final Stats ARCHER_STATS = new Stats();
    private static final Stats KNIGHT_STATS = new Stats();
    private static final Stats MAGE_STATS = new Stats();
    private static final Stats GIANT_STATS = new Stats();
    private static final Stats ALIEN_STATS = new Stats();
    private static final Stats NINJA_STATS = new Stats();
    private static final Stats HORSEMAN_STATS = new Stats();
    private static final Stats GOLEM_STATS = new Stats();
    private static final Stats TROLL_STATS = new Stats();
    private static final Stats SAMURAI_STATS = new Stats();
    private static final Stats VIKING_STATS = new Stats();

    static final HashMap<String, Pair<EntityCreator, Stats>> entityTypes = new HashMap<>();

    static {
        //Creators
        EntityCreator PEASANT_CREATOR = new PeasantCreator();
        EntityCreator KNIGHT_CREATOR = new KnightCreator();
        EntityCreator ARCHER_CREATOR = new ArcherCreator();
        EntityCreator MAGE_CREATOR = new MageCreator();
        EntityCreator GIANT_CREATOR = new GiantCreator();
        EntityCreator ALIEN_CREATOR = new AlienCreator();
        EntityCreator NINJA_CREATOR = new NinjaCreator();
        EntityCreator HORSEMAN_CREATOR = new HorsemanCreator();
        EntityCreator GOLEM_CREATOR = new GolemCreator();
        EntityCreator TROLL_CREATOR = new TrollCreator();
        EntityCreator SAMURAI_CREATOR = new SamuraiCreator();
        EntityCreator VIKING_CREATOR = new VikingCreator();

        //Peasant
        PEASANT_STATS.addStat(Statistic.HEALTH, 50);
        PEASANT_STATS.addStat(Statistic.DAMAGE, 10);
        PEASANT_STATS.addStat(Statistic.COOLDOWN, 1);
        PEASANT_STATS.addStat(Statistic.SPEED, 1);
        PEASANT_STATS.addStat(Statistic.RANGE, 1);
        PEASANT_STATS.addStat(Statistic.ACCURACY, 50);
        PEASANT_STATS.addStat(Statistic.AGILITY, 10);

        //Knight
        KNIGHT_STATS.addStat(Statistic.HEALTH, 100);
        KNIGHT_STATS.addStat(Statistic.DAMAGE, 30);
        KNIGHT_STATS.addStat(Statistic.COOLDOWN, 1);
        KNIGHT_STATS.addStat(Statistic.SPEED, 1);
        KNIGHT_STATS.addStat(Statistic.RANGE, 1);
        KNIGHT_STATS.addStat(Statistic.ACCURACY, 80);
        KNIGHT_STATS.addStat(Statistic.AGILITY, 50);
        KNIGHT_STATS.addStat(Statistic.ARMOR, 50);

        // Archer
        ARCHER_STATS.addStat(Statistic.HEALTH, 50);
        ARCHER_STATS.addStat(Statistic.DAMAGE, 40);
        ARCHER_STATS.addStat(Statistic.COOLDOWN, 3);
        ARCHER_STATS.addStat(Statistic.SPEED, 2);
        ARCHER_STATS.addStat(Statistic.RANGE, 50);
        ARCHER_STATS.addStat(Statistic.ACCURACY, 70);
        ARCHER_STATS.addStat(Statistic.AGILITY, 50);

        // Mage
        MAGE_STATS.addStat(Statistic.HEALTH, 50);
        MAGE_STATS.addStat(Statistic.DAMAGE, 50);
        MAGE_STATS.addStat(Statistic.COOLDOWN, 5);
        MAGE_STATS.addStat(Statistic.SPEED, 1);
        MAGE_STATS.addStat(Statistic.RANGE, 5);
        MAGE_STATS.addStat(Statistic.ACCURACY, 90);
        MAGE_STATS.addStat(Statistic.AGILITY, 20);
        MAGE_STATS.addStat(Statistic.AREA_OF_EFFECT, 5);

        // Giant
        GIANT_STATS.addStat(Statistic.HEALTH, 1000);
        GIANT_STATS.addStat(Statistic.DAMAGE, 100);
        GIANT_STATS.addStat(Statistic.COOLDOWN, 5);
        GIANT_STATS.addStat(Statistic.SPEED, 1);
        GIANT_STATS.addStat(Statistic.RANGE, 2);
        GIANT_STATS.addStat(Statistic.ACCURACY, 100);
        GIANT_STATS.addStat(Statistic.AGILITY, 0);
        GIANT_STATS.addStat(Statistic.AREA_OF_EFFECT, 3);

        // Alien
        ALIEN_STATS.addStat(Statistic.HEALTH, 1);
        ALIEN_STATS.addStat(Statistic.DAMAGE, 100);
        ALIEN_STATS.addStat(Statistic.COOLDOWN, 3);
        ALIEN_STATS.addStat(Statistic.SPEED, 0.5);
        ALIEN_STATS.addStat(Statistic.RANGE, 8);
        ALIEN_STATS.addStat(Statistic.ACCURACY, 100);
        ALIEN_STATS.addStat(Statistic.AGILITY, 10);

        // Ninja
        NINJA_STATS.addStat(Statistic.HEALTH, 10);
        NINJA_STATS.addStat(Statistic.DAMAGE, 150);
        NINJA_STATS.addStat(Statistic.COOLDOWN, 0.5);
        NINJA_STATS.addStat(Statistic.SPEED, 5);
        NINJA_STATS.addStat(Statistic.RANGE, 1);
        NINJA_STATS.addStat(Statistic.ACCURACY, 100);
        NINJA_STATS.addStat(Statistic.AGILITY, 90);

        // Horseman
        HORSEMAN_STATS.addStat(Statistic.HEALTH, 200);
        HORSEMAN_STATS.addStat(Statistic.DAMAGE, 100);
        HORSEMAN_STATS.addStat(Statistic.COOLDOWN, 2);
        HORSEMAN_STATS.addStat(Statistic.SPEED, 5);
        HORSEMAN_STATS.addStat(Statistic.RANGE, 5);
        HORSEMAN_STATS.addStat(Statistic.ACCURACY, 80);
        HORSEMAN_STATS.addStat(Statistic.AGILITY, 10);
        HORSEMAN_STATS.addStat(Statistic.ARMOR, 30);

        // Golem
        GOLEM_STATS.addStat(Statistic.HEALTH, 400);
        GOLEM_STATS.addStat(Statistic.DAMAGE, 500);
        GOLEM_STATS.addStat(Statistic.COOLDOWN, 8);
        GOLEM_STATS.addStat(Statistic.SPEED, 1);
        GOLEM_STATS.addStat(Statistic.RANGE, 1);
        GOLEM_STATS.addStat(Statistic.ACCURACY, 100);
        GOLEM_STATS.addStat(Statistic.AGILITY, 0);
        GOLEM_STATS.addStat(Statistic.ARMOR, 80);

        // Troll
        TROLL_STATS.addStat(Statistic.HEALTH, 100);
        TROLL_STATS.addStat(Statistic.DAMAGE, 30);
        TROLL_STATS.addStat(Statistic.COOLDOWN, 1);
        TROLL_STATS.addStat(Statistic.SPEED, 2);
        TROLL_STATS.addStat(Statistic.RANGE, 1);
        TROLL_STATS.addStat(Statistic.ACCURACY, 80);
        TROLL_STATS.addStat(Statistic.AGILITY, 60);
        TROLL_STATS.addStat(Statistic.ARMOR, 20);

        // Samurai
        SAMURAI_STATS.addStat(Statistic.HEALTH, 100);
        SAMURAI_STATS.addStat(Statistic.DAMAGE, 200);
        SAMURAI_STATS.addStat(Statistic.COOLDOWN, 3);
        SAMURAI_STATS.addStat(Statistic.SPEED, 1);
        SAMURAI_STATS.addStat(Statistic.RANGE, 5);
        SAMURAI_STATS.addStat(Statistic.ACCURACY, 95);
        SAMURAI_STATS.addStat(Statistic.AGILITY, 50);
        SAMURAI_STATS.addStat(Statistic.AREA_OF_EFFECT, 3);

        // Viking
        VIKING_STATS.addStat(Statistic.HEALTH, 300);
        VIKING_STATS.addStat(Statistic.DAMAGE, 100);
        VIKING_STATS.addStat(Statistic.COOLDOWN, 2);
        VIKING_STATS.addStat(Statistic.SPEED, 1);
        VIKING_STATS.addStat(Statistic.RANGE, 1);
        VIKING_STATS.addStat(Statistic.ACCURACY, 80);
        VIKING_STATS.addStat(Statistic.AGILITY, 20);
        VIKING_STATS.addStat(Statistic.ARMOR, 30);

        entityTypes.put("Peasant", new Pair<>(PEASANT_CREATOR, PEASANT_STATS));
        entityTypes.put("Knight", new Pair<>(KNIGHT_CREATOR, KNIGHT_STATS));
        entityTypes.put("Archer", new Pair<>(ARCHER_CREATOR, ARCHER_STATS));
        entityTypes.put("Mage", new Pair<>(MAGE_CREATOR, MAGE_STATS));
        entityTypes.put("Giant", new Pair<>(GIANT_CREATOR, GIANT_STATS));
        entityTypes.put("Alien", new Pair<>(ALIEN_CREATOR, ALIEN_STATS));
        entityTypes.put("Ninja", new Pair<>(NINJA_CREATOR, NINJA_STATS));
        entityTypes.put("Horseman", new Pair<>(HORSEMAN_CREATOR, HORSEMAN_STATS));
        entityTypes.put("Golem", new Pair<>(GOLEM_CREATOR, GOLEM_STATS));
        entityTypes.put("Troll", new Pair<>(TROLL_CREATOR, TROLL_STATS));
        entityTypes.put("Samurai", new Pair<>(SAMURAI_CREATOR, SAMURAI_STATS));
        entityTypes.put("Viking", new Pair<>(VIKING_CREATOR, VIKING_STATS));
    }

    /**
     * Gets the names of the available entity
     *
     * @return Set of the entities names
     */
    public static Set<String> getEntityTypes() {
        return entityTypes.keySet();
    }

    /**
     * Given an entity type name returns the corresponding copy of a stats object
     *
     * @param entity name if the entity type
     * @return the stats of the entity type (copy) (null if the unit doesnt exists)
     */
    public static Stats getEntityTypeStats(String entity) {
        var entityType = entityTypes.get(entity);
        if (entityType == null) return null;
        return new Stats(entityType.second);
    }

    /**
     * Given an entity type name returns the corresponding creator object
     *
     * @param entity name of the entity type
     * @return the creator of the entity type (null if the unit doesnt exists)
     */
    public static EntityCreator getEntityTypeCreator(String entity) {
        var entityType = entityTypes.get(entity);
        if (entityType == null) return null;
        return entityType.first;
    }

    /**
     * Given an entity type name removes the corresponding entity type
     *
     * @param type name of the type type
     * @return true if the type type was removed
     * @throws UnmodifiableTypeException if caller tries to delete an initial type
     */
    public static boolean dropEntityType(String type) throws UnmodifiableTypeException {
        if (INITIAL_UNIT.contains(type)) throw new UnmodifiableTypeException(type);
        var entityType = entityTypes.remove(type);
        save();
        return entityType != null;
    }

    /**
     * Creates an instance of an entity of given type and it's initial position
     *
     * @param type     name of the entity type
     * @param position of the entity initially
     * @return Instance of the entity
     */
    public static Entity createEntity(String type, Vector2 position) {
        Pair<EntityCreator, Stats> pair = entityTypes.get(type);
        return pair.first.createEntity(type, position, pair.second);
    }

    /**
     * Creates or edits a new entity type
     *
     * @param type    name of the type
     * @param creator the creator of the entity type
     * @param stats   the stats of the entity type
     * @return true if replaced an existing entity type
     * @throws UnmodifiableTypeException if caller tries to override an initial type
     */
    public static boolean setEntityType(String type, EntityCreator creator, Stats stats) throws UnmodifiableTypeException {
        if (INITIAL_UNIT.contains(type)) throw new UnmodifiableTypeException(type);
        boolean r = entityTypes.put(type, new Pair<>(creator, stats)) != null;
        save();
        return r;
    }

    /**
     * Saves the types of entities
     */
    private static void save() {
        //TODO
    }

    /**
     * Loads the types of entities
     */
    private static void load() {
        //TODO
    }

}
