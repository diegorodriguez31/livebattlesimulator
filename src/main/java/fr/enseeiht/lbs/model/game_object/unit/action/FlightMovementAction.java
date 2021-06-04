package main.java.fr.enseeiht.lbs.model.game_object.unit.action;

import main.java.fr.enseeiht.lbs.model.game_object.Statistic;
import main.java.fr.enseeiht.lbs.model.game_object.Vector2;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;

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
