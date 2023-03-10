package com.srd.wemate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

public class UserTest4 extends AppCompatActivity {
    ImageButton imageButton;
    public static String clean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_test4);
        RadioButton inputEdit1 = findViewById(R.id.radio_now);
        RadioButton inputEdit2 = findViewById(R.id.radio_later);

        imageButton = (ImageButton) findViewById(R.id.btn_next);
        imageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                if (inputEdit1.isChecked()) {
                    clean = "now";
                } else {
                    clean = "later";
                }
                Intent intent = new Intent(getApplicationContext(), UserTest5.class);
                startActivity(intent);
            }
        });

        imageButton = (ImageButton) findViewById(R.id.btn_back);
        imageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), UserTest3.class);
                startActivity(intent);
            }
        });

        //화면 전환 효과 없애기--------------------------------------------
        getWindow().setWindowAnimations(0);
    }
}