package com.srd.wemate.expense;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.srd.wemate.Board.Board_write;
import com.srd.wemate.GroupAdapter;
import com.srd.wemate.MainActivity;
import com.srd.wemate.ProfileActivity;
import com.srd.wemate.Board.Board;
import com.srd.wemate.ProfileActivity;
import com.srd.wemate.R;
import com.srd.wemate.RulesBulletin;
import com.srd.wemate.Schedule;
import com.srd.wemate.SearchPage;
import com.srd.wemate.model.dto.profile.ProfileListResponseDto;
import com.srd.wemate.retrofit.ExpenseApi;
import com.srd.wemate.retrofit.ProfileApi;
import com.srd.wemate.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Expense extends AppCompatActivity {
    private ArrayList<ExpenseData> data;
    private ExpenseAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

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
    com.google.android.material.floatingactionbutton.FloatingActionButton FloatingActionButton;


    android.widget.Button Button;
    List<ExpenseData> expenseData;
    TextView tv_moneysum;
    int moneysum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        //db에서 값 가져오기
        RetrofitService retrofitService = new RetrofitService();
        ExpenseApi expenseApi = retrofitService.getRetrofit().create(ExpenseApi.class);



        //RecyclerView 연결--------------------------------------------------------
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        tv_moneysum = (TextView) findViewById(R.id.money_sum);
        moneysum = 0;

        data = new ArrayList<>();

        adapter = new ExpenseAdapter(data, getApplicationContext());
        recyclerView.setAdapter(adapter);

        if (GroupID != 0) {

            expenseApi.getAllExpense().enqueue(new Callback<List<ExpenseData>>() {
                @Override
                public void onResponse(Call<List<ExpenseData>> call, Response<List<ExpenseData>> response) {
                    expenseData = response.body();
                    Log.i("save","Expense 받아오기 성공:"+response.body());

                    if (expenseData != null) {
                        // db 연결
                        RetrofitService retrofitService = new RetrofitService();
                        ProfileApi profileApi = retrofitService.getRetrofit().create(ProfileApi.class);
                        profileApi.findByGid(GroupID).enqueue(new Callback<List<ProfileListResponseDto>>() {
                            @Override
                            public void onResponse(Call<List<ProfileListResponseDto>> call, Response<List<ProfileListResponseDto>> response) {
                                ArrayList<ProfileListResponseDto> temp = (ArrayList<ProfileListResponseDto>) response.body();
                                Log.i("save","profile 받아오기 성공:"+response.body());
                                for (int i = 0; i < expenseData.size(); i++) {
                                    for (int j = 0; j < temp.size(); j++) {
                                        if (expenseData.get(i).getUid().equals(temp.get(j).getId())) {
                                            data.add(new ExpenseData(expenseData.get(i).getId(),expenseData.get(i).getMoney(),expenseData.get(i).getPurpose(),expenseData.get(i).getDate(),expenseData.get(i).getMemo(),expenseData.get(i).getUid()));
                                            System.out.println("i:" + i + "j:" + j);
                                            System.out.println("Expense 받아오기 성공:"+expenseData.get(i).getId()+expenseData.get(i).getMoney()+expenseData.get(i).getPurpose()+expenseData.get(i).getDate()+expenseData.get(i).getMemo());

                                            moneysum += expenseData.get(i).getMoney();

                                        }
                                    }
//
                                }

                                System.out.println("Expense_MoneySum:" + moneysum);
                                tv_moneysum.setText(String.valueOf(moneysum));
                                adapter.setList(data);
                            }

                            @Override
                            public void onFailure(Call<List<ProfileListResponseDto>> call, Throwable t) {
                                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"에러",t);
                            }
                        });
                    }
                    //hghghh


//                for (int i = 0; i < expenseData.size(); i++) {
//
//
//
//
//                }

                }

                @Override
                public void onFailure(Call<List<ExpenseData>> call, Throwable t) {
                    Log.i("save","Expense 받아오기 실패:"+t.getMessage());

                }
            });
        } else {
            Log.i("save","group matching nope");
        }

        //data.add(new ExpenseData("asd",333,"as","asa","asa"));
//        ////dummy
//        for (int i = 1; i <= 10; i++) {
//            if (i % 2 == 0)
//                data.add(new ExpenseData(i + " ID", 10000,"어제 장본거","2022-01-22","10000원씩 보내주세요"));
//            else
//                data.add(new ExpenseData(i + " ID", 29000,"생필품","2022-01-23","5000원씩 보내주세요"));
//
//        }


        //// adapter의 값이 변경되었다는 것을 알려줍니다


        //글쓰기 버튼
        FloatingActionButton = (FloatingActionButton) findViewById(R.id.exp_write);
        FloatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Expenses_write.class);
                startActivity(intent);
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
        Expense.SlidingPageAnimationListener animationListener = new Expense.SlidingPageAnimationListener();
        translateLeftAnim.setAnimationListener(animationListener);
        translateRightAnim.setAnimationListener(animationListener);

        //하우스 메뉴바--------------------------------------------------------
        Button = (Button) findViewById(R.id.btn_rules);
        Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RulesBulletin.class);
                startActivity(intent);
            }
        });

        Button = (Button) findViewById(R.id.btn_board);
        Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Board.class);
                startActivity(intent);
            }
        });

        Button = (Button) findViewById(R.id.btn_expense);
        Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Expense.class);
                startActivity(intent);
            }
        });

        Button = (Button) findViewById(R.id.btn_schedule);
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
