package main.java.fr.enseeiht.lbs.model.gameObject.unit.buff;

import main.java.fr.enseeiht.lbs.model.gameObject.unit.visitors.IBuffVisitor;

public interface Buff {
    void accept(IBuffVisitor visitor);
}
