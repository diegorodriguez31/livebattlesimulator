package main.java.fr.enseeiht.lbs.model.battleSimulator;

import main.java.fr.enseeiht.lbs.model.gameObject.unit.Unit;

import java.util.ArrayList;
import java.util.List;

public class Army {
    private static int maxArmyIndex = 0;

    private List<Unit> units;
    private int armyIndex;

    public Army() {
        this.units = new ArrayList<>();
        armyIndex = maxArmyIndex++;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void addUnit(Unit unit) {
        units.add(unit);
        unit.setTeam(this);
    }

    public long getAliveUnitCount() {
        return units.stream().filter(unit -> unit.getHealth() > 0).count();
    }

    public int getArmyIndex() {
        return armyIndex;
    }
}
