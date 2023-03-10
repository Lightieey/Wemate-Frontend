package com.srd.wemate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

public class UserTest2 extends AppCompatActivity {
    ImageButton imageButton;
    public static String rest_style;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_test2);
        RadioButton inputEdit1 = findViewById(R.id.radio_home);
        RadioButton inputEdit2 = findViewById(R.id.radio_out);

        imageButton = (ImageButton) findViewById(R.id.btn_next);
        imageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                if (inputEdit1.isChecked()) {
                    rest_style = "home";
                } else {
                    rest_style = "out";
                }
                Intent intent = new Intent(getApplicationContext(), UserTest3.class);
                startActivity(intent);
            }
        });

        imageButton = (ImageButton) findViewById(R.id.btn_back);
        imageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), UserTest1.class);
                startActivity(intent);
            }
        });

        //화면 전환 효과 없애기--------------------------------------------
        getWindow().setWindowAnimations(0);
    }
}