package com.srd.wemate;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.srd.wemate.Board.Board;
import com.srd.wemate.Board.Board_write;
import com.srd.wemate.expense.Expense_Item;
import com.srd.wemate.expense.Expenses_write;

public class MainActivity extends AppCompatActivity {
    public static String user_id;
    Button imageButton;

    // naver login
    ImageButton naverloginbtn,kakaologinbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        naverloginbtn = (ImageButton) findViewById(R.id.btnNaverlogin);
        naverloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NaverLoginActivity.class);
                startActivity(intent);
            }
        });

        kakaologinbtn = (ImageButton) findViewById(R.id.btnKakaologin);
        kakaologinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),KakaoLoginActivity.class);
                startActivity(intent);
            }
        });



    }
}
