package main.java.fr.enseeiht.lbs.model.battle.simulator;

import main.java.fr.enseeiht.lbs.model.game.object.unit.Unit;

import java.util.ArrayList;
import java.util.List;

/**
 * Définit les armées qui vont s'affronter lors des simulations de batailles.
 */
public class Army {

    private List<Unit> units;

    /**
     * Identifiant de l'armée, utilisé pour la manipulation graphique.
     */
    private int armyIndex;

    public Army(int index) {
        this.units = new ArrayList<>();
        armyIndex = index;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void addUnit(Unit unit) {
        units.add(unit);
        unit.setTeam(this);
    }

    /**
     * Retourne le nombre d'unités en vie dans l'armée.
     * Une unité est en vie si elle a sa santé > 0.
     */
    public long getAliveUnitCount() {
        return units.stream().filter(unit -> unit.getHealth() > 0).count();
    }

    public int getArmyIndex() {
        return armyIndex;
    }
}
