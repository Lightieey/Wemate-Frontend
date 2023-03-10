package com.srd.wemate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.srd.wemate.Board.Board;
import com.srd.wemate.model.Characteristic;
import com.srd.wemate.model.Profile;
import com.srd.wemate.model.Profile_Characteristic;
import com.srd.wemate.retrofit.CharacteristicApi;
import com.srd.wemate.retrofit.ProfileApi;
import com.srd.wemate.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPage extends AppCompatActivity {
    private ArrayList<Profile> data,data_body; //원래 data 여기 있었음
    //private ArrayList<Profile_Characteristic> data;
    private ArrayList<Characteristic> c_data;
    private Characteristic c_data_body,characteristic;
    private SearchAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    ArrayAdapter<CharSequence> adspin1, adspin2;
    String choice_do="";
    String choice_se="";
    Button Button;
    ImageButton btnSearch;
    FloatingActionButton btnReset;

    static ArrayList<String> profileIdIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);


        //RecyclerView 연결--------------------------------------------------------
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        data = new ArrayList<>();
        c_data = new ArrayList<>();

        adapter = new SearchAdapter(data, getApplicationContext());
        recyclerView.setAdapter(adapter);



//        data.add(new SearchData(i + " ID", "인생 룸메이트를 찾고 싶어요"));
//        ////dummy
//        for (int i = 1; i <= 10; i++) {
//           if (i % 2 == 0)
//                data.add(new SearchData(i + " ID", "인생 룸메이트를 찾고 싶어요"));
//            else
//                data.add(new SearchData(i + " ID", "한학기 동안 같이 사실 분"));
//
//        }

        // db 연결
        RetrofitService retrofitService = new RetrofitService();
        ProfileApi profileApi = retrofitService.getRetrofit().create(ProfileApi.class);

        profileApi.getAllProfile().enqueue(new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                data_body = (ArrayList<Profile>) response.body();
                Log.i("save","검색 Response 성공:"+ response.body());
                Log.i("save","데이터 사이즈:"+ data_body.size());
                //Log.i("save","현재 thread 확인:"+ Thread.currentThread().getId());
                //Log.i("save","메인 thread 확인:"+ Looper.getMainLooper().getThread().getId());
                Log.i("save","데이터 겟:"+ data_body.get(0));

                for (int i = 0; i < data_body.size(); i++) {
                    data.add(data_body.get(i));
                    Log.i("save","데이터:"+ data_body);

                    //Profile에 맞는 Characteristic 가져오기
                    CharacteristicApi characteristicApi = retrofitService.getRetrofit().create(CharacteristicApi.class);
                    System.out.println("ddd" + data_body.get(i).getId().getClass().getName());
//                    characteristicApi.findById(data_body.get(i).getId()).enqueue(new Callback<Characteristic>() {
//                        @Override
//                        public void onResponse(Call<Characteristic> call, Response<Characteristic> response) {
//                            c_data_body =  response.body();
//                            Log.i("save","Characteristic Response성공:"+ c_data_body);
//                            Log.i("save","Characteristic 라이프스타일:"+ c_data_body.getLife_style());
////                            characteristic= new Characteristic();
//
//                            if(c_data_body.getLife_style().equals("day"))
//                            {data.set( "#얼리버드🐓");}
//                            else{data.setLife_style( "#올빼미🦉");}
//
//                            if(c_data_body.getRelation().equals("solo"))
//                            {characteristic.setLife_style(  "#개인플레이🙋");}
//                            else{characteristic.setLife_style( "#패밀리👨‍👩‍👧‍👦");}
//
//                            if(c_data_body.getRest_style().equals("home"))
//                            {characteristic.setLife_style( "#집순이🏠");}
//                            else{characteristic.setLife_style( "#밖순이🏙");}
//
//                            if(c_data_body.getShare().equals("Y"))
//                            {characteristic.setLife_style( "#물건 공유🙌");}
//                            else{characteristic.setLife_style( "#물건 각자✋");}
//
//                            if(c_data_body.getGuest().equals("Y"))
//                            {characteristic.setLife_style( "#손님 OK👫");}
//                            else{characteristic.setLife_style( "#손님 NO🧍");}
//
//                            if(c_data_body.getClean().equals("now"))
//                            {characteristic.setClean(  "#깔끔이🧹");}
//                            else{characteristic.setClean(  "#나중에 할게🧦");}
//
//                            if(c_data_body.getShower().equals("morning"))
//                            {characteristic.setLife_style( "#아침 샤워☀");}
//                            else{characteristic.setLife_style( "#저녁 샤워🌙");}
//
//                            c_data.add(characteristic);
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<Characteristic> call, Throwable t) {
//                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"에러",t);
//                        }
//                    });
                }


//                for (int i = 0; i < data.size(); i++) {
//                    Log.i("save","인덱스 에드 전");
//                    //data.add(new BoardData(datalist.get(i).getId(), datalist.get(i).getContent()));
//                    profileIdIndex.add(data.get(i).getId());
//                    Log.i("save","인덱스 에드 후");
//                }
                //// adapter의 값이 변경되었다는 것을 알려줍니다
                Log.i("save","어뎁터 셋리스트 전");
                adapter.setList(data);
                Log.i("save","어뎁터 셋리스트 후");
            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "에러", t);
            }
        });




        //// adapter의 값이 변경되었다는 것을 알려줍니다
        Log.i("save","어댑터에 알려줌 전");
