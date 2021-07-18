package com.example.store;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;


public class RecViewAdapter_customerPage extends RecyclerView.Adapter<RecViewAdapter_customerPage.MyViewHolder> {

    Context context;
    List<Product_list_rec_customerPage> Data;

    public RecViewAdapter_customerPage(Context context, List<Product_list_rec_customerPage> data) {
        this.context = context;
        Data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_customer_page, parent, false);
        MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.name.setText(Data.get(position).getName());
        holder.price.setText(Data.get(position).getPrice() + "");
        Picasso.with(context)
                .load(new File(Data.get(position).getImagePath()))
                .into(holder.imageView);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Go_To = new Intent(context, Product_Show.class);
                Go_To.putExtra("key_positionrecycler", Data.get(position).getID());
                context.startActivity(Go_To);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView price;
        ImageView imageView;
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewName_rec_customerpage);
            price = itemView.findViewById(R.id.textViewPrice_rec_customerpage);
            imageView = itemView.findViewById(R.id.image_item_rec_customerPage);
            linearLayout = itemView.findViewById(R.id.linear_recy_sllerOne);
        }
    }
}
