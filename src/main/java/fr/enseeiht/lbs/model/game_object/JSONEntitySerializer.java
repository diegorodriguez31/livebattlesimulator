package main.java.fr.enseeiht.lbs.model.game_object;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import main.java.fr.enseeiht.lbs.utils.Pair;

import java.util.HashMap;
import java.util.Map;

public class JSONEntitySerializer implements EntitySerializer {
    @Override
    public void parse(String parse) {
        JsonElement root = JsonParser.parseString(parse);
        for (Map.Entry<String, JsonElement> entry : root.getAsJsonObject().entrySet()) {

        }
    }

    @Override
    public String write(HashMap<String, Pair<EntityPrimitiveTypes, Stats>> entityTypes) {
        JsonObject jsonObject = new JsonObject();
        for (Map.Entry<String, Pair<EntityPrimitiveTypes, Stats>> entry : entityTypes.entrySet()) {
            jsonObject.add(entry.getKey(), serializeEntityType(entry.getValue()));
        }
        return jsonObject.toString();
    }

    private JsonElement serializeEntityType(Pair<EntityPrimitiveTypes, Stats> content) {
        JsonObject entityObject = new JsonObject();
        JsonObject statObject = new JsonObject();
        for (Map.Entry<Statistic, Double> entry :
                content.second.getStatistics().entrySet()) {
            statObject.add(entry.getKey().toString(), new JsonPrimitive(entry.getValue()));
        }
        entityObject.add("stats", statObject);
        entityObject.add("primitive_type", new JsonPrimitive(content.first.toString()));
        return entityObject;
    }
}
