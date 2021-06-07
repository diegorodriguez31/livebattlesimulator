package main.java.fr.enseeiht.lbs.model.game_object.unit.action;

import main.java.fr.enseeiht.lbs.model.game_object.Statistic;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.world.WorldElement;
import main.java.fr.enseeiht.lbs.utils.Vector2;

public class GroundMovementAction implements IMovementAction {
    private Unit self;
    private Vector2 target;

    public GroundMovementAction(Unit self) {
        this.self = self;
    }
    @Override
    public void execute(long deltaTime) {
        float speed;
        if (this.self.getFieldElement() == WorldElement.DESERT){
            speed = (float) ((self.getStats().getStatisticValue(Statistic.SPEED) * deltaTime / 1000));
            speed = (float) (0.7*speed);
        }else if(this.self.getFieldElement() == WorldElement.WATER){
            speed = (float) ((self.getStats().getStatisticValue(Statistic.SPEED) * deltaTime / 1000));
            speed = (float) (0.3*speed);
        } else{
            speed = (float) self.getStats().getStatisticValue(Statistic.SPEED) * deltaTime / 1000;
        }

        Vector2 deltaPos = target.sub(self.getPosition());
        float traveledDistance = Math.min(deltaPos.size(), speed);
        this.self.getPosition().inc(deltaPos.normalize(traveledDistance));
    }

    @Override
    public void setTarget(Vector2 target) {this.target = target;

    }
}
