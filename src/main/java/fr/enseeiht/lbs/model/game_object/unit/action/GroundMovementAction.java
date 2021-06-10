package main.java.fr.enseeiht.lbs.model.game_object.unit.action;

import main.java.fr.enseeiht.lbs.model.game_object.Statistic;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.FireDebuff;
import main.java.fr.enseeiht.lbs.model.world.WorldElement;
import main.java.fr.enseeiht.lbs.utils.Vector2;

public class GroundMovementAction implements IMovementAction {
    private Unit self;
    private Vector2 target;

    private final double DESERT_SPEED_MULTIPLIER = 0.7;
    private final double WATER_SPEED_MULTIPLIER = 0.3;
    private final double SNOW_SPEED_MULTIPLIER = 0.5;
    private final double LAVA_SPEED_MULTIPLIER = 2.0;
    private final long LAVA_TIC_DAMAGE = 50;
    private final long LAVA_TIC_DURATION = 1000;

    public GroundMovementAction(Unit self) {
        this.self = self;
    }

    @Override
    public void execute(long deltaTime) {
        double speed = self.getStats().getStatisticValue(Statistic.SPEED) * deltaTime / 1000;

        WorldElement element = this.self.getFieldElement();
        switch (element) {
            case DESERT:
                speed *= DESERT_SPEED_MULTIPLIER;
                break;
            case WATER:
                speed *= WATER_SPEED_MULTIPLIER;
                break;
            case SNOW:
                speed *= SNOW_SPEED_MULTIPLIER;
                break;
            case LAVA:
                speed *= LAVA_SPEED_MULTIPLIER;
                self.addBuffs(new FireDebuff(LAVA_TIC_DAMAGE, LAVA_TIC_DURATION));
                break;
            default:
                break;
        }

        Vector2 deltaPos = target.sub(self.getPosition());
        double traveledDistance = Math.min(deltaPos.size(), speed);
        this.self.getPosition().inc(deltaPos.normalize((float) traveledDistance));
    }

    @Override
    public void setTarget(Vector2 target) {
        this.target = target;
    }
}
