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
        {tag1.setText( "#μΌλ¦¬λ²λπ");}
        else{tag1.setText( "#μ¬λΉΌλ―Έπ¦");}

        if(rest_style=="home")
        {tag2.setText( "#μ§μμ΄π ");}
        else{tag2.setText( "#λ°μμ΄π");}

        if(relation=="solo")
        {tag3.setText( "#κ°μΈνλ μ΄π");}
        else{tag3.setText( "#ν¨λ°λ¦¬π¨βπ©βπ§βπ¦");}

        if(clean=="now")
        {tag4.setText( "#κΉλμ΄π§Ή");}
        else{tag4.setText( "#λμ€μ ν κ²π§¦");}

        if(share=="Y")
        {tag5.setText("#λ¬Όκ±΄ κ³΅μ π");}
        else{tag5.setText("#λ¬Όκ±΄ κ°μβ");}

        if(guest=="Y")
        {tag6.setText( "#μλ OKπ«");}
        else{tag6.setText("#μλ NOπ§");}

        if(shower=="morning")
        {tag7.setText("#μμΉ¨ μ€μβ");}
        else{tag7.setText( "#μ λ μ€μπ");}




        imageButton = (ImageButton) findViewById(R.id.imageButton5); //νλ‘ν μμ νκΈ°
        imageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                startActivity(intent);
            }
        });

        imageButton = (ImageButton) findViewById(R.id.imageButton6); //μ±ν₯ λΆμνκΈ°
        imageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), UserTest1.class);
                startActivity(intent);
            }
        });

        //νλ¨ λ°--------------------------------------------------------
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

        //νλ©΄ μ ν ν¨κ³Ό μμ κΈ°--------------------------------------------
        getWindow().setWindowAnimations(0);

    }
}