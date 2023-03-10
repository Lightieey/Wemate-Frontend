package com.srd.wemate;

import static com.srd.wemate.MainActivity.user_id;
import static com.srd.wemate.SearchProfile.chat_name;
import static com.srd.wemate.SearchProfile.profile;
import static com.srd.wemate.SearchProfile.selectedID;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.srd.wemate.model.dto.mategroup.MateGroupAddRequestDto;
import com.srd.wemate.model.dto.mategroup.MateGroupSaveRequestDto;
import com.srd.wemate.model.dto.rule.RuleSaveRequestDto;
import com.srd.wemate.retrofit.MateGroupApi;
import com.srd.wemate.retrofit.RetrofitService;
import com.srd.wemate.retrofit.RuleApi;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.srd.wemate.RulesBulletin.ruleID;
public class Popup_match extends AppCompatActivity {
    Button Btn_yes, Btn_no;
//    public static int ruleID = 0;  // 초기화
    public static int GroupID = 0;  // 초기화

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 상태바 제거 (전체화면 모드)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_popup_match);

        Btn_yes = (Button) findViewById(R.id.Btn_yes);
        Btn_no = (Button) findViewById(R.id.Btn_no);

    }

    //동작 버튼 클릭
    public void mOk(View v) {

        RetrofitService retrofitService = new RetrofitService();
        MateGroupApi mateGroupApi = retrofitService.getRetrofit().create(MateGroupApi.class);

        // 메이트 2명 넘어갈때 어떻게 구현할지?
        // 내가 메이트있는 상태 + 상대방 추가
        // 상대방이 메이트있는 상태 + 내가 거기에 추가



        // generate group
        MateGroupSaveRequestDto requestDto = new MateGroupSaveRequestDto();

        mateGroupApi.save(requestDto).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                //assert response.body() != null;
                Log.i("save","Response성공:"+ response.body());   // gid

                GroupID = response.body();

                // 채팅에서 내 아이디, 상대방 아이디 가져오기 ***********
                String uesrId1 = profile.getId(); //내아이디
                String userId2 = selectedID; //상대방 아이디

                // insert group users
                MateGroupAddRequestDto addrequestDto = new MateGroupAddRequestDto(user_id);

                mateGroupApi.addMate(GroupID, addrequestDto).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        //assert response.body() != null;
                        Log.i("save","Response성공:"+ response.body());

                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"에러",t);
                    }
                });

                MateGroupAddRequestDto addrequestDto2 = new MateGroupAddRequestDto(userId2);

                mateGroupApi.addMate(GroupID, addrequestDto2).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        //assert response.body() != null;
                        Log.i("save","Response성공:"+ response.body());

                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"에러",t);
                    }
                });

                // 규칙 게시판 생성
                RuleApi ruleApi = retrofitService.getRetrofit().create(RuleApi.class);

                String DefaultRule = "규칙을 입력해보세요";

                RuleSaveRequestDto requestSaveDto = new RuleSaveRequestDto(DefaultRule, GroupID);

                ruleApi.save(requestSaveDto).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        //assert response.body() != null;
                        Log.i("save","Response성공:"+ response.body());
                        ruleID = response.body();
                        System.out.println("Popup_match:ruleID: " + ruleID);
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"에러",t);
                    }
                });
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"에러",t);
            }
        });





        finish();
    }


    //취소 버튼 클릭
    public void mCancle(View v) {
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}