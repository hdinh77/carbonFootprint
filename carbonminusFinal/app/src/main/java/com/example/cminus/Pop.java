package com.example.cminus;


import android.content.Intent;
import android.view.View;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageButton;

import static com.example.cminus.R.layout.popupwindow;

public class Pop extends Activity {
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(popupwindow);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((width), (height));

        configureCarButton();
        configureFoodButton();

    }



        private void configureCarButton() {
            ImageButton button = findViewById(R.id.imageButton);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    startActivity(new Intent(Pop.this, drive.class));

                }

            });
        }

    private void configureFoodButton() {
        ImageButton button1 = findViewById(R.id.imageButton1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Pop.this, food.class));

            }

        });
    }

}
