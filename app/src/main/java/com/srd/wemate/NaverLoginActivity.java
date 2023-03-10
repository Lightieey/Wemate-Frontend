package com.srd.wemate;

import static com.srd.wemate.MainActivity.user_id;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.srd.wemate.model.Profile;
import com.srd.wemate.retrofit.ProfileApi;
import com.srd.wemate.retrofit.RetrofitService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Response;

public class NaverLoginActivity extends AppCompatActivity {

    Button okbtn;
    OAuthLogin mOAuthLoginModule;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naver_login);

        okbtn = (Button)findViewById(R.id.okButton);
        okbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserTest1.class);
                startActivity(intent);
            }
        });

        mContext = getApplicationContext();
        mOAuthLoginModule = OAuthLogin.getInstance();
        mOAuthLoginModule.init(
                mContext
                ,getString(R.string.naver_client_id)
                ,getString(R.string.naver_client_secret)
                ,getString(R.string.naver_client_name)
        );

        @SuppressLint("HandlerLeak")
        OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
            @Override
            public void run(boolean success) {
                if (success) {
                    String accessToken = mOAuthLoginModule.getAccessToken(mContext);
                    String refreshToken = mOAuthLoginModule.getRefreshToken(mContext);
                    long expiresAt = mOAuthLoginModule.getExpiresAt(mContext);
                    String tokenType = mOAuthLoginModule.getTokenType(mContext);

                    Log.i("LoginData","accessToken : "+ accessToken);
                    Log.i("LoginData","refreshToken : "+ refreshToken);
                    Log.i("LoginData","expiresAt : "+ expiresAt);
                    Log.i("LoginData","tokenType : "+ tokenType);

                    //String token = "YOUR_ACCESS_TOKEN"; // ????????? ????????? ?????? ??????;
                    String header = "Bearer " + accessToken; // Bearer ????????? ?????? ??????
                    String apiURL = "https://openapi.naver.com/v1/nid/me";

                    Map<String, String> requestHeaders = new HashMap<>();
                    requestHeaders.put("Authorization", header);

                    new Thread(() -> {
                        String responseBody = get(apiURL,requestHeaders);
                        Log.d("responseBody ?????? : ", responseBody);
                        //NaverUserInfo(responseBody);
                        user_id = responseBody.split("\"")[13];

                        Profile profile = new Profile();
                        profile.setId(user_id);

                        RetrofitService retrofitService = new RetrofitService();
                        ProfileApi profileApi = retrofitService.getRetrofit().create(ProfileApi.class);
                        profileApi.save(profile)
                                .enqueue(new retrofit2.Callback<Profile>() {
                                    @Override
                                    public void onResponse(Call<Profile> call, Response<Profile> response) {
                                        Log.i("save","Response??????:"+response.body());
                                    }

                                    @Override
                                    public void onFailure(Call<Profile> call, Throwable t) {
                                        //Log.i("save","Response??????:"+t);
                                        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"??????",t);
                                    }
                                });
                    }).start();

                    Toast.makeText(mContext, "????????????????????????.", Toast.LENGTH_SHORT).show();

                } else {
                    String errorCode = mOAuthLoginModule
                            .getLastErrorCode(mContext).getCode();
                    String errorDesc = mOAuthLoginModule.getLastErrorDesc(mContext);
                    Toast.makeText(mContext, "errorCode:" + errorCode
                            + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
                }
            }
        };

        mOAuthLoginModule.startOauthLoginActivity(NaverLoginActivity.this, mOAuthLoginHandler);


    }

    private void NaverUserInfo(String msg){

        Gson gson = new Gson();

        // responsebody ?????? : {"resultcode":"00","message":"success","response":{"id":"gzFA1YAq~~"}}
        // ????????? ?????? class ????????? gson?????? ???????????? ???????????? ?????????????????????
        // ???????????? ????????? ?????? split?????? ????????? ????????? ???????????? ?????????

        //Profile profile = gson.fromJson(msg, Profile.class);
        //Log.d("check : ", profile.toString());

    }


    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // ?????? ??????
                return readBody(con.getInputStream());
            } else { // ?????? ??????
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API ????????? ?????? ??????", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL??? ?????????????????????. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("????????? ??????????????????. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API ????????? ????????? ??????????????????.", e);
        }
    }

    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;

        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://192.168.123.106:8080/app/android");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-from-urlencoded");
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);

                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0];
                osw.write(sendMsg);
                osw.flush();

                if (conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                } else {
                    Log.i("????????????", conn.getResponseCode()+"error");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return receiveMsg;
        }
    }
}