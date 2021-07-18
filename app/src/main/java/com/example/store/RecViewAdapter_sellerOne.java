package com.example.store;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import DataBase.Mahsoolat;


public class RecViewAdapter_sellerOne extends RecyclerView.Adapter<RecViewAdapter_sellerOne.MyViewHolder> {

    Context context;
    List<Product_List_One> Data;
    Mahsoolat mahsoolat;

    public RecViewAdapter_sellerOne(Context context, List<Product_List_One> data) {
        this.context = context;
        Data = data;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_sllerone, parent, false);
        MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.name.setText(Data.get(position).getName());
        holder.price.setText(Data.get(position).getPrice());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (context instanceof SellerPage) {
                    Intent Go_To = new Intent(context, Click_Product.class);
                    Go_To.putExtra("key_positionrecycler", Data.get(position).getID());
                    context.startActivity(Go_To);
                }
                if (context instanceof AdminPriority) {
                    mahsoolat=new Mahsoolat(context);
                    Cursor res = mahsoolat.ShowallData();
                    while (res.moveToNext()) {
                        if (res.getInt(0)== Data.get(position).getID()) {
                            mahsoolat.updateData(Integer.toString( Data.get(position).getID()),res.getString(1),res.getString(2),res.getString(3),res.getInt(4),res.getString(5),res.getString(6),1);
                            Toast.makeText(context, "this product will be shown at the top", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
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
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewNameAdapterRcycler1);
            price = itemView.findViewById(R.id.textViewPriceAdapterRcycler1);
            linearLayout = itemView.findViewById(R.id.linear_recy_sllerOne);
        }
    }
}
