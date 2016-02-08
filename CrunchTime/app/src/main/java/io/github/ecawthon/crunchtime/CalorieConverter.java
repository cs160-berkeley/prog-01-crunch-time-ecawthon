package io.github.ecawthon.crunchtime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.HashMap;

public class CalorieConverter extends AppCompatActivity {
    HashMap<String, Exercise> excs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_converter);

        final String[] rep_names
                = getResources().getStringArray(R.array.rep_exercises);
        final int[] rep_convs //new int[]{350, 200, 225, 100};
            = getResources().getIntArray(R.array.rep_conv);
        final String[] time_names
                = getResources().getStringArray(R.array.time_exercises);
        final int[] time_convs //new int[]{10, 12, 25, 25, 12, 20, 12, 13,
        // 15};
            = getResources().getIntArray(R.array.time_conv);

        excs = lists_to_exc_set(Unit.REP, rep_names, rep_convs);
        excs.putAll(lists_to_exc_set(Unit.MINUTE, time_names, time_convs));
        excs.put("Calories", new Exercise("Calories", Unit.CALORIE, 100));

        Spinner inExcChooser = (Spinner) findViewById(R.id.fromSpinner);
        Spinner outExcChooser = (Spinner) findViewById(R.id.toSpinner);
        TextView inUnit = (TextView) findViewById(R.id.fromUnit);
        TextView outUnit = (TextView) findViewById(R.id.toUnit);
        ExerciseSelectedListener inListener =
                new ExerciseSelectedListener(inUnit, this);
        ExerciseSelectedListener outListener =
                new ExerciseSelectedListener(outUnit, this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                android.R.id.text1,
                excs.keySet().toArray(new String[excs.size()]));
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        inExcChooser.setAdapter(adapter);
        inExcChooser.setSelection(0);
        inExcChooser.setOnItemSelectedListener(inListener);
        outExcChooser.setAdapter(adapter);
        outExcChooser.setSelection(0);
        outExcChooser.setOnItemSelectedListener(outListener);


    }

    private static HashMap<String, Exercise> lists_to_exc_set(Unit unit,
                                                              String[] names,
                                                              int[] convs) {
        HashMap<String, Exercise> excs = new HashMap<>();
        for (int i = 0; i < names.length; i++) {
            excs.put(names[i], new Exercise(names[i], unit, convs[i]));
        }
        return excs;
    }


}
