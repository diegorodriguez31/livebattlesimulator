package main.java.fr.enseeiht.lbs.model.game_object;

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

    public Vector2 getPosition() {
        return position;
    }

    public boolean isDead() {
        return getHealth() <= 0;
    }

    public String getName() {
        return name;
    }

    public WorldElement getFieldElement(){
        Vector2 position = this.getPosition();
        int EntityY = (int) (position.getY()*1.08);
        int EntityX = (int)(position.getX()*1.85);
        if(EntityY >= World.getInstance().getSizeY()){ EntityY = World.getInstance().getSizeY();}
        if(EntityX >= World.getInstance().getSizeX()){ EntityX = World.getInstance().getSizeX();}
        getSqrSize();
        for( int y=0;y<World.getInstance().getSizeY();y++){
            if(y == EntityY) {
                for (int x = 0; x < World.getInstance().getSizeX(); x++) {
                    if ((x == EntityX)){
                        return World.getInstance().getTile(x,y);
                    }
                }
            }
        }
        return null;
    }
    public void getSqrSize(){
        System.out.println(getPosition().size());//max is around  with 93.5*60.15
        //max array is 400.
    }
}
