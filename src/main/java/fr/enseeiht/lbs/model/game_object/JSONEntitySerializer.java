package main.java.fr.enseeiht.lbs.model.game_object;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import main.java.fr.enseeiht.lbs.utils.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JSONEntitySerializer implements EntitySerializer {

    public static final String PRIMITIVE_TYPE = "primitive_type";
    public static final String STATS = "stats";

    @Override
    public Map<? extends String, ? extends Pair<EntityPrimitiveTypes, Stats>> parse(String parse) {
        JsonElement root = JsonParser.parseString(parse);
        HashMap<String, Pair<EntityPrimitiveTypes, Stats>> entityTypes = new HashMap<>();
        for (Map.Entry<String, JsonElement> entry : root.getAsJsonObject().entrySet()) {
            entityTypes.put(entry.getKey(), deserializeEntityType(entry.getValue().getAsJsonObject()));
        }
        return entityTypes;
    }

    @Override
    public String write(HashMap<String, Pair<EntityPrimitiveTypes, Stats>> entityTypes, Set<String> excluded) {
        JsonObject jsonObject = new JsonObject();
        for (Map.Entry<String, Pair<EntityPrimitiveTypes, Stats>> entry : entityTypes.entrySet()) {
            if (!excluded.contains(entry.getKey()))
                jsonObject.add(entry.getKey(), serializeEntityType(entry.getValue()));
        }
        return jsonObject.toString();
    }

    private JsonElement serializeEntityType(Pair<EntityPrimitiveTypes, Stats> content) {
        JsonObject entityObject = new JsonObject();
        JsonObject statObject = new JsonObject();
        for (Map.Entry<Statistic, Double> entry :
                content.second.getStatistics().entrySet()) {
            statObject.add(entry.getKey().name(), new JsonPrimitive(entry.getValue()));
        }
        entityObject.add(STATS, statObject);
        entityObject.add(JSONEntitySerializer.PRIMITIVE_TYPE, new JsonPrimitive(content.first.name()));
        return entityObject;
    }

    private Pair<EntityPrimitiveTypes, Stats> deserializeEntityType(JsonObject content) {
        Stats stats = new Stats();
        for (Map.Entry<String, JsonElement> entry : content.get(STATS).getAsJsonObject().entrySet()) {
            stats.addStat(Statistic.valueOf(entry.getKey()), entry.getValue().getAsDouble());
        }
        return new Pair<>(EntityPrimitiveTypes.valueOf(content.get(PRIMITIVE_TYPE).getAsString()), stats);
    }
}
