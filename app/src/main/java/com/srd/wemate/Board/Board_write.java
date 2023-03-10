package com.srd.wemate.Board;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.srd.wemate.MainActivity;
import com.srd.wemate.ProfileActivity;
import com.srd.wemate.R;
import com.srd.wemate.model.dto.posts.PostsSaveRequestDto;
import com.srd.wemate.model.dto.posts.PostsUpdateRequestDto;
import com.srd.wemate.retrofit.PostsApi;
import com.srd.wemate.retrofit.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.srd.wemate.MainActivity.user_id;
import static com.srd.wemate.Popup_match.GroupID;
import static com.srd.wemate.SearchProfile.profile;
import static com.srd.wemate.UserProfileActivity.my_user_name;

public class Board_write extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write);

        TextView textView = (TextView) findViewById(R.id.content);
        Button okbtn = (Button) findViewById(R.id.button6);

        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RetrofitService retrofitService = new RetrofitService();
                PostsApi postsApi = retrofitService.getRetrofit().create(PostsApi.class);

                PostsSaveRequestDto requestDto = new PostsSaveRequestDto(textView.getText().toString(), my_user_name, false, GroupID);

                postsApi.save(requestDto).enqueue(new Callback<Long>() {
                    @Override
                    public void onResponse(Call<Long> call, Response<Long> response) {
                        //assert response.body() != null;
                        Log.i("save","Response성공:"+ response.body());

                    }

                    @Override
                    public void onFailure(Call<Long> call, Throwable t) {
                        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"에러",t);
                    }
                });

                Intent intent = new Intent(getApplicationContext(), Board.class);
                startActivity(intent);
            }
        });

//        upbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                RetrofitService retrofitService = new RetrofitService();
//                PostsApi postsApi = retrofitService.getRetrofit().create(PostsApi.class);
//
//                PostsUpdateRequestDto requestDto = new PostsUpdateRequestDto(textView.getText().toString(), false);
//
//                postsApi.update(postId, requestDto).enqueue(new Callback<Long>() {
//                    @Override
//                    public void onResponse(Call<Long> call, Response<Long> response) {
//                        //assert response.body() != null;
//                        Log.i("save","Response성공:"+ response.body());
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<Long> call, Throwable t) {
//                        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"에러",t);
//                    }
//                });
//            }
//        });
    }
}