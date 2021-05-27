package main.java.fr.enseeiht.lbs.model.battleSimulator;

import main.java.fr.enseeiht.lbs.model.gameObject.unit.Unit;

import java.util.ArrayList;
import java.util.List;

public class Army {
    private static int maxColorIndex = 0;

    private List<Unit> units;
    private int colorIndex;

    public Army() {
        this.units = new ArrayList<>();
        colorIndex = maxColorIndex++;
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

    public int getColorIndex() {
        return colorIndex;
    }
}
