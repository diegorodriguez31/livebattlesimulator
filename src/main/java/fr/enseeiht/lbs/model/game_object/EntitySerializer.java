package main.java.fr.enseeiht.lbs.model.game_object;

import main.java.fr.enseeiht.lbs.utils.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface EntitySerializer {
    Map<? extends String, ? extends Pair<EntityPrimitiveTypes, Stats>> parse(String parse);

    String write(HashMap<String, Pair<EntityPrimitiveTypes, Stats>> entityTypes, Set<String> excluded);
}
