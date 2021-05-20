package main.java.fr.enseeiht.lbs.battleSimulator;

import main.java.fr.enseeiht.lbs.gameObject.unit.Unit;

import java.util.ArrayList;
import java.util.List;

public class Army {
    private List<Unit> units;

    public Army() {
        this.units = new ArrayList<>();
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void addUnit(Unit unit){
        units.add(unit);
    }

    public long getAliveUnitCount(){
        return units.stream().filter(unit -> unit.getHealth()>0).count();
    }
}
