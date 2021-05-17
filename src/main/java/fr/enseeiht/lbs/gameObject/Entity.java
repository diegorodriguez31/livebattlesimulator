package main.java.fr.enseeiht.lbs.gameObject;

public abstract class Entity implements GameObject {
    protected double health;
    protected Stats stats;
    protected Vector2 position;

    public Stats getStats() {
        return stats;
    }

    public double getHealth() {
        return health;
    }

    public void receiveDamage(double damage){
        health -= damage;
    }
}
