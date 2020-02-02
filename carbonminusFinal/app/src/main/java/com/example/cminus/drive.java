package com.example.cminus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class drive extends Activity {
    DatabaseHelper mDatabaseHelper;
    public double driveEmission = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drive_window);
        mDatabaseHelper = new DatabaseHelper(this);



        configureDriveCancelButton();

        configureDriveSubmitButton();

        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        String[] items = new String[]{"Car", "Bus", "Plane"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

    }

    private void configureDriveCancelButton() {
        Button drivecancel = findViewById(R.id.buttoncancel);
        drivecancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }

        });
    }

    public void configureDriveSubmitButton() {
        Button drivesubmit = findViewById(R.id.buttonsubmit);
        drivesubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                calculateDriveprint();
                Log.d("drivingemission", driveEmission+"");
                String newEntry = driveEmission+"";
                mDatabaseHelper.addData(newEntry);
                TextView bubble1 = findViewById(R.id.bubble1);

                    bubble1.setText(newEntry);

                driveEmission = 0;
            }

        });
    }



    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void calculateDriveprint() {
        Log.d("mytag2", "testing");
        final EditText milesText = (EditText) findViewById(R.id.answer1);
        int miles = Integer.parseInt(milesText.getText().toString());

        final EditText mileage = (EditText) findViewById(R.id.answer2);
        int mpg = Integer.parseInt(mileage.getText().toString());

        final EditText peopleText = (EditText) findViewById(R.id.answer3);
        int people = Integer.parseInt(peopleText.getText().toString());
        //don't divide by zero
        if (people <= 0) {
            people = 1;
        }

        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        int selection = spinner.getSelectedItemPosition();



        switch(selection) {
            // case statements
            // values must be of same type of expression
            case 0:
                //car
                driveEmission = miles * (mpg) * 19.4 / people;
                return;
            case 1:
                //bus
                driveEmission = miles * (mpg) * 19.4 / people;
                return;
            case 2:
                //plane
                driveEmission = miles * 1.118;
                return;

        }





    }


}
