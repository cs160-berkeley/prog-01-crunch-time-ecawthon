package io.github.ecawthon.energyequivalence;

/**
 * The possible units for exercises
 */
public enum Unit {
    REP("Rep(s)"), MINUTE("Minute(s)"), CALORIE("Calorie(s)");
    String name;

    Unit(String n) {
        this.name = n;
    }
}
