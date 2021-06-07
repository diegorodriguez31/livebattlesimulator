package main.java.fr.enseeiht.lbs.model.game_object;

import main.java.fr.enseeiht.lbs.utils.Pair;
import main.java.fr.enseeiht.lbs.utils.Vector2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class EntityFactory {
    /**
     * Exception triggered in the case someone tries to modified the base unit of the game
     */
    static class UnmodifiableTypeException extends Exception {
        public UnmodifiableTypeException(String types) {
            super("Cant modify entity type : " + types);
        }
    }

    private static final EntitySerializer SERIALIZER = new JSONEntitySerializer();
    private static final Set<String> INITIAL_UNITS = new HashSet<String>(Arrays.asList("Farmer", "Knight"));
    private static final String SAVE_PATH = "save/units.json";

    private static final Stats PEASANT_STATS = new Stats();
    private static final Stats KNIGHT_STATS = new Stats();

    static final HashMap<String, Pair<EntityPrimitiveTypes, Stats>> entityTypes = new HashMap<>();

    static {
        //Initialisation of the units type

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

        entityTypes.put("Farmer", new Pair<>(EntityPrimitiveTypes.PEASANT, PEASANT_STATS));
        entityTypes.put("Knight", new Pair<>(EntityPrimitiveTypes.KNIGHT, KNIGHT_STATS));
        try {
            load();
        } catch (IOException e) {
            System.err.println("Could not load the entities correctly");
        }
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
    public static EntityPrimitiveTypes getEntityPrimitiveType(String entity) {
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
        if (INITIAL_UNITS.contains(type)) throw new UnmodifiableTypeException(type);
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
        Pair<EntityPrimitiveTypes, Stats> pair = entityTypes.get(type);
        return pair.first.getCreator().createEntity(type, position, pair.second);
    }

    /**
     * Creates or edits a new entity type
     *
     * @param type      name of the type
     * @param primitive the creator of the entity type
     * @param stats     the stats of the entity type
     * @return true if replaced an existing entity type
     * @throws UnmodifiableTypeException if caller tries to override an initial type
     */
    public static boolean setEntityType(String type, EntityPrimitiveTypes primitive, Stats stats) throws UnmodifiableTypeException {
        if (INITIAL_UNITS.contains(type)) throw new UnmodifiableTypeException(type);
        boolean r = entityTypes.put(type, new Pair<>(primitive, stats)) != null;
        save();
        return r;
    }

    /**
     * Saves the types of entities
     */
    public static void save() {
        try {
            Files.writeString(Path.of(SAVE_PATH), SERIALIZER.write(entityTypes, INITIAL_UNITS));
        } catch (IOException e) {
            System.err.println("Failed to write the entities");
        }
    }

    /**
     * Loads the types of entities
     */
    private static void load() throws IOException {
        entityTypes.putAll(SERIALIZER.parse(Files.readString(Path.of(SAVE_PATH))));
    }

}
