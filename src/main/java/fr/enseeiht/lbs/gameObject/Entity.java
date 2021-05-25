package main.java.fr.enseeiht.lbs.gameObject;

public abstract class Entity extends GameObject {
    protected double health;
    protected Stats stats;
    protected Vector2 position;
    
    public Entity(float x, float y) {
    	this.position = new Vector2(x, y);
    }

    public Stats getStats() {
        return stats;
    }

    public double getHealth() {
        return health;
    }

    public void receiveDamage(double damage){
        health -= damage;
        if (isDead()){
            this.removeFromBattle();
        }
    }

    public boolean isDead(){
        return getHealth()<=0;
    }

	public Vector2 getPosition() {
		return position;
	}

	
}
