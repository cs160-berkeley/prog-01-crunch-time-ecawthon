package io.github.ecawthon.crunchtime;

/**
 * Created by eleanor on 2/5/16.
 */
public class Exercise {
    String name;
    Unit unit;
    int per_ccal;

    Exercise(String exc, Unit u, int n) {
        name = exc;
        unit = u;
        per_ccal = n;
    }

    public String getName() {
        return name;
    }

    public Unit getUnit() {
        return unit;
    }

    public int getPer_ccal() {
        return per_ccal;
    }

}
