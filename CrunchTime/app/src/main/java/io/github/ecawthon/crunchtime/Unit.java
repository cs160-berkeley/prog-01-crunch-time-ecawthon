package io.github.ecawthon.crunchtime;

/**
 * Created by eleanor on 2/5/16.
 */
public enum Unit {
    REP("Reps"), MINUTE("Minutes"), CALORIE("Calories");
    String name;

    private Unit(String n) {
        this.name = n;
    }
}
