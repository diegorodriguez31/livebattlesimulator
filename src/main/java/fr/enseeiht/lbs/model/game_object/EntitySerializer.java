package main.java.fr.enseeiht.lbs.model.game_object;

import main.java.fr.enseeiht.lbs.utils.Pair;

import java.util.HashMap;

public interface EntitySerializer {
    void parse(String parse);

    String write(HashMap<String, Pair<EntityPrimitiveTypes, Stats>> entityTypes);
}
