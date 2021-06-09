package main.java.fr.enseeiht.lbs.model.game_object;

import main.java.fr.enseeiht.lbs.LiveBattleSimulator;
import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.model.world.WorldElement;
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

    public void kill() {
        health = 0.0;
        if (isDead()) {
            removeFromBattle();
        }
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

    public WorldElement getFieldElement(){
        Vector2 position = this.getPosition();
        int entityY = (int) (position.getY());
        int entityX = (int)(position.getX());
        if(entityY >= World.MAX_POSITION_Y){ entityY = World.MAX_POSITION_Y;}
        if(entityX >= World.MAX_POSITION_X){ entityX = World.MAX_POSITION_X;}
        getSqrSize();
        for( int y=0;y<World.NB_TILES_Y;y++){
            if(y == entityY) {
                for (int x = 0; x < World.NB_TILES_X; x++) {
                    if ((x == entityX)){
                        return World.getInstance().getTile(x,y);
                    }
                }
            }
        }
        return null;
    }
    public void getSqrSize(){
        if (LiveBattleSimulator.VERBOSE >= 2){
            System.out.println(getPosition().size());
        }
        //max array is 400.
    }
}