//        adapter.setList(data);
        Log.i("save","어댑터에 알려줌 후");

        //하단 바--------------------------------------------------------
        Log.i("save","하단바 전");
        Button = (Button) findViewById(R.id.menu_pf);
        Log.i("save","하단바 후");
        Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Log.i("save","하단바 버튼 클릭");
                Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(intent);
            }
        });

        Button = (Button) findViewById(R.id.menu_home);
        Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), RulesBulletin.class);
                startActivity(intent);
            }
        });

        btnReset = (FloatingActionButton) findViewById(R.id.btn_reset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setList(data);
            }
        });

        //화면 전환 효과 없애기--------------------------------------------
        Log.i("save","getwindow 실행x");
        getWindow().setWindowAnimations(0);
        Log.i("save","getwindow 실행o");

        adspin1 = ArrayAdapter.createFromResource(this, R.array.spinner_do, android.R.layout.simple_spinner_dropdown_item);

        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner spin1 = (Spinner)findViewById(R.id.spinner);
        final Spinner spin2 = (Spinner)findViewById(R.id.spinner2);
        spin1.setAdapter(adspin1);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adspin1.getItem(i).equals("강남구")) {
                    adspin2 = ArrayAdapter.createFromResource(SearchPage.this, R.array.spinner_region_seoul_gangnam, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) { //아무것도 선택안될시 자동완성
                        }
                    });
                } else if (adspin1.getItem(i).equals("강동구")) {
                    choice_do = "강동구";
                    adspin2 = ArrayAdapter.createFromResource(SearchPage.this, R.array.spinner_region_seoul_gangdong, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("강북구")) {
                    choice_do = "강북구";
                    adspin2 = ArrayAdapter.createFromResource(SearchPage.this, R.array.spinner_region_seoul_gangbuk, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("강서구")) {
                    choice_do = "강서구";
                    adspin2 = ArrayAdapter.createFromResource(SearchPage.this, R.array.spinner_region_seoul_gangseo, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("관악구")) {
                    choice_do = "관악구";
                    adspin2 = ArrayAdapter.createFromResource(SearchPage.this, R.array.spinner_region_seoul_gwanak, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("광진구")) {
                    choice_do = "광진구";
                    adspin2 = ArrayAdapter.createFromResource(SearchPage.this, R.array.spinner_region_seoul_gwangjin, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("구로구")) {
                    choice_do = "구로구";
                    adspin2 = ArrayAdapter.createFromResource(SearchPage.this, R.array.spinner_region_seoul_guro, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("금천구")) {
                    choice_do = "금천구";
                    adspin2 = ArrayAdapter.createFromResource(SearchPage.this, R.array.spinner_region_seoul_geumcheon, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("노원구")) {
                    choice_do = "노원구";
                    adspin2 = ArrayAdapter.createFromResource(SearchPage.this, R.array.spinner_region_seoul_nowon, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("도봉구")) {
                    choice_do = "도봉구";
                    adspin2 = ArrayAdapter.createFromResource(SearchPage.this, R.array.spinner_region_seoul_dobong, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("동대문구")) {
                    choice_do = "동대문구";
                    adspin2 = ArrayAdapter.createFromResource(SearchPage.this, R.array.spinner_region_seoul_dongdaemun, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("동작구")) {
                    choice_do = "동작구";
                    adspin2 = ArrayAdapter.createFromResource(SearchPage.this, R.array.spinner_region_seoul_dongjag, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("마포구")) {
                    choice_do = "마포구";
                    adspin2 = ArrayAdapter.createFromResource(SearchPage.this, R.array.spinner_region_seoul_mapo, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("서대문구")) {
                    choice_do = "서대문구";
                    adspin2 = ArrayAdapter.createFromResource(SearchPage.this, R.array.spinner_region_seoul_seodaemun, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("서초구")) {
                    choice_do = "서초구";
                    adspin2 = ArrayAdapter.createFromResource(SearchPage.this, R.array.spinner_region_seoul_seocho, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("성동구")) {
                    choice_do = "성동구";
                    adspin2 = ArrayAdapter.createFromResource(SearchPage.this, R.array.spinner_region_seoul_seongdong, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("성북구")) {
                    choice_do = "성북구";
                    adspin2 = ArrayAdapter.createFromResource(SearchPage.this, R.array.spinner_region_seoul_seongbuk, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("송파구")) {
                    choice_do = "송파구";
                    adspin2 = ArrayAdapter.createFromResource(SearchPage.this, R.array.spinner_region_seoul_songpa, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("양천구")) {
                    choice_do = "양천구";
                    adspin2 = ArrayAdapter.createFromResource(SearchPage.this, R.array.spinner_region_seoul_yangcheon, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("영등포구")) {
                    choice_do = "영등포구";
                    adspin2 = ArrayAdapter.createFromResource(SearchPage.this, R.array.spinner_region_seoul_yeongdeungpo, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("용산구")) {
                    choice_do = "용산구";
                    adspin2 = ArrayAdapter.createFromResource(SearchPage.this, R.array.spinner_region_seoul_yongsan, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("은평구")) {
                    choice_do = "은평구";
                    adspin2 = ArrayAdapter.createFromResource(SearchPage.this, R.array.spinner_region_seoul_eunpyeong, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("종로구")) {
                    choice_do = "종로구";
                    adspin2 = ArrayAdapter.createFromResource(SearchPage.this, R.array.spinner_region_seoul_jongno, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("중구")) {
                    choice_do = "중구";
                    adspin2 = ArrayAdapter.createFromResource(SearchPage.this, R.array.spinner_region_seoul_jung, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("중랑구")) {
                    choice_do = "중랑구";
                    adspin2 = ArrayAdapter.createFromResource(SearchPage.this, R.array.spinner_region_seoul_jungnanggu, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        final Spinner spinner_pay = (Spinner)findViewById(R.id.spinner_pay);
        final Spinner spinner_gender = (Spinner)findViewById(R.id.spinner_gender);
        final Spinner spinner_term = (Spinner)findViewById(R.id.spinner_term);
        final Spinner spinner_room = (Spinner)findViewById(R.id.spinner_room);
        final Spinner spinner_age = (Spinner)findViewById(R.id.spinner_age);


        btnSearch = (ImageButton) findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String add1 = spin1.getSelectedItem().toString();
                String add2t = null;
                if (spin2.getSelectedItem() != null) {
                    add2t = spin2.getSelectedItem().toString();
                }
                String pay = spinner_pay.getSelectedItem().toString();
                String gender = spinner_gender.getSelectedItem().toString();
                String term = spinner_term.getSelectedItem().toString();
                String room = spinner_room.getSelectedItem().toString();
                String age = spinner_age.getSelectedItem().toString();

                // db 연결
                RetrofitService retrofitService = new RetrofitService();
                ProfileApi profileApi = retrofitService.getRetrofit().create(ProfileApi.class);

                String add2 = add2t;
                profileApi.getAllProfile().enqueue(new Callback<List<Profile>>() {
                    @Override
                    public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                        Log.i("save","검색:"+ response.body());

                        ArrayList<Profile> datalist = (ArrayList<Profile>) response.body();
                        ArrayList<Profile> temp = new ArrayList<>();

                        if (!add1.equals("지역구")) {
                            for (int i = 0; i < datalist.size() - 1; i++) {
                                if (add1.equals(datalist.get(i).getAddr1())) {
                                    temp.add(datalist.get(i));
                                }
                            }
                            datalist = temp;

                            if (!add2.equals("지역동")) {
                                for (int i = 0; i < datalist.size(); i++) {
                                    if (add2.equals(datalist.get(i).getAddr2())) {
                                        temp.add(datalist.get(i));
                                    }
                                }
                                datalist = temp;
                            }
                        }

                        if (!pay.equals("월/전세")) {
                            for (int i = 0; i < datalist.size(); i++) {
                                if (pay.equals(datalist.get(i).getRent_fee())) {
                                    temp.add(datalist.get(i));
                                }
                            }
                            datalist = temp;
                        }

                        if (!gender.equals("성별")) {
                            for (int i = 0; i < datalist.size(); i++) {
                                if (gender.equals(datalist.get(i).getGender())) {
                                    temp.add(datalist.get(i));
                                }
                            }
                            datalist = temp;
                        }

                        if (!term.equals("주거기간")) {
                            for (int i = 0; i < datalist.size(); i++) {
                                if (term.equals(datalist.get(i).getPeriod())) {
                                    temp.add(datalist.get(i));
                                }
                            }
                            datalist = temp;
                        }

                        if (!room.equals("주거형태")) {
                            for (int i = 0; i < datalist.size(); i++) {
                                if (room.equals(datalist.get(i).getRoom_type())) {
                                    temp.add(datalist.get(i));
                                }
                            }
                            datalist = temp;
                        }

                        if (!age.equals("연령대")) {
                            for (int i = 0; i < datalist.size(); i++) {
                                if (age.equals(datalist.get(i).getAge())) {
                                    temp.add(datalist.get(i));
                                }
                            }
                            datalist = temp;
                        }
                        adapter.setList(datalist);
                    }

                    @Override
                    public void onFailure(Call<List<Profile>> call, Throwable t) {
                        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "에러", t);
                    }
                });
            }
        });
    }
}