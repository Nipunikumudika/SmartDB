package com.nipuni.smartdb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<UserDataBase> names_list;
    private LayoutInflater inflater;
    itemClickListner itemClickListner;

    public UserAdapter(Context mContext, ArrayList<UserDataBase> names_list, itemClickListner itemClickListner) {
        this.mContext = mContext;
        this.names_list = names_list;
        this.itemClickListner = itemClickListner;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_user_data, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_name.setText(names_list.get(position).name);
        holder.tv_tp.setText(names_list.get(position).tp);

        holder.itemView.setOnClickListener(view -> {
            itemClickListner.onItemClick(position);
        });
    }
    @Override
    public int getItemCount() {
        return names_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        TextView tv_tp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_tp = itemView.findViewById(R.id.tv_tp);
        }
    }
    //create item click interface
    public interface itemClickListner{
        void onItemClick(int position);
    }
}

