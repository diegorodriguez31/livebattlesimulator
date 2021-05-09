
import java.util.*;

public class Game extends Observable implements Observer {
    private List<Unit>[] units;
    long lastTime = 0;

    public Game(int nbTeams) {
        this.setUnits(new List[nbTeams]);
        for (int i = 0; i < nbTeams; i++) {
            this.getUnits()[i] = new ArrayList<>();
        }

    }

    public void addUnit(int team, Unit unit){
        getUnits()[team].add(unit);
        unit.addObserver(this);

    }

    public void addUnit(Unit unit){
        getUnits()[unit.getTeam()].add(unit);
        unit.addObserver(this);
    }

    public void start(){
        lastTime = System.currentTimeMillis();
        while (true){
            long deltaTime = System.currentTimeMillis()-lastTime;
            lastTime = System.currentTimeMillis();
            System.out.println("Tick (deltaTime : "+deltaTime+")");
            for (List<Unit> team:
                    this.getUnits()) {
                for (Unit unit:
                     team) {
                    unit.update(deltaTime, this);
                }
            }
            this.setChanged();
            this.notifyObservers();
            try {

                Thread.sleep(1000/60);
            }catch (InterruptedException e){
                System.err.println(e.getMessage());
            }
        }
    }

    public Unit findClosestEnemy(Vector2 pos, int team){
        float squaredDistance = Float.POSITIVE_INFINITY;
        Unit closestEnemy = null;
        for (int i = 0; i < getUnits().length; i++) {
            if (i != team) {
                for (Unit unit :
                        getUnits()[i]) {
                    var d = unit.getPosition().sub(pos).sqrdLength();
                    if (d<squaredDistance){
                        squaredDistance = d;
                        closestEnemy = unit;
                    }
                }
            }
        }
        return closestEnemy;
    }


    @Override
    public void update(Observable observable, Object o) {
        Unit u = (Unit) observable;
        getUnits()[u.getTeam()].remove(u);
        u.deleteObserver(this);
    }

    public List<Unit>[] getUnits() {
        return units;
    }

    public void setUnits(List<Unit>[] units) {
        this.units = units;
    }
}
