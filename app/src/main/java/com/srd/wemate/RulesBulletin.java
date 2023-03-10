package com.srd.wemate;

//import static com.srd.wemate.Popup_match.ruleID;

import static com.srd.wemate.Popup_match.GroupID;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.srd.wemate.Board.Board;
import com.srd.wemate.model.dto.profile.ProfileListResponseDto;
import com.srd.wemate.model.dto.rule.RuleResponseDto;
import com.srd.wemate.expense.Expense;
import com.srd.wemate.model.dto.rule.RuleUpdateRequestDto;
import com.srd.wemate.retrofit.ProfileApi;
import com.srd.wemate.retrofit.RetrofitService;
import com.srd.wemate.retrofit.RuleApi;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RulesBulletin extends AppCompatActivity {

    public static int ruleID = 0;  // 초기화
    //슬라이드 열기/닫기 플래그
    boolean isPageOpen = false;
    //슬라이드 열기 애니메이션
    Animation translateLeftAnim;
    //슬라이드 닫기 애니메이션
    Animation translateRightAnim;
    //슬라이드 레이아웃
    LinearLayout slidingPage01;
    ImageButton btn_edit;
    ImageButton btn_edit2;
 
    Button Button;
    FloatingActionButton fButton;

    boolean check = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules_bulletin);

        //저장 버튼
//        fButton = findViewById(R.id.btn7);
//        fButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(RulesBulletin.this, "저장되었습니다", Toast.LENGTH_SHORT).show();
//            }
//        });



        TextView content = (TextView) findViewById(R.id.rules);

        RetrofitService retrofitService = new RetrofitService();
        RuleApi ruleApi = retrofitService.getRetrofit().create(RuleApi.class);
        
        ruleApi.findById(ruleID).enqueue(new Callback<RuleResponseDto>() {
            @Override
            public void onResponse(Call<RuleResponseDto> call, Response<RuleResponseDto> response) {
                //assert response.body() != null;
                Log.i("save","Response성공:"+ response.body());
                System.out.println("RulesBulletin RuleID=" + ruleID);

                if (response.body() == null) {
                    content.setText("메이트 매칭 후 이용할 수 있습니다.");
                }
                else {
                    content.setText(response.body().getContent());
                    check = true;
                }
            }

            @Override
            public void onFailure(Call<RuleResponseDto> call, Throwable t) {
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"에러",t);
            }
        });


        FloatingActionButton savebtn = (FloatingActionButton) findViewById(R.id.btn7);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // content랑 id 받아오기
                // id는 메이트 그룹 만들 때 자동 생성 -> 전역변수로 가지고 있기

                if (check) {
                    Toast.makeText(RulesBulletin.this, "저장되었습니다", Toast.LENGTH_SHORT).show();

                    RetrofitService retrofitService = new RetrofitService();
                    RuleApi ruleApi = retrofitService.getRetrofit().create(RuleApi.class);

                    RuleUpdateRequestDto requestDto = new RuleUpdateRequestDto(content.getText().toString());

                    System.out.println(requestDto.getContent());
                    ruleApi.update(ruleID, requestDto).enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                            //assert response.body() != null;
                            Log.i("save","Response성공:"+ response.body());
//                            if (response.body() == null)

                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"에러",t);
                        }
                    });
                }


            }
        });

        //슬라이드----------------------------------------------------------
        ////UI
        slidingPage01 = (LinearLayout) findViewById(R.id.slidingPage01);
        btn_edit = (ImageButton) findViewById(R.id.btn_edit);
        btn_edit2 = (ImageButton) findViewById(R.id.btn_edit2);



        ////애니메이션
        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);
        ////애니메이션 리스너 설정
        RulesBulletin.SlidingPageAnimationListener animationListener = new RulesBulletin.SlidingPageAnimationListener();
        translateLeftAnim.setAnimationListener(animationListener);
        translateRightAnim.setAnimationListener(animationListener);

        //하우스 메뉴바--------------------------------------------------------
        Button = (Button) findViewById(R.id.btn_board); //게시판
        Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Board.class);
                startActivity(intent);
            }
        });

        Button = (Button) findViewById(R.id.btn_rules); //규칙
        Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RulesBulletin.class);
                startActivity(intent);
            }
        });

        Button = (Button) findViewById(R.id.btn_expense); //생활비
        Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Expense.class);
                startActivity(intent);
            }
        });

        Button = (Button) findViewById(R.id.btn_schedule); //일정
        Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Schedule.class);
                startActivity(intent);
            }
        });

        //하단 바--------------------------------------------------------
        Button = (Button) findViewById(R.id.menu_search);
        Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchPage.class);
                startActivity(intent);
            }
        });
        Button = (Button) findViewById(R.id.menu_pf);
        Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });



        //화면 전환 효과 없애기--------------------------------------------
        getWindow().setWindowAnimations(0);
    }

    //슬라이드---------------------------------------------------------------------------
    ////버튼
    public void onButton1Clicked(View v) {
        //닫기
        if (isPageOpen) {
            //애니메이션 시작
            slidingPage01.startAnimation(translateRightAnim);
        }
        //열기
        else {
            slidingPage01.setVisibility(View.VISIBLE);
            slidingPage01.startAnimation(translateLeftAnim);
        }
    }

    ////애니메이션 리스너
    private class SlidingPageAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationEnd(Animation animation) {
            //슬라이드 열기->닫기
            if (isPageOpen) {
                slidingPage01.setVisibility(View.INVISIBLE);
                isPageOpen = false;
            }
            //슬라이드 닫기->열기
            else {
                btn_edit2.setImageResource(R.drawable.delete);
                isPageOpen = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

        @Override
        public void onAnimationStart(Animation animation) {

        }
    }


}