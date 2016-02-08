package io.github.ecawthon.energyequivalence;

/**
 * an Exercise groups information about the name of the exercise, the units
 * in which it is measured, and the conversion from those units to calories.
 */
public class Exercise {
    private String name;
    private Unit unit;
    private int per_ccal;

    /**
     * @param exc the name of the exercise
     * @param u   the unit in which it is measured
     * @param n   per_ccal of unit is equivalent to 100 calories
     */
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
        return per_ccal; }

}
