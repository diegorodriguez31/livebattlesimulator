package main.java.fr.enseeiht.lbs.model.gameObject.unit.action;

import main.java.fr.enseeiht.lbs.model.gameObject.Statistic;
import main.java.fr.enseeiht.lbs.model.gameObject.Vector2;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.Unit;

public class FlightMovementAction implements IMovementAction {
    private Unit self;
    private Vector2 target;

    private final int SUPER_PIXEL_SIZE = 11;

    public FlightMovementAction(Unit self) {
        this.self = self;
    }

    @Override
    public void setTarget(Vector2 target) {
        this.target = target;
    }

    @Override
    public void execute(long deltaTime) {
        this.self.getPosition().inc(
                target.sub(self.getPosition())
                        .normalize(
                                (float) self.getStats().getStatisticValue(Statistic.SPEED)*SUPER_PIXEL_SIZE*deltaTime/1000));
    }
}
