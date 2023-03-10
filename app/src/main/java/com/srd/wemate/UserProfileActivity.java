package com.srd.wemate;

import static com.srd.wemate.MainActivity.user_id;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.srd.wemate.model.Profile;
import com.srd.wemate.retrofit.ProfileApi;
import com.srd.wemate.retrofit.RetrofitService;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener
{

    private final int GET_GALLERY_IMAGE = 200;
    private ImageView imageview;
    public static Uri selectedImageUri;
    ArrayAdapter<CharSequence> adspin1, adspin2;
    String choice_do="";
    String choice_se="";
    private Spinner spinner_term;
    private TextView tv_result;
    private File tempSelectFile;
    private Bitmap bm;
    private String temp,img;
    Button Button;
    public static String my_user_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        imageview = (ImageView)findViewById(R.id.imageView);
        imageview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });
        EditText inputEditName = findViewById(R.id.name);
        EditText inputEditTitle = findViewById(R.id.title);
        EditText inputEditContent = findViewById(R.id.introduction);
        //RadioGroup inputEditGender = findViewById(R.id.radioGroup);
        RadioButton inputEditGender1 = findViewById(R.id.radio_women);
        RadioButton inputEditGender2 = findViewById(R.id.radio_men);
        //RadioGroup inputEditSmoke = findViewById(R.id.radioGroup2);
        RadioButton inputEditSmoke1 = findViewById(R.id.radio_smoking);
        RadioButton inputEditSmoke2 = findViewById(R.id.radio_nonsmoking);
        //EditText inputEditAge = findViewById(R.id.age);
        RadioButton rbpet = findViewById(R.id.radio_pet);
        RadioButton rbnopet = findViewById(R.id.radio_no_pet);
        EditText inputEditPet = findViewById(R.id.pet);
        final Spinner spin1 = (Spinner)findViewById(R.id.spinner);
        final Spinner spin2 = (Spinner)findViewById(R.id.spinner2);
        final Spinner spinner_term = (Spinner)findViewById(R.id.spinner_term);
        final Spinner spinner_age = (Spinner)findViewById(R.id.spinner_age);
        RadioButton room_type1 = findViewById(R.id.radio_room_together);
        RadioButton room_type2 = findViewById(R.id.radio_room_private);
        RadioButton rent_fee1 = findViewById(R.id.radio_monthly_rent);
        RadioButton rent_fee2 = findViewById(R.id.radio_lump_sum);

        Button btn_refresh = (Button)findViewById(R.id.btn_refresh);


        adspin1 = ArrayAdapter.createFromResource(this, R.array.spinner_do, android.R.layout.simple_spinner_dropdown_item);

        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin1.setAdapter(adspin1);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adspin1.getItem(i).equals("강남구")) {
                    adspin2 = ArrayAdapter.createFromResource(UserProfileActivity.this, R.array.spinner_region_seoul_gangnam, android.R.layout.simple_spinner_dropdown_item);
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
                    adspin2 = ArrayAdapter.createFromResource(UserProfileActivity.this, R.array.spinner_region_seoul_gangdong, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("강북구")) {
                    choice_do = "강북구";
                    adspin2 = ArrayAdapter.createFromResource(UserProfileActivity.this, R.array.spinner_region_seoul_gangbuk, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("강서구")) {
                    choice_do = "강서구";
                    adspin2 = ArrayAdapter.createFromResource(UserProfileActivity.this, R.array.spinner_region_seoul_gangseo, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("관악구")) {
                    choice_do = "관악구";
                    adspin2 = ArrayAdapter.createFromResource(UserProfileActivity.this, R.array.spinner_region_seoul_gwanak, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("광진구")) {
                    choice_do = "광진구";
                    adspin2 = ArrayAdapter.createFromResource(UserProfileActivity.this, R.array.spinner_region_seoul_gwangjin, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("구로구")) {
                    choice_do = "구로구";
                    adspin2 = ArrayAdapter.createFromResource(UserProfileActivity.this, R.array.spinner_region_seoul_guro, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("금천구")) {
                    choice_do = "금천구";
                    adspin2 = ArrayAdapter.createFromResource(UserProfileActivity.this, R.array.spinner_region_seoul_geumcheon, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("노원구")) {
                    choice_do = "노원구";
                    adspin2 = ArrayAdapter.createFromResource(UserProfileActivity.this, R.array.spinner_region_seoul_nowon, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("도봉구")) {
                    choice_do = "도봉구";
                    adspin2 = ArrayAdapter.createFromResource(UserProfileActivity.this, R.array.spinner_region_seoul_dobong, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("동대문구")) {
                    choice_do = "동대문구";
                    adspin2 = ArrayAdapter.createFromResource(UserProfileActivity.this, R.array.spinner_region_seoul_dongdaemun, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("동작구")) {
                    choice_do = "동작구";
                    adspin2 = ArrayAdapter.createFromResource(UserProfileActivity.this, R.array.spinner_region_seoul_dongjag, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("마포구")) {
                    choice_do = "마포구";
                    adspin2 = ArrayAdapter.createFromResource(UserProfileActivity.this, R.array.spinner_region_seoul_mapo, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("서대문구")) {
                    choice_do = "서대문구";
                    adspin2 = ArrayAdapter.createFromResource(UserProfileActivity.this, R.array.spinner_region_seoul_seodaemun, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("서초구")) {
                    choice_do = "서초구";
                    adspin2 = ArrayAdapter.createFromResource(UserProfileActivity.this, R.array.spinner_region_seoul_seocho, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("성동구")) {
                    choice_do = "성동구";
                    adspin2 = ArrayAdapter.createFromResource(UserProfileActivity.this, R.array.spinner_region_seoul_seongdong, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("성북구")) {
                    choice_do = "성북구";
                    adspin2 = ArrayAdapter.createFromResource(UserProfileActivity.this, R.array.spinner_region_seoul_seongbuk, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("송파구")) {
                    choice_do = "송파구";
                    adspin2 = ArrayAdapter.createFromResource(UserProfileActivity.this, R.array.spinner_region_seoul_songpa, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("양천구")) {
                    choice_do = "양천구";
                    adspin2 = ArrayAdapter.createFromResource(UserProfileActivity.this, R.array.spinner_region_seoul_yangcheon, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("영등포구")) {
                    choice_do = "영등포구";
                    adspin2 = ArrayAdapter.createFromResource(UserProfileActivity.this, R.array.spinner_region_seoul_yeongdeungpo, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("용산구")) {
                    choice_do = "용산구";
                    adspin2 = ArrayAdapter.createFromResource(UserProfileActivity.this, R.array.spinner_region_seoul_yongsan, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("은평구")) {
                    choice_do = "은평구";
                    adspin2 = ArrayAdapter.createFromResource(UserProfileActivity.this, R.array.spinner_region_seoul_eunpyeong, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("종로구")) {
                    choice_do = "종로구";
                    adspin2 = ArrayAdapter.createFromResource(UserProfileActivity.this, R.array.spinner_region_seoul_jongno, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("중구")) {
                    choice_do = "중구";
                    adspin2 = ArrayAdapter.createFromResource(UserProfileActivity.this, R.array.spinner_region_seoul_jung, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("중랑구")) {
                    choice_do = "중랑구";
                    adspin2 = ArrayAdapter.createFromResource(UserProfileActivity.this, R.array.spinner_region_seoul_jungnanggu, android.R.layout.simple_spinner_dropdown_item);
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

        //todo 버튼 클릭시 이벤트
        btn_refresh.setOnClickListener(view -> {
            Toast.makeText(UserProfileActivity.this, "저장되었습니다", Toast.LENGTH_SHORT).show();


            //String name = String.valueOf(inputEditName.getText());
            //String title = String.valueOf(inputEditTitle .getText());
            //String gender = String.valueOf(inputEditGender .getCheckedRadioButtonId());
            //String smoke = String.valueOf(inputEditSmoke .getCheckedRadioButtonId()); //값이 string으로 리턴되는지 확인해봐야 함
            //String age = String.valueOf(inputEditAge .getText());
            //String pet = String.valueOf(inputEditPet .getText());

            my_user_name = inputEditName.getText().toString();
            String title = inputEditTitle.getText().toString();
            String content = inputEditContent.getText().toString();
            String add1 = spin1.getSelectedItem().toString();
            String add2 = spin2.getSelectedItem().toString();
            String period = spinner_term.getSelectedItem().toString();
            String age = spinner_age.getSelectedItem().toString();

            System.out.println("title" + title);

            String gender;
            if (inputEditGender1.isChecked()) {
                //gender = inputEditGender1.getText().toString();
                gender = "female";
            } else {
                //gender = inputEditGender2.getText().toString();
                gender = "male";
            }
            System.out.println("gender" + gender);

            boolean smoke;
            if (inputEditSmoke1.isChecked()) {
                //smoke = inputEditSmoke1.getText().toString();
                smoke = true;
            } else {
                //smoke = inputEditSmoke2.getText().toString();
                smoke = false;
            }
            System.out.println("smoke" + smoke);

            //int age = Integer.parseInt(inputEditAge.getText().toString());

            String pet;
            if (rbpet.isChecked()) {
                pet = inputEditPet.getText().toString();
            } else {
                pet = "null";
            }

            String room_type;
            if (room_type1.isChecked()) {
                room_type = room_type1.getText().toString();
            } else {
                room_type = room_type2.getText().toString();
            }

            String rent_fee;
            if (rent_fee1.isChecked()) {
                rent_fee = rent_fee1.getText().toString();
            } else {
                rent_fee = rent_fee2.getText().toString();
            }



        Spinner spinner3 = (Spinner) findViewById(R.id.spinner_term);
/*        tv_result = (TextView)findViewById(R.id.tv_result);

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv_result.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

            RetrofitService retrofitService = new RetrofitService();
            ProfileApi profileApi = retrofitService.getRetrofit().create(ProfileApi.class);

            Profile profile = new Profile();
            profile.setId(user_id); //여기 null이 들어가면 실행이 안됨 -->최종에서는 무조건 로그인을 해서 user_id에 값이 들어간 다음에 실행
            //profile.setId("id넣기");
            //profile.setId(user_id); //여기 null이 들어가면 실행이 안됨 -->최종에서는 무조건 로그인을 해서 user_id에 값이 들어간 다음에 실행
            profile.setName(my_user_name);
            profile.setTitle(title);
            profile.setContent(content);
            profile.setGender(gender);
            profile.setSmoke(smoke);
            profile.setAge(age);
            profile.setPet(pet);
            profile.setAddr1(add1);
            profile.setAddr2(add2);
            profile.setPeriod(period);
            profile.setRoom_type(room_type);
            profile.setRent_fee(rent_fee);
            //System.out.println("이미지 타입 확인:"+img.getClass().getName()+"  프린트:"+img);
            profile.setImg("img");


            profileApi.save(profile)
                    .enqueue(new Callback<Profile>() {
                        @Override
                        public void onResponse(Call<Profile> call, Response<Profile> response) {
                            Log.i("save","Response성공:"+response.body().toString());
                        }

                        @Override
                        public void onFailure(Call<Profile> call, Throwable t) {
                            //Log.i("save","Response실패:"+t);
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"에러",t);
                        }
                    });

            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);

        });
    }
//    private Bitmap resize(Bitmap bm) {
//        Configuration config = getResources().getConfiguration();
//        if (config.smallestScreenWidthDp >= 800)
//            bm = Bitmap.createScaledBitmap(bm, 400, 240, true);
//        else if (config.smallestScreenWidthDp >= 600)
//            bm = Bitmap.createScaledBitmap(bm, 300, 180, true);
//        else if (config.smallestScreenWidthDp >= 400)
//            bm = Bitmap.createScaledBitmap(bm, 200, 120, true);
//        else if (config.smallestScreenWidthDp >= 360)
//            bm = Bitmap.createScaledBitmap(bm, 180, 108, true);
//        else
//            bm = Bitmap.createScaledBitmap(bm, 160, 96, true);
//        return bm;
//    }
//    public static String byteArrayToBinaryString(byte[] b) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < b.length; ++i) {
//            sb.append(byteToBinaryString(b[i]));
//        }
//        return sb.toString();
//    }

    // 바이너리 바이트를 스트링으로
//    public static String byteToBinaryString(byte n) {
//        StringBuilder sb = new StringBuilder("00000000");
//        for (int bit = 0; bit < 8; bit++) {
//            if (((n >> bit) & 1) > 0) {
//                sb.setCharAt(7 - bit, '1');
//            }
//        }
//        return sb.toString();
//    }
    //    private void BitMapToString(Bitmap bitmap) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);    //bitmap compress
//        byte[] arr = baos.toByteArray();
//        String image = Base64.encodeToString(arr, Base64.DEFAULT);
//        temp = "";
//        try {
//            temp = "&imagedevice=" + URLEncoder.encode(image, "utf-8");
//        } catch (Exception e) {
//            Log.e("exception", e.toString());
//        }
//
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();
            imageview.setImageURI(selectedImageUri);
            //bitmap 얻기
//            try {
//                bm = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData()); //bitmap
//                bm = resize(bm);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (OutOfMemoryError e) {
//                Toast.makeText(getApplicationContext(), "이미지 용량이 너무 큽니다.", Toast.LENGTH_SHORT).show();
//            }
            //bitmap을 bytearray로
            //ByteArrayOutputStream stream = new ByteArrayOutputStream();
            //bm.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            //byte[] byteArray = stream.toByteArray();
            //img = "&image=" + byteArrayToBinaryString(byteArray);
            //System.out.println(img.getClass().getName());
            //사진 저장
//            try {
//            InputStream in = getContentResolver().openInputStream(selectedImageUri);
//            Bitmap image = BitmapFactory.decodeStream(in);
//            imageview.setImageBitmap(image);
//            in.close();
//
//            String date = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
//            tempSelectFile = new File(Environment.getExternalStorageDirectory()+"/Pictures/Test/", "temp_" + date + ".jpeg");
//            OutputStream out = new FileOutputStream(tempSelectFile);
//            image.compress(Bitmap.CompressFormat.JPEG, 50, out);
//
//            }
//            catch (IOException ioe) { ioe.printStackTrace(); }


        }

        }


    @Override
    public void onClick(View view) {

    }
}
