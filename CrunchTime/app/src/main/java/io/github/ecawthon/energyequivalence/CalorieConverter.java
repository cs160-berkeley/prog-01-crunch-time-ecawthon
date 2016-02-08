package io.github.ecawthon.energyequivalence;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * The main activity for this simple, single-view app.
 * <p/>
 * We maintain an unordered collection of Exercises, and treat "Calories" as
 * an exercise.
 */
public class CalorieConverter extends AppCompatActivity {
    /**
     * Maps all exercise names to Exercise objects. Populated in onCreate.
     */
    protected HashMap<String, Exercise> excs;
    // Just the names of the exercises, in the order to be displayed in the
    // spinners.
    protected ArrayList<String> exc_names;

    // Spinners for picking the exercises
    protected Spinner inExcChooser, outExcChooser;
    // displays the unit of the corresponding spinner value
    protected TextView inUnit, outUnit;
    // Only allow the user to edit the input count; outCount
    // is calculated.
    protected EditText inCount;
    protected TextView outCount;

    /**
     * @return the appropriate blank in "___ is equivalent to ___".
     */
    private static String formatUnit(double count, Exercise e) {
        switch(e.getUnit()) {
            case CALORIE:
                return "burning " + count + " calories";
            default:
                return "doing " + count + " " + e.getUnit().name + " of " +
                        e.getName();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_converter);

        inExcChooser = (Spinner) findViewById(R.id.fromSpinner);
        outExcChooser = (Spinner) findViewById(R.id.toSpinner);
        inUnit = (TextView) findViewById(R.id.fromUnit);
        outUnit = (TextView) findViewById(R.id.toUnit);
        inCount = (EditText) findViewById(R.id.inputCount);
        outCount = (TextView) findViewById(R.id.outputCount);

        populateExcsMap();

        // Set up spinner functionality using the exercise data
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                android.R.id.text1, exc_names);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // default to converting the first exercise to calories
        initSpinner(inExcChooser, inUnit, 1, adapter);
        initSpinner(outExcChooser, outUnit, 0, adapter);
    }

    /**
     * Set up an exercise chooser spinner to interact with everything else
     *
     * @param s     the spinner
     * @param uView the TextView where the units of the exercise should be
     *              displayed
     * @param pos   index of the initial selection to display
     * @param a     the ArrayAdapter to initialize with
     */
    private void initSpinner(Spinner s,
                             TextView uView,
                             int pos,
                             ArrayAdapter a) {
        s.setAdapter(a);
        s.setSelection(pos);
        s.setOnItemSelectedListener(new ExerciseSelectedListener(uView, this));
    }

    /**
     * Update the converted units display. Called when the calculate button is
     * pushed.
     */
    public void convert(View v) {
        Exercise from = excs.get(inExcChooser.getSelectedItem().toString());
        Exercise to = excs.get(outExcChooser.getSelectedItem().toString());
        double in = Double.valueOf(String.valueOf(inCount.getText()));
        double conversionFactor;
        if (in != 0) {
            conversionFactor =
                    ((double) to.getPer_ccal()) / ((double) from.getPer_ccal());
        } else {
            conversionFactor = 0;
        }
        double out = conversionFactor * in;
        outCount.setText(String.valueOf(out));

        Toast.makeText(this, formatUnit(in, from) + " is equivalent to "
                + formatUnit(out, to) + ".", Toast.LENGTH_LONG).show();
    }

    /**
     * Parse strings.xml and integers.xml to get exercise units and
     * conversion rates.
     * <p/>
     * post condition: this.excs and this.exc_names contain all exercises,
     * including "Calories"
     */
    private void populateExcsMap() {
        exc_names = new ArrayList<>();
        exc_names.add("Calories");
        exc_names.addAll(Arrays.asList(getResources()
                .getStringArray(R.array.rep_exercises)));
        exc_names.addAll(Arrays.asList(getResources()
                .getStringArray(R.array.time_exercises)));

        final int[] rep_convs = getResources().getIntArray(R.array.rep_conv);
        final int[] time_convs = getResources().getIntArray(R.array.time_conv);

        excs = new HashMap<>();
        excs.put("Calories", new Exercise("Calories", Unit.CALORIE, 100));
        int i;
        for (i = 0; i < rep_convs.length; i++) {
            excs.put(exc_names.get(i + 1), new Exercise(exc_names.get(i + 1),
                    Unit.REP, rep_convs[i]));
        }
        for (; i < rep_convs.length + time_convs.length; i++) {
            excs.put(exc_names.get(i + 1), new Exercise(exc_names.get(i + 1),
                    Unit.MINUTE, time_convs[i - rep_convs.length]));
        }
    }


}
