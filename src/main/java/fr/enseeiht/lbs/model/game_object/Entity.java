package main.java.fr.enseeiht.lbs.model.game_object;

import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.model.world.WorldElement;
import main.java.fr.enseeiht.lbs.utils.Vector2;

/**
 * Élément présent sur le terrain lors de la simulation de bataille.
 * Peut être assimilé à une unité ou à un bâtiment par exemple.
 */
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

    /**
     * Tue l'entité et la retire de la bataille
     */
    public void kill() {
        health = 0.0;
        if (isDead()) {
            removeFromBattle();
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public boolean isDead() {
        return getHealth() <= 0;
    }

    public String getName() {
        return name;
    }

    /**
     * Récupère l'élément de terrain sur lequel l'entité est présente
     * @return WorldElement l'élément de terrain
     */
    public WorldElement getFieldElement() {
        Vector2 pos = getPosition();
        return World.getInstance().getTile((int) (pos.getX() / World.MAX_POSITION_X * World.NB_TILES_X), (int) (pos.getY() / World.MAX_POSITION_Y * World.NB_TILES_Y));
    }
}
