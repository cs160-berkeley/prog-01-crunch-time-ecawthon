package io.github.ecawthon.crunchtime;

/**
 * Created by eleanor on 2/5/16.
 */
public enum Unit {
    REP("Rep(s)"), MINUTE("Minute(s)"), CALORIE("Calorie(s)");
    String name;

    private Unit(String n) {
        this.name = n;
    }
}
