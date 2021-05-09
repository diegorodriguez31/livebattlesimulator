import java.util.Observable;

public class Unit extends Observable{
    private Vector2 position;
    private int health, team, damage, attackSpeed;
    private float reach,cdr, speed;

    public Unit(Vector2 position, int health, int team, int damage, float reach, int attackSpeed, float speed) {
        this.position = position;
        this.health = health;
        this.team = team;
        this.damage = damage;
        this.reach = reach;
        this.attackSpeed = attackSpeed;
        cdr = 0;
        this.speed = speed;
    }

    public float getReach() {
        return reach;
    }

    public int getTeam() {
        return team;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void update(long deltaTime, Game context){
        Unit closest = context.findClosestEnemy(position, team);
        if (closest==null)return;
        cdr -= deltaTime;
        if (closest.position.sub(getPosition()).length()<reach){
            attack(closest);
        }else {
            var dif = closest.getPosition().sub(getPosition());
            dif.scale(speed*deltaTime/dif.length()/1000);
            this.position.inc(dif);
        }
    }

    public void attack(Unit other){
        if (other.position.sub(getPosition()).length()<reach && cdr < 0) {
            System.out.println("pow");
            other.recieveAttack(this.damage);
            cdr = 1000f/attackSpeed;
        }
    }

    public void recieveAttack(int damage){
        health -=damage;
        if (health<0){
            setChanged();
            notifyObservers();
        }

    }


}
