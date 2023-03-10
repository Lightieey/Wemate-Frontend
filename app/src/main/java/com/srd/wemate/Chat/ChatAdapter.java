package com.srd.wemate.Chat;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.srd.wemate.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ChatData> myDataList;

    public ChatAdapter(ArrayList<ChatData> dataList){
        myDataList = dataList;
        System.out.println("ChatAdapter 실행됨"+dataList);
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//        if(viewType == Chat_code.ViewType.CENTER_CONTENT){
//            view = inflater.inflate(R.layout.activity_chat_center_item,parent,false);
//            return new CenterViewHolder(view);

        if(viewType == Chat_code.ViewType.LEFT_CONTENT){
            view = inflater.inflate(R.layout.chat_left_item,parent,false);
            return new LeftViewHolder(view);
        }else{
            view = inflater.inflate(R.layout.chat_right_item,parent,false);
            return new RightViewHolder(view);
        }
    }

    // 각각의 뷰 홀더에 데이터를 연결
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
//        if(viewHolder instanceof CenterViewHolder){
//            ((CenterViewHolder)viewHolder).u_id.setText(myDataList.get(position).getContent());

        if(viewHolder instanceof LeftViewHolder){
            ((LeftViewHolder)viewHolder).u_id.setText(myDataList.get(position).getI_id());
            ((LeftViewHolder)viewHolder).content.setText(myDataList.get(position).getContent());
        }else{
            ((RightViewHolder)viewHolder).content.setText(myDataList.get(position).getContent());
        }
    }


    // 리사이클러뷰안에서 들어갈 뷰 홀더의 개수
    @Override
    public int getItemCount() {
        return myDataList.size();
    }


    // 위에 3개만 오버라이드가 기본 셋팅이라서
    // 여기서 ViewType 오버라이딩 (구별할려고)
    @Override
    public int getItemViewType(int position) {
        return myDataList.get(position).getViewType();
    }


    // 리사이클러뷰에 들어갈 뷰 홀더, 그 안의 Item들-------------------------
//    맨 위 툴바
//    public class CenterViewHolder extends RecyclerView.ViewHolder{
//        TextView u_id;
//
//
//        public CenterViewHolder(@NonNull View itemView) {
//            super(itemView);
//            u_id = (TextView)itemView.findViewById(R.id.u_id);
//        }
//
//    }

    // 상대방 채팅
    public class LeftViewHolder extends RecyclerView.ViewHolder{
        ImageView u_img;
        TextView u_id;
        TextView content;
        TextView time;

        public LeftViewHolder(@NonNull View itemView) {
            super(itemView);
            u_img = (ImageView)itemView.findViewById(R.id.u_img);
            u_id = (TextView)itemView.findViewById(R.id.u_id);
            content = (TextView)itemView.findViewById(R.id.content);
            time = (TextView)itemView.findViewById(R.id.time);

            u_img.setBackground(new ShapeDrawable(new OvalShape()));
            u_img.setClipToOutline(true);

        }
    }

    // 내 채팅
    public class RightViewHolder extends RecyclerView.ViewHolder{
        TextView content;
        TextView time;

        public RightViewHolder(@NonNull View itemView) {
            super(itemView);
            content = (TextView)itemView.findViewById(R.id.content);
            time = (TextView)itemView.findViewById(R.id.time);
        }
    }

}