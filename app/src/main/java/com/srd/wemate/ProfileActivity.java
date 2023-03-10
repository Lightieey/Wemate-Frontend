package com.srd.wemate;

import static com.srd.wemate.MainActivity.user_id;
import static com.srd.wemate.SearchProfile.selectedID;
import static com.srd.wemate.UserProfileActivity.my_user_name;
import static com.srd.wemate.UserTest1.life_style;
import static com.srd.wemate.UserTest2.rest_style;
import static com.srd.wemate.UserTest3.relation;
import static com.srd.wemate.UserTest4.clean;
import static com.srd.wemate.UserTest5.share;
import static com.srd.wemate.UserTest6.guest;
import static com.srd.wemate.UserTest7.shower;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    Button Button;
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView UserId = (TextView) findViewById(R.id.id);
        TextView tag1 = (TextView) findViewById(R.id.tag1);
        TextView tag2 = (TextView) findViewById(R.id.tag2);
        TextView tag3 = (TextView) findViewById(R.id.tag3);
        TextView tag4 = (TextView) findViewById(R.id.tag4);
        TextView tag5 = (TextView) findViewById(R.id.tag5);
        TextView tag6 = (TextView) findViewById(R.id.tag6);
        TextView tag7 = (TextView) findViewById(R.id.tag7);


        UserId.setText(my_user_name);
        if(life_style=="day")
        {tag1.setText( "#얼리버드🐓");}
        else{tag1.setText( "#올빼미🦉");}

        if(rest_style=="home")
        {tag2.setText( "#집순이🏠");}
        else{tag2.setText( "#밖순이🏙");}

        if(relation=="solo")
        {tag3.setText( "#개인플레이🙋");}
        else{tag3.setText( "#패밀리👨‍👩‍👧‍👦");}

        if(clean=="now")
        {tag4.setText( "#깔끔이🧹");}
        else{tag4.setText( "#나중에 할게🧦");}

        if(share=="Y")
        {tag5.setText("#물건 공유🙌");}
        else{tag5.setText("#물건 각자✋");}

        if(guest=="Y")
        {tag6.setText( "#손님 OK👫");}
        else{tag6.setText("#손님 NO🧍");}

        if(shower=="morning")
        {tag7.setText("#아침 샤워☀");}
        else{tag7.setText( "#저녁 샤워🌙");}




        imageButton = (ImageButton) findViewById(R.id.imageButton5); //프로필 수정하기
        imageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                startActivity(intent);
            }
        });

        imageButton = (ImageButton) findViewById(R.id.imageButton6); //성향 분석하기
        imageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), UserTest1.class);
                startActivity(intent);
            }
        });

        //하단 바--------------------------------------------------------
        Button = (Button) findViewById(R.id.menu_home);
        Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), RulesBulletin.class);
                startActivity(intent);
            }
        });

        Button = (Button) findViewById(R.id.menu_search);
        Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),SearchPage.class);
                startActivity(intent);
            }
        });

        //화면 전환 효과 없애기--------------------------------------------
        getWindow().setWindowAnimations(0);

    }
}