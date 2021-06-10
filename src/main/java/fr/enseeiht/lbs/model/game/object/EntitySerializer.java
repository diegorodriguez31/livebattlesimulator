package main.java.fr.enseeiht.lbs.model.game.object;

import main.java.fr.enseeiht.lbs.utils.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Allows an entity to be parsed
 */
public interface EntitySerializer {
    /**
     * Given a string this function will parse it into a list of entity types
     *
     * @param parse the string to be parsed
     * @return the map containing the entities types
     */
    Map<? extends String, ? extends Pair<EntityPrimitiveTypes, Stats>> parse(String parse);

    /**
     * Given a map of all the entities this function generates a string that represent all entities types
     *
     * @param entityTypes map of all the entities to be written to a string
     * @param excluded    list of entities to avoid writing
     * @return string that represent all entities types
     */
    String write(HashMap<String, Pair<EntityPrimitiveTypes, Stats>> entityTypes, Set<String> excluded);
}
