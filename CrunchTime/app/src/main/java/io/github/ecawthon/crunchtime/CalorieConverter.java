package io.github.ecawthon.crunchtime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CalorieConverter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_converter);

        final Spinner excChooser = (Spinner) findViewById(R.id.excSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                android.R.id.text1, getResources().getStringArray(R.array.exercises));
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        excChooser.setAdapter(adapter);
        excChooser.setSelection(0);

    }


}
