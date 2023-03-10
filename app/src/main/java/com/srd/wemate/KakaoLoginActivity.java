package com.srd.wemate;

import static com.srd.wemate.MainActivity.user_id;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.common.util.Utility;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class KakaoLoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naver_login);

        Log.i("hash", "debug keyhash is " + Utility.INSTANCE.getKeyHash(this));
        Button okbtn = findViewById(R.id.okButton); //  onCreate 가 호출 되어야  .xml 파일의 레이아웃이  객체화됨
        KakaoSdk.init(this, "52cb6418052b6a6fd224235c70fc7a86");

        okbtn = (Button)findViewById(R.id.okButton);
        okbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserTest1.class);
                startActivity(intent);
            }
        });

        //예외처리 부분 --> 나중에 필요하면 쓰기
        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) { //invoke가 콜백으로 호출됨, 토큰이 전달되면 로그인 성공, 토큰이 null이면 로그인 실패
                if (oAuthToken != null){
                }

                if (throwable != null){
                }

                return null;
            }
        };

        if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(KakaoLoginActivity.this)){
            UserApiClient.getInstance().loginWithKakaoTalk(KakaoLoginActivity.this,callback);

        }else{ //카카오톡 설치되어 있지 않을 때 --> 웹으로 로그인 실행
            UserApiClient.getInstance().loginWithKakaoAccount(KakaoLoginActivity.this,callback);
            System.out.println("웹으로 로그인");
        }
        updateKakaoLoginUI();
    }

    private void updateKakaoLoginUI(){
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                user_id=user.getId().toString();
                if(user != null){
                    Log.i("login", "invoke:id="+user_id);
                    Log.i("login", "invoke:nickname="+user.getKakaoAccount().getProfile().getNickname());
                }
                else{
                    Log.i("login", "invoke=실패="+throwable.getMessage());

                }
//                setContentView(R.layout.activity_naver_login);
                return null;
            }
        });
    }

}