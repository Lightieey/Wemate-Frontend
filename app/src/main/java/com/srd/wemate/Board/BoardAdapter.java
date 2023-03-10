package com.srd.wemate.Board;

import static com.srd.wemate.Board.Board.postsIdIndex;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.srd.wemate.MainActivity;
import com.srd.wemate.R;
import com.srd.wemate.model.dto.posts.PostsListResponseDto;
import com.srd.wemate.model.dto.posts.PostsResponseDto;
import com.srd.wemate.model.dto.posts.PostsUpdateRequestDto;
import com.srd.wemate.retrofit.PostsApi;
import com.srd.wemate.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.CustomViewHolder> {
    private ArrayList<PostsListResponseDto> arrayList;
    private Context context;

    public BoardAdapter(ArrayList<PostsListResponseDto> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_board_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id.setText(arrayList.get(position).getAuthor());
        holder.content.setText(arrayList.get(position).getContent());

        holder.itemView.setTag(position);
        holder.delete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());

                builder.setMessage("정말 삭제하시겠습니까?");
                builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.out.println(arrayList.get(position).getId());
                        remove(holder.getAdapterPosition());
                    }
                });

                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();
            }
        });

        holder.pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setpin(holder.getAdapterPosition());
            }
        });

        //체크버튼 클릭
        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!holder.check.isSelected())
                {
                    holder.check.setSelected(true);
                    holder.check_num.setText("1");
                }

                else
                {
                    holder.check.setSelected(false);
                    holder.check_num.setText("0");

                }
            }
        });
    }

    public void setList(List<PostsListResponseDto> list){
        this.arrayList = (ArrayList<PostsListResponseDto>) list;
        // adapter의 값이 변경되었다는 것을 알려줍니다
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public void remove(int position){

        // db에서 삭제
        RetrofitService retrofitService = new RetrofitService();
        PostsApi postsApi = retrofitService.getRetrofit().create(PostsApi.class);

        // 테스트용 코드 -> id 가져오는 코드 구현 필요
        Long postID = postsIdIndex.get(position);

        postsApi.delete(postID).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                //assert response.body() != null;
                Log.i("delete","Response성공:"+ response.body());

                arrayList.remove(position);
                notifyItemRemoved(position);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"에러",t);
            }
        });
    }

//    void addItem(BoardData data) {
//        // 외부에서 item을 추가시킬 함수입니다.
//        arrayList.add(data);
//    }
    public void setpin(int position) {

        // db에서 pin값 확인
        RetrofitService retrofitService = new RetrofitService();
        PostsApi postsApi = retrofitService.getRetrofit().create(PostsApi.class);

        Long postID = postsIdIndex.get(position);

        postsApi.findById(postID).enqueue(new Callback<PostsResponseDto>() {
            @Override
            public void onResponse(Call<PostsResponseDto> call, Response<PostsResponseDto> response) {
                //assert response.body() != null;
                Log.i("findbyId","Response성공:"+ response.body());
                boolean mypin = response.body().isPin();

                // db update
                if (mypin) {
                    mypin = false;
                } else {
                    mypin = true;
                }

                PostsUpdateRequestDto updateRequestDto = new PostsUpdateRequestDto(response.body().getContent(), mypin);

                postsApi.update(postID, updateRequestDto).enqueue(new Callback<Long>() {
                    @Override
                    public void onResponse(Call<Long> call, Response<Long> response) {
                        Log.i("update","Response성공:"+ response.body());
                        //notifyDataSetChanged();
                        //BoardAdapter.this.notify();
                        Intent intent = new Intent(context, Board.class);
                        context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    }

                    @Override
                    public void onFailure(Call<Long> call, Throwable t) {
                        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"에러",t);
                    }
                });

            }

            @Override
            public void onFailure(Call<PostsResponseDto> call, Throwable t) {
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"에러",t);
            }
        });

    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView id, content, check_num;
        protected LinearLayout layout;
        protected ImageButton delete, check;
        protected Button pin;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.id = itemView.findViewById(R.id.id);
            this.content = itemView.findViewById(R.id.content);
            layout = itemView.findViewById(R.id.layout);
            this.delete = itemView.findViewById(R.id.btn_delete);
            this.pin = itemView.findViewById(R.id.btn_pin);
            this.check = itemView.findViewById(R.id.btn_check);
            this.check_num = itemView.findViewById(R.id.check_num);
        }
    }
}