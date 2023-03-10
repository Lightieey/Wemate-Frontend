package com.srd.wemate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;

public class UserTest1 extends AppCompatActivity {

    ImageButton imageButton;
    public static String life_style;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_test1);
        RadioButton inputEdit1 = findViewById(R.id.radio_early_bird);
        RadioButton inputEdit2 = findViewById(R.id.radio_owl);

        imageButton = (ImageButton) findViewById(R.id.btn_next);
        imageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                if (inputEdit1.isChecked()) {
                    life_style = "day";
                } else {
                    life_style = "night";
                }





                Intent intent = new Intent(getApplicationContext(), UserTest2.class);
                startActivity(intent);
            }
        });

        imageButton = (ImageButton) findViewById(R.id.btn_back);
        imageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}