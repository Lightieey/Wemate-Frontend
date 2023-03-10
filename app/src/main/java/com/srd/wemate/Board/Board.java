package com.srd.wemate.Board;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.srd.wemate.GroupAdapter;
import com.srd.wemate.GroupData;
import com.srd.wemate.MainActivity;
import com.srd.wemate.ProfileActivity;
import com.srd.wemate.expense.Expenses_write;
import com.srd.wemate.model.Profile;
import com.srd.wemate.R;
import com.srd.wemate.RulesBulletin;
import com.srd.wemate.Schedule;
import com.srd.wemate.SearchPage;
import com.srd.wemate.expense.Expense;
import com.srd.wemate.model.dto.mategroup.MateGroupResponseDto;
import com.srd.wemate.model.dto.posts.PostsListResponseDto;
import com.srd.wemate.model.dto.profile.ProfileListResponseDto;
import com.srd.wemate.retrofit.PostsApi;
import com.srd.wemate.retrofit.ProfileApi;
import com.srd.wemate.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.srd.wemate.Popup_match.GroupID;

public class Board extends AppCompatActivity {
    private ArrayList<PostsListResponseDto> data;
   private ArrayList<ProfileListResponseDto> data2; //슬라이드 RecyclerView
    private BoardAdapter adapter;
    private GroupAdapter adapter2;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private LinearLayoutManager linearLayoutManager;
    private LinearLayoutManager linearLayoutManager2;
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
    FloatingActionButton FloatingActionButton;

    static ArrayList<Long> postsIdIndex;
    //static ArrayList<Long> postsIdIndex2;

    List<PostsListResponseDto> datalist;
    List<MateGroupResponseDto> datalist2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        //RecyclerView 연결--------------------------------------------------------
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        data = new ArrayList<>();
        postsIdIndex = new ArrayList<>();

        adapter = new BoardAdapter(data, getApplicationContext());
        recyclerView.setAdapter(adapter);

        //RecyclerView 연결----------슬라이드창 뷰----------------------------------------------
        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerView2);
        linearLayoutManager2 = new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(linearLayoutManager2);

        data2 = new ArrayList<>();

        adapter2 = new GroupAdapter(data2,getApplicationContext());
        recyclerView2.setAdapter(adapter2);

        // db 연결
        RetrofitService retrofitService = new RetrofitService();
        ProfileApi profileApi = retrofitService.getRetrofit().create(ProfileApi.class);


        profileApi.findByGid(GroupID).enqueue(new Callback<List<ProfileListResponseDto>>() {
            @Override
            public void onResponse(Call<List<ProfileListResponseDto>> call, Response<List<ProfileListResponseDto>> response) {
                ArrayList<ProfileListResponseDto> temp = (ArrayList<ProfileListResponseDto>) response.body();
                data2 = temp;
                Log.i("save","Response성공:"+ response.body());
                System.out.println(data2);

                //// adapter의 값이 변경되었다는 것을 알려줍니다
                adapter2.setList(data2);
                adapter2 = new GroupAdapter(data2, getApplicationContext());
                recyclerView2.setAdapter(adapter2);
            }

            @Override
            public void onFailure(Call<List<ProfileListResponseDto>> call, Throwable t) {
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"에러",t);
            }
        });



        if (GroupID == 0) {

        } else {
            // db 연결
//            RetrofitService retrofitService = new RetrofitService();
            PostsApi postsApi = retrofitService.getRetrofit().create(PostsApi.class);

            postsApi.findAllDesc().enqueue(new Callback<List<PostsListResponseDto>>() {
                @Override
                public void onResponse(Call<List<PostsListResponseDto>> call, Response<List<PostsListResponseDto>> response) {

                    if (response.body() != null) {
                        datalist = response.body();
                        Log.i("save","Response성공:"+ response.body());

                        // user gid == mategroup db gid
                        for (int i = 0; i < datalist.size(); i++) {
                            if (datalist.get(i).getGid() == GroupID) {
                                data.add(datalist.get(i));
                            }
                        }

                        for (int i = 0; i < data.size(); i++) {
                            //data.add(new BoardData(datalist.get(i).getId(), datalist.get(i).getContent()));
                            //if (data.size() == 1) break;
                            postsIdIndex.add(datalist.get(i).getId());
                            //postsIdIndex.add(datalist.get(i).getId());
                        }
                        //// adapter의 값이 변경되었다는 것을 알려줍니다
                        adapter.setList(data);
                    }
                    else {
                        System.out.println(">>>No Posts");
                    }

                }

                @Override
                public void onFailure(Call<List<PostsListResponseDto>> call, Throwable t) {
                    Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"에러",t);
                }
            });
        }


        //글쓰기 버튼
        FloatingActionButton = (FloatingActionButton) findViewById(R.id.board_write);
        FloatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Board_write.class);
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
        Board.SlidingPageAnimationListener animationListener = new Board.SlidingPageAnimationListener();
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
        Button = (Button) findViewById(R.id.btn_board);
        Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Board.class);
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
