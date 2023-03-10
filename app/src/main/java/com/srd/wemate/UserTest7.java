package com.srd.wemate;

import static com.srd.wemate.MainActivity.user_id;
import static com.srd.wemate.UserTest1.life_style;
import static com.srd.wemate.UserTest2.rest_style;
import static com.srd.wemate.UserTest3.relation;
import static com.srd.wemate.UserTest4.clean;
import static com.srd.wemate.UserTest5.share;
import static com.srd.wemate.UserTest6.guest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.srd.wemate.model.Characteristic;
import com.srd.wemate.retrofit.CharacteristicApi;
import com.srd.wemate.retrofit.ProfileApi;
import com.srd.wemate.retrofit.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserTest7 extends AppCompatActivity {
    ImageButton imageButton;
    Button button;
    public static String shower;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_test7);
        RadioButton inputEdit1 = findViewById(R.id.radio_morning);
        RadioButton inputEdit2 = findViewById(R.id.radio_night);

        button = (Button) findViewById(R.id.btn_next);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                if (inputEdit1.isChecked()) {
                    shower = "morning";
                } else {
                    shower = "night";
                }
                RetrofitService retrofitService = new RetrofitService();
                CharacteristicApi characteristicApi = retrofitService.getRetrofit().create(CharacteristicApi.class);
                Characteristic characteristic = new Characteristic();
                characteristic.setId(user_id);
                characteristic.setRelation(relation);
                characteristic.setRest_style(rest_style);
                characteristic.setLife_style(life_style);
                characteristic.setShare(share);
                characteristic.setShower(shower);
                characteristic.setClean(clean);
                characteristic.setGuest(guest);

                characteristicApi.save(characteristic).enqueue(new Callback<Characteristic>() {
                    @Override
                    public void onResponse(Call<Characteristic> call, Response<Characteristic> response) {
                        Log.i("save","Response성공:"+response.body().toString());

                    }

                    @Override
                    public void onFailure(Call<Characteristic> call, Throwable t) {
                        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"에러",t);
                    }
                });



                Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                startActivity(intent);
            }
        });

        imageButton = (ImageButton) findViewById(R.id.btn_back);
        imageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), UserTest6.class);
                startActivity(intent);
            }
        });

        //화면 전환 효과 없애기--------------------------------------------
        getWindow().setWindowAnimations(0);
    }
}