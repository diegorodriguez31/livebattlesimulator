package main.java.fr.enseeiht.lbs.gameObject.unit.buff;

import main.java.fr.enseeiht.lbs.gameObject.unit.visitors.BuffVisitor;
import main.java.fr.enseeiht.lbs.gameObject.unit.Unit;

public interface Buff {
    void accept(BuffVisitor visitor);
    void update(Unit unit, double deltaTime);
}
