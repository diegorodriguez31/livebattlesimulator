package main.java.fr.enseeiht.lbs.gameObject.unit.buff;

import main.java.fr.enseeiht.lbs.gameObject.unit.visitors.IBuffVisitor;

public interface Buff {
    void accept(IBuffVisitor visitor);
}
