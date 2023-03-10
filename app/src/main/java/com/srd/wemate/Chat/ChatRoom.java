package com.srd.wemate.Chat;

import static com.srd.wemate.MainActivity.user_id;
import static com.srd.wemate.SearchProfile.chat_name;
import static com.srd.wemate.SearchProfile.profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.srd.wemate.Popup_match;
import com.srd.wemate.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ChatRoom extends AppCompatActivity {
    //private ArrayList<ChatData> dataList;
    ImageButton btn_send;
    Button id_send;
    EditText msg_text, id_input;
    String msg_str,id_str;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ChatData chatData;
    String id_string,msg_string; //카카오 or 네이버 로그인
    ArrayList<ChatData> dataList;
    String datetime;
    ChatAdapter chatAdapter;

    private static final String TAG = "ChatActivity";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        btn_send = (ImageButton) findViewById(R.id.btn_send);
        database = FirebaseDatabase.getInstance();
        dataList= new ArrayList<>();
        chatData = new ChatData();
        ValueEventListener valueEventListener;

        myRef = database.getReference("message");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("","리스너");
                for(DataSnapshot child: snapshot.getChildren()){
//                    id_string = child.child("i_id").getValue().toString();
//                    msg_string = child.child("content").getValue().toString();
//                    Log.i("","id출력:"+id_string+"메세지 출력"+msg_string);
                }
                if(user_id==id_string){ // LEFT_CONTENT = 0(상대방), RIGHT_CONTENT = 1 (나)
                    chatData.setViewType(1);
                }
                else if(user_id!=id_string){
                    chatData.setViewType(0);
                }
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                datetime = dateFormat.format(calendar.getTime());

                chatData.setI_id(profile.getId());
                chatData.setU_id(chat_name); //룸메 검색 게시판에서 채팅을 원하는 사람을 클릭하면 그 사람의 아이디값 가져와야 함
                chatData.setContent(msg_string);
                chatData.setU_img(123123l); //아마 long값? 어떻게 넣을지 확인
                chatData.setTime(datetime);
                dataList.add(chatData);
                System.out.println(chatData.getI_id()+"  "+chatData.getContent()+"   "+chatData.getTime());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg_text = (EditText) findViewById(R.id.editChat);
                msg_str = msg_text.getText().toString();


                ChatDataSend chatDataSend= new ChatDataSend(user_id,msg_str);
                chatDataSend.setContent(user_id);
                chatDataSend.setContent(msg_str);

                myRef.push().setValue(chatDataSend);
                //myRef.addValueEventListener(valueEventListener);

                msg_text.setText("");
            }
        });


        initData();

        RecyclerView recycler = findViewById(R.id.chat_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(new ChatAdapter(dataList));

        // 매칭하기 팝업창
        Button btn_popup = (Button) findViewById(R.id.btn_popup);
        btn_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatRoom.this, Popup_match.class);
                startActivity(intent);
            }
        });
    }

    private void initData(){
        //dataList = new ArrayList<>();
//        dataList.add(new ChatData("상대ID", null, Chat_code.ViewType.CENTER_CONTENT));
        dataList.add(new ChatData("안녕하세요", "내ID", Chat_code.ViewType.RIGHT_CONTENT));
        dataList.add(new ChatData("반갑습니다", "상대ID", Chat_code.ViewType.LEFT_CONTENT));
    }
}