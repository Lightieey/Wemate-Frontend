package com.srd.wemate.Board;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.srd.wemate.R;

public class Board_Item extends AppCompatActivity {

    private Button btn_check;
    int check_click = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_item);

        //체크버튼 클릭 설정
        btn_check = (Button)findViewById(R.id.btn_check);
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check_click == 0)
                {
                    btn_check.setSelected(true);
                    check_click = 1;
                }
                else
                {
                    btn_check.setSelected(false);
                    check_click = 0;
                }
            }
        });
    }
}