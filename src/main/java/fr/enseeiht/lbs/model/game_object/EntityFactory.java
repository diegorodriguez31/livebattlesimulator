package main.java.fr.enseeiht.lbs.model.game_object;

import main.java.fr.enseeiht.lbs.model.game_object.creators.KnightCreator;
import main.java.fr.enseeiht.lbs.model.game_object.creators.PeasantCreator;
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
    private static final Set<String> INITIAL_UNIT = new HashSet<>(Arrays.asList("Farmer", "Knight"));

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
        return new Stats(entityType.getSecond());
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
        return entityType.getFirst();
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
        return pair.getFirst().createEntity(type, position, pair.getSecond());
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
