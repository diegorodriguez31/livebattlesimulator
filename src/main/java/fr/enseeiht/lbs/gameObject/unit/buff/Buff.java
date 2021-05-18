package main.java.fr.enseeiht.lbs.gameObject.unit.buff;

import main.java.fr.enseeiht.lbs.gameObject.unit.visitors.IBuffVisitor;
import main.java.fr.enseeiht.lbs.gameObject.unit.visitors.SBuffVisitor;
import main.java.fr.enseeiht.lbs.gameObject.unit.Unit;

public interface Buff {
    void accept(IBuffVisitor visitor);
}
