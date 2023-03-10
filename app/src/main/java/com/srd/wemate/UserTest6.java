package com.srd.wemate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

public class UserTest6 extends AppCompatActivity {
    ImageButton imageButton;
    public static String guest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_test6);
        RadioButton inputEdit1 = findViewById(R.id.radio_guest_Y);
        RadioButton inputEdit2 = findViewById(R.id.radio_guest_N);

        imageButton = (ImageButton) findViewById(R.id.btn_next);
        imageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                if (inputEdit1.isChecked()) {
                    guest = "Y";
                } else {
                    guest = "N";
                }
                Intent intent = new Intent(getApplicationContext(), UserTest7.class);
                startActivity(intent);
            }
        });

        imageButton = (ImageButton) findViewById(R.id.btn_back);
        imageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), UserTest5.class);
                startActivity(intent);
            }
        });

        //화면 전환 효과 없애기--------------------------------------------
        getWindow().setWindowAnimations(0);
    }
}