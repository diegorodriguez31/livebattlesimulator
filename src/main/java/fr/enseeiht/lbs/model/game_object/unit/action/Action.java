package main.java.fr.enseeiht.lbs.model.game_object.unit.action;

/**
 * Action effectuée par une entité lors d'une bataille
 */
public interface Action {
    void execute(long deltaTime);
}
