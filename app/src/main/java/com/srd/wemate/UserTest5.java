package com.srd.wemate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

public class UserTest5 extends AppCompatActivity {
    ImageButton imageButton;
    public static String share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_test5);
        RadioButton inputEdit1 = findViewById(R.id.radio_share_Y);
        RadioButton inputEdit2 = findViewById(R.id.radio_share_N);

        imageButton = (ImageButton) findViewById(R.id.btn_next);
        imageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                if (inputEdit1.isChecked()) {
                    share = "Y";
                } else {
                    share = "N";
                }
                Intent intent = new Intent(getApplicationContext(), UserTest6.class);
                startActivity(intent);
            }
        });

        imageButton = (ImageButton) findViewById(R.id.btn_back);
        imageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), UserTest4.class);
                startActivity(intent);
            }
        });

        //화면 전환 효과 없애기--------------------------------------------
        getWindow().setWindowAnimations(0);
    }
}