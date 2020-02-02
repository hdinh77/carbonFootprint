package com.example.cminus;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cminus.R;




public class food extends Activity {
    public double foodEmission = 0;

    DatabaseHelper mDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_window);
        mDatabaseHelper = new DatabaseHelper(this);
        configureFoodCancelButton();

        configureFoodSubmitButton();


        //get the spinner from the xml.
        Spinner dropdown1 = findViewById(R.id.spinner2);
        //create a list of items for the spinner.
        String[] items = new String[]{"Meat/Eggs", "Bakery Products", "Fruits/Vegetables", "dairy"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown1.setAdapter(adapter);

    }


    public void configureFoodCancelButton() {
        Button foodcancel = findViewById(R.id.buttoncancel1);
        foodcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }

        });
    }


    public void configureFoodSubmitButton() {
        Button foodsubmit = findViewById(R.id.buttonsubmit1);
        foodsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                calculateFoodprint();
                Log.d("HEYONIGGALOOKATME", foodEmission+"");
                String newEntry = foodEmission+"";
                mDatabaseHelper.addData(newEntry);

                TextView bubble2 = (TextView) findViewById(R.id.bubble2);
                if (bubble2 != null)
                {
                    bubble2.setText(foodEmission+"");
                }
                    foodEmission = 0;
            }

        });
    }



    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void calculateFoodprint() {
        Log.d("mytag2", "testing");
        final EditText lbsText = (EditText) findViewById(R.id.answerA);
        int dollars = Integer.parseInt(lbsText.getText().toString());
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        int selection = spinner.getSelectedItemPosition();

        switch(selection) {
            // case statements
            // values must be of same type of expression
            case 0:
                //meat and eggs
                foodEmission = dollars * (38.3);
                return;
            case 1:
                //bakery products
                foodEmission = dollars * (19.6);
                return;
            case 2:
                //fruits and vegetables
                foodEmission = dollars * (31.0);
                return;
            case 3:
                //dairy
                foodEmission = dollars * (50.5);
                return;
        }





    }
}
