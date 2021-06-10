package main.java.fr.enseeiht.lbs.model.game.object.unit.action;

import main.java.fr.enseeiht.lbs.model.game.object.Statistic;
import main.java.fr.enseeiht.lbs.model.game.object.unit.Unit;
import main.java.fr.enseeiht.lbs.utils.Vector2;

/**
 * Déplacement d'une unité sans prendre en compte les changements de terrain.
 */
public class FlightMovementAction implements IMovementAction {
    private Unit self;
    private Vector2 target;

    public FlightMovementAction(Unit self) {
        this.self = self;
    }

    @Override
    public void setTarget(Vector2 target) {
        this.target = target;
    }

    @Override
    public void execute(long deltaTime) {
        float speed = (float) self.getStats().getStatisticValue(Statistic.SPEED) * deltaTime / 1000;
        Vector2 deltaPos = target.sub(self.getPosition());
        float traveledDistance = Math.min(deltaPos.size(), speed);
        this.self.getPosition().inc(deltaPos.normalize(traveledDistance));
    }
}
