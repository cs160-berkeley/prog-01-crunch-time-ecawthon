package io.github.ecawthon.crunchtime;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

/**
 * The OnItemSelectedListener for the spinners. Handles updating the units
 * according to the selected exercise.
 */
public class ExerciseSelectedListener implements AdapterView.OnItemSelectedListener {
    TextView uView;
    CalorieConverter cc;

    /**
     * @param unitView the TextView where the units of the selected exercise
     *                 should appear.
     * @param a        the CalorieConverter to which this spinner is attached.
     */
    ExerciseSelectedListener(TextView unitView, CalorieConverter a) {
        uView = unitView;
        cc = a;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        uView.setText(cc.excs.get(parent
                        .getItemAtPosition(pos).toString())
                        .getUnit().name);
        // Since we don't update the output until they hit "Calculate", clear
        // it when they change either exercise.
        cc.outCount.setText("");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
