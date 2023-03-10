package com.srd.wemate;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.srd.wemate.model.Profile;
import com.srd.wemate.model.Profile_Characteristic;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.CustomViewHolder> {
    private ArrayList<Profile> arrayList;
    private Context context;

    public SearchAdapter(ArrayList<Profile> arrayList,  Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_search_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.CustomViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id.setText(arrayList.get(position).getName());
        holder.title.setText(arrayList.get(position).getTitle());
//        holder.tag1.setText(characteristics.get(position).getLife_style());
//        holder.tag2.setText(characteristics.get(position).getGuest());
//        holder.tag3.setText(characteristics.get(position).getClean());
//        holder.tag4.setText(characteristics.get(position).getShower());
//        holder.tag5.setText(characteristics.get(position).getShare());
//        holder.tag6.setText(characteristics.get(position).getRest_style());
//        holder.tag7.setText(characteristics.get(position).getRelation());
        holder.itemView.setTag(position);
        holder.layout.setClickable(true);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(context, SearchProfile.class);
                    intent.putExtra("select", arrayList.get(position).getId());
                    context.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
                }
            }
        });


    }

    public void setList(ArrayList<Profile> list ) {
        this.arrayList = list;
        // adapter의 값이 변경되었다는 것을 알려줍니다
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    void addItem(Profile data) {
        // 외부에서 item을 추가시킬 함수입니다.
        arrayList.add(data);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView id, title,tag1,tag2,tag3,tag4,tag5,tag6,tag7;
        protected androidx.constraintlayout.widget.ConstraintLayout layout;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.id = itemView.findViewById(R.id.id);
            this.title = itemView.findViewById(R.id.title);
            this.tag1 = itemView.findViewById(R.id.tag1);
            this.tag2 = itemView.findViewById(R.id.tag2);
            this.tag3 = itemView.findViewById(R.id.tag3);
            this.tag4 = itemView.findViewById(R.id.tag4);
            this.tag5 = itemView.findViewById(R.id.tag5);
            this.tag6 = itemView.findViewById(R.id.tag6);
            this.tag7 = itemView.findViewById(R.id.tag7);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}