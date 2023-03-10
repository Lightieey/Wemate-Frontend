package com.srd.wemate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.srd.wemate.Chat.ChatRoom;
import com.srd.wemate.model.Characteristic;
import com.srd.wemate.model.Profile;
import com.srd.wemate.retrofit.CharacteristicApi;
import com.srd.wemate.retrofit.ProfileApi;
import com.srd.wemate.retrofit.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchProfile extends AppCompatActivity {


    public static Profile profile;
    public static String chat_name;
    public static String selectedID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_profile);

        TextView tvUserId = (TextView) findViewById(R.id.id);
        TextView tvTitle = (TextView) findViewById(R.id.title);
        TextView tvTag1 = (TextView) findViewById(R.id.tag1);
        TextView tvTag2 = (TextView) findViewById(R.id.tag2);
        TextView tvTag3 = (TextView) findViewById(R.id.tag3);
        TextView tvTag4 = (TextView) findViewById(R.id.tag4);
        TextView tvTag5 = (TextView) findViewById(R.id.tag5);
        TextView tvTag6 = (TextView) findViewById(R.id.tag6);
        TextView tvTag7 = (TextView) findViewById(R.id.tag7);

        TextView tvGender = (TextView) findViewById(R.id.gender);
        TextView tvSmoke = (TextView) findViewById(R.id.smoke);
        TextView tvAge = (TextView) findViewById(R.id.age);
        TextView tvPet = (TextView) findViewById(R.id.pet);
        TextView tvRegion = (TextView) findViewById(R.id.region);
        TextView tvPeriod = (TextView) findViewById(R.id.period);
        TextView tvRoom = (TextView) findViewById(R.id.room);
        TextView tvPay = (TextView) findViewById(R.id.pay);
        TextView tvContent = (TextView) findViewById(R.id.content);

        Intent intent = getIntent();
        selectedID = intent.getStringExtra("select");
        System.out.println(selectedID);

        // db 연결 - 프로필 받아오기
        RetrofitService retrofitService = new RetrofitService();
        ProfileApi profileApi = retrofitService.getRetrofit().create(ProfileApi.class);


        profileApi.findById(selectedID).enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                Log.i("save","Response성공:"+ response.body());
                profile = response.body();

                // 테스트
                RetrofitService retrofitService = new RetrofitService();
                CharacteristicApi characteristicApi = retrofitService.getRetrofit().create(CharacteristicApi.class);


                characteristicApi.findById(selectedID).enqueue(new Callback<Characteristic>() {
                    @Override
                    public void onResponse(Call<Characteristic> call, Response<Characteristic> response) {

                        Log.i("save","Response성공:"+ response.body());

                        Characteristic characteristic = response.body();

                        chat_name = profile.getName();

                        tvUserId.setText(profile.getName());
                        tvTitle.setText(profile.getTitle());

                        if(characteristic.getLife_style()=="day")
                        {tvTag1.setText( "#얼리버드🐓");}
                        else{tvTag1.setText("#올빼미🦉");}

                        if(characteristic.getRelation()=="solo")
                        {tvTag2.setText( "#개인플레이🙋");}
                        else{tvTag2.setText(  "#패밀리👨‍👩‍👧‍👦");}

                        if(characteristic.getRest_style()=="home")
                        {tvTag3.setText("#집순이🏠");}
                        else{tvTag3.setText( "#밖순이🏙");}

                        if(characteristic.getShare()=="Y")
                        {tvTag4.setText( "#물건 공유🙌");}
                        else{tvTag4.setText( "#물건 각자✋");}

                        if(characteristic.getGuest()=="Y")
                        {tvTag5.setText( "#손님 OK👫");}
                        else{tvTag5.setText( "#손님 NO🧍");}

                        if(characteristic.getClean()=="now")
                        {tvTag6.setText( "#깔끔이🧹");}
                        else{tvTag6.setText("#나중에 할게🧦");}

                        if(characteristic.getShower()=="morning")
                        {tvTag7.setText( "#아침 샤워☀");}
                        else{tvTag7.setText( "#저녁 샤워🌙");}



//                        tvTag1.setText(characteristic.getLife_style());
//                        tvTag2.setText(characteristic.getRelation());
//                        tvTag3.setText(characteristic.getRest_style());
//                        tvTag4.setText(characteristic.getShare());
//                        tvTag5.setText(characteristic.getShower());
//                        tvTag6.setText(characteristic.getClean());
//                        tvTag7.setText(characteristic.getGuest());
                        tvGender.setText(profile.getGender());

                        if (profile.isSmoke()) tvSmoke.setText("흡연");
                        else tvSmoke.setText("흡연 안함");

                        tvAge.setText(profile.getAge());
                        tvPet.setText(profile.getPet());
                        tvRegion.setText(profile.getAddr1() + profile.getAddr2());
                        tvPeriod.setText(profile.getPeriod());
                        tvRoom.setText(profile.getRoom_type());
                        tvPay.setText(profile.getRent_fee());
                        System.out.println(" == " + profile.getContent());
                        tvContent.setText(profile.getContent());

                    }

                    @Override
                    public void onFailure(Call<Characteristic> call, Throwable t) {
                        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"에러",t);
                    }
                });

            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"에러",t);
            }
        });

        ImageButton btn1 = (ImageButton) findViewById(R.id.chat_send);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchProfile.this, ChatRoom.class);
                startActivity(intent);
                finish();
            }
        });

    }
}