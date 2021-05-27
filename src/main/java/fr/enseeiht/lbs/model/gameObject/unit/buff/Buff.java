package main.java.fr.enseeiht.lbs.model.gameObject.unit.buff;

import main.java.fr.enseeiht.lbs.model.gameObject.unit.visitor.BuffVisitor;

public interface Buff {
    void accept(BuffVisitor visitor);
}
