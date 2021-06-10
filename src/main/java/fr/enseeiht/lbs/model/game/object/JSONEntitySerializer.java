package main.java.fr.enseeiht.lbs.model.game.object;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import main.java.fr.enseeiht.lbs.utils.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Allows an entity to be parsed into JSON with the GSON library
 */
public class JSONEntitySerializer implements EntitySerializer {

    public static final String PRIMITIVE_TYPE = "primitive_type";
    public static final String STATS = "stats";

    /**
     * Given a string this function will parse it into a list of entity types
     *
     * @param parse the string to be parsed
     * @return the map containing the entities types
     */
    @Override
    public Map<? extends String, ? extends Pair<EntityPrimitiveTypes, Stats>> parse(String parse) {
        JsonElement root = JsonParser.parseString(parse);
        HashMap<String, Pair<EntityPrimitiveTypes, Stats>> entityTypes = new HashMap<>();
        if (root.isJsonNull()) return entityTypes;
        for (Map.Entry<String, JsonElement> entry : root.getAsJsonObject().entrySet()) {
            entityTypes.put(entry.getKey(), deserializeEntityType(entry.getValue().getAsJsonObject()));
        }
        return entityTypes;
    }

    /**
     * Given a map of all the entities this function generates a string that represent all entities types
     *
     * @param entityTypes map of all the entities to be written to a string
     * @param excluded    list of entities to avoid writing
     * @return string that represent all entities types
     */
    @Override
    public String write(HashMap<String, Pair<EntityPrimitiveTypes, Stats>> entityTypes, Set<String> excluded) {
        JsonObject jsonObject = new JsonObject();
        for (Map.Entry<String, Pair<EntityPrimitiveTypes, Stats>> entry : entityTypes.entrySet()) {
            if (!excluded.contains(entry.getKey()))
                jsonObject.add(entry.getKey(), serializeEntityType(entry.getValue()));
        }
        return jsonObject.toString();
    }

    /**
     * Create a json object for the content of an entity type
     *
     * @param content of an entity type
     * @return corresponding json object
     */
    private JsonElement serializeEntityType(Pair<EntityPrimitiveTypes, Stats> content) {
        JsonObject entityObject = new JsonObject();
        JsonObject statObject = new JsonObject();
        for (Map.Entry<Statistic, Double> entry :
                content.getSecond().getStatistics().entrySet()) {
            statObject.add(entry.getKey().name(), new JsonPrimitive(entry.getValue()));
        }
        entityObject.add(STATS, statObject);
        entityObject.add(JSONEntitySerializer.PRIMITIVE_TYPE, new JsonPrimitive(content.getFirst().name()));
        return entityObject;
    }

    /**
     * Given a json object return the content of an unit type
     *
     * @param content object to be parsed
     * @return entity type content
     */
    private Pair<EntityPrimitiveTypes, Stats> deserializeEntityType(JsonObject content) {
        Stats stats = new Stats();
        for (Map.Entry<String, JsonElement> entry : content.get(STATS).getAsJsonObject().entrySet()) {
            stats.addStat(Statistic.valueOf(entry.getKey()), entry.getValue().getAsDouble());
        }
        return new Pair<>(EntityPrimitiveTypes.valueOf(content.get(PRIMITIVE_TYPE).getAsString()), stats);
    }
}
