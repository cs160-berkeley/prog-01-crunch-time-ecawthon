package io.github.ecawthon.crunchtime;

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

public class CalorieConverter extends AppCompatActivity {
    HashMap<String, Exercise> excs;

    Spinner inExcChooser, outExcChooser;
    TextView inUnit, outUnit;
    EditText inCount;
    TextView outCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_converter);

        ArrayList<String> exc_names = new ArrayList<String>();
        exc_names.add("Calories");
        exc_names.addAll(Arrays.asList(getResources()
                        .getStringArray(R.array.rep_exercises)));
        exc_names.addAll(Arrays.asList(getResources()
                .getStringArray(R.array.time_exercises)));

        final int[] rep_convs //new int[]{350, 200, 225, 100};
            = getResources().getIntArray(R.array.rep_conv);
        final int[] time_convs  = getResources().getIntArray(R.array.time_conv);

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
        assert(excs.size() == rep_convs.length + time_convs.length &&
        excs.size() == exc_names.size());

        inExcChooser = (Spinner) findViewById(R.id.fromSpinner);
        outExcChooser = (Spinner) findViewById(R.id.toSpinner);
        inUnit = (TextView) findViewById(R.id.fromUnit);
        outUnit = (TextView) findViewById(R.id.toUnit);
        inCount = (EditText) findViewById(R.id.inputCount);
        outCount = (TextView) findViewById(R.id.outputCount);
        ExerciseSelectedListener inListener =
                new ExerciseSelectedListener(inUnit, this);
        ExerciseSelectedListener outListener =
                new ExerciseSelectedListener(outUnit, this);

        String[] a = new String[exc_names.size()];
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                android.R.id.text1, exc_names.toArray(a));
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        inExcChooser.setAdapter(adapter);
        inExcChooser.setSelection(1);
        inExcChooser.setOnItemSelectedListener(inListener);
        outExcChooser.setAdapter(adapter);
        outExcChooser.setSelection(0);
        outExcChooser.setOnItemSelectedListener(outListener);


    }

    public void convert(View v) {
        Exercise from = excs.get(inExcChooser.getSelectedItem().toString());
        Exercise to = excs.get(outExcChooser.getSelectedItem().toString());
        double in = Double.valueOf(String.valueOf(inCount.getText()));
        double conversionFactor = ((double) to.per_ccal) / ((double)from.per_ccal);
        double out = conversionFactor * in;
        outCount.setText(String.valueOf(out));

        Toast.makeText(this, formatUnit(in, from) + " is equivalent to "
                + formatUnit(out, to) + ".", Toast.LENGTH_LONG).show();
    }

    private static String formatUnit(double count, Exercise e) {
        switch(e.unit) {
            case CALORIE:
                return "burning " + count + " calories";
            default:
                return "doing " + count + " " + e.unit.name + " of " + e.getName();

        }
    }


}
