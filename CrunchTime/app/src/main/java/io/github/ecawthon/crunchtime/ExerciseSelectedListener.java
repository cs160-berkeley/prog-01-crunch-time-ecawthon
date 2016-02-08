package io.github.ecawthon.crunchtime;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by eleanor on 2/6/16.
 */
public class ExerciseSelectedListener implements AdapterView.OnItemSelectedListener {
    TextView uView;
    CalorieConverter cc;

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
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
