package com.srd.wemate.expense;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.srd.wemate.R;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.CustomViewHolder> {
    private ArrayList<ExpenseData> arrayList;
    private Context context;

    public ExpenseAdapter(ArrayList<ExpenseData> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ExpenseAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_expense_item, parent, false);
        ExpenseAdapter.CustomViewHolder holder = new ExpenseAdapter.CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.CustomViewHolder holder, int position) {
        holder.id.setText(arrayList.get(position).getId());
        holder.money.setText(Integer.toString(arrayList.get(position).getMoney()));
        holder.purpose.setText(arrayList.get(position).getPurpose());
        holder.date.setText(arrayList.get(position).getDate());
        holder.memo.setText(arrayList.get(position).getMemo());

        holder.itemView.setTag(position);

    }

    public void setList(ArrayList<ExpenseData> list){
        this.arrayList = list;
        // adapter의 값이 변경되었다는 것을 알려줍니다
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    void addItem(ExpenseData data) {
        // 외부에서 item을 추가시킬 함수입니다.
        System.out.println(data);
        arrayList.add(data);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView id,money,purpose,date,memo;
        protected androidx.constraintlayout.widget.ConstraintLayout layout;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.id = itemView.findViewById(R.id.id);
            this.money = itemView.findViewById(R.id.money);
            this.purpose = itemView.findViewById(R.id.purpose);
            this.date = itemView.findViewById(R.id.date);
            this.memo = itemView.findViewById(R.id.content);

            layout = itemView.findViewById(R.id.layout);
        }
    }


}
