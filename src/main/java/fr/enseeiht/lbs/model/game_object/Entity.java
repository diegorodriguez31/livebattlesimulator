package main.java.fr.enseeiht.lbs.model.game_object;

import main.java.fr.enseeiht.lbs.utils.Vector2;

public abstract class Entity extends GameObject {
    private String name;
    protected double health;
    protected Stats stats;
    protected Vector2 position;

    public Entity(String name, double health, Vector2 position) {
        this.name = name;
        this.health = health;
        this.position = position;
    }

    public Entity(String name, Stats stats, Vector2 position) {
        this.name = name;
        this.health = stats.getStatisticValue(Statistic.HEALTH);
        this.stats = stats;
        this.position = position;
    }

    public Stats getStats() {
        return stats;
    }

    public double getHealth() {
        return health;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getFloatPosition() {
        return getPosition().sqrSize();
    }

    public boolean isDead() {
        return getHealth() <= 0;
    }

    public String getName() {
        return name;
    }
}
