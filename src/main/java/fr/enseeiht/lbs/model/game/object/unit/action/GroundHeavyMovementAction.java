package main.java.fr.enseeiht.lbs.model.game.object.unit.action;

import main.java.fr.enseeiht.lbs.model.game.object.Statistic;
import main.java.fr.enseeiht.lbs.model.game.object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game.object.unit.buff.FireDebuff;
import main.java.fr.enseeiht.lbs.model.world.WorldElement;
import main.java.fr.enseeiht.lbs.utils.Vector2;

/**
 * Deplacement d'une unité lourde qui s'adapte au terrain.
 */
public class GroundHeavyMovementAction implements IMovementAction{

    private Unit self;
    private Vector2 target;

    /**
     * Multiplicateurs de vitesses en fonction du terrain.
     */
    private final double DESERT_SPEED_MULTIPLIER = 0.5;
    private final double WATER_SPEED_MULTIPLIER = 0.2;
    private final double SNOW_SPEED_MULTIPLIER = 0.6;
    private final double LAVA_SPEED_MULTIPLIER = 1.5;
    private final double FOREST_SPEED_MULTIPLIER = 0.8;

    public GroundHeavyMovementAction(Unit self) {
        this.self = self;
    }

    /**
     * On adapte la vitesse au terrain sur lequel l'unité se trouve.
     * La lave ajoute un débuff de feu et fait courrir les unités plus vite.
     */
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
                self.addBuffs(new FireDebuff(20));
                break;
            case FOREST:
                speed *= FOREST_SPEED_MULTIPLIER;
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

