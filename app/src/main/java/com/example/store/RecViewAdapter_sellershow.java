package com.example.store;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecViewAdapter_sellershow extends RecyclerView.Adapter<RecViewAdapter_sellershow.MyViewHolder> {
    Context context;
    List<Seller_List> Data;

    public RecViewAdapter_sellershow(Context context, List<Seller_List> data) {
        this.context = context;
        Data = data;
    }


    @NonNull
    @Override
    public RecViewAdapter_sellershow.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.item_recycleview_sellershow, parent, false);
        MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecViewAdapter_sellershow.MyViewHolder holder, final int position) {
        holder.user.setText(Data.get(position).getUser());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             /*   Intent Go_To_Select_Season=new Intent(context,SelectSeason.class);
                Go_To_Select_Season.putExtra("position_recyclerView",position);
                Go_To_Select_Season.putExtra("name_novel",Data.get(position).getName_novel());
                Go_To_Select_Season.putExtra("Number_Of_Season",Data.get(position).getNumber_OF_Season());
                context.startActivity(Go_To_Select_Season);*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView user;

        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.textViewNameAdapterRcyclershow1);
            linearLayout = itemView.findViewById(R.id.linear_recy_sellershow);
        }
    }
}
