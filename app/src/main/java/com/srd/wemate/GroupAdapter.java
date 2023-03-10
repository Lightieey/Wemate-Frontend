package com.srd.wemate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.srd.wemate.model.dto.profile.ProfileListResponseDto;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.CustomViewHolder>  {
    private ArrayList<ProfileListResponseDto> arrayList;
    private Context context;

    public GroupAdapter(ArrayList<ProfileListResponseDto> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public GroupAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_group_item, parent, false);
        GroupAdapter.CustomViewHolder holder = new GroupAdapter.CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAdapter.CustomViewHolder holder, int position) {
        holder.id.setText(arrayList.get(position).getName());
        holder.itemView.setTag(position);

    }

    public void setList(ArrayList<ProfileListResponseDto> list){
        this.arrayList = list;

        // adapter의 값이 변경되었다는 것을 알려줍니다
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    void addItem(ProfileListResponseDto data2) {
        // 외부에서 item을 추가시킬 함수입니다.
        System.out.println(data2);
        arrayList.add(data2);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView id;
        protected androidx.constraintlayout.widget.ConstraintLayout layout;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.id = itemView.findViewById(R.id.id);

            layout = itemView.findViewById(R.id.layout);
        }
    }

}
