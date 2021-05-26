package main.java.fr.enseeiht.lbs.gameObject.unit.buff;

import main.java.fr.enseeiht.lbs.gameObject.unit.visitors.BuffVisitor;

public interface Buff {
    void accept(BuffVisitor visitor);
}
