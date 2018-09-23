package com.example.oudom.homework.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.oudom.homework.CouponDetailActivity;
import com.example.oudom.homework.MainActivity;
import com.example.oudom.homework.model.Information;
import com.example.oudom.homework.R;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Information> mdata;
    RequestOptions option;
    public RecyclerViewAdapter(Context mContext, List<Information> mdata) {
        this.mContext = mContext;
        this.mdata = mdata;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view =inflater.inflate(R.layout.list_item,parent,false);
        final MyViewHolder viewholder= new MyViewHolder(view);
        viewholder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CouponDetailActivity.class);
                intent.putExtra("food_detail_name",mdata.get(viewholder.getAdapterPosition()).getFoodName());
                intent.putExtra("food_detail_image",mdata.get(viewholder.getAdapterPosition()).getFoodImage());
                mContext.startActivity(intent);
            }
        });
        return viewholder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.foodname.setText(mdata.get(position).getFoodName());
        holder.fooddisc.setText(mdata.get(position).getFoodDisc());
        //loading image
        Glide.with(mContext).load(mdata.get(position).getFoodImage()).apply(option).into(holder.foodimage);
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView foodname,fooddisc;
        ImageView foodimage;
        LinearLayout view_container;


        public MyViewHolder(View itemView) {
            super(itemView);
            foodname = itemView.findViewById(R.id.foodName);
            fooddisc = itemView.findViewById(R.id.foodDisc);
            foodimage= itemView.findViewById(R.id.foodImage);

            view_container = itemView.findViewById(R.id.rowitem_container);
        }
    }
}
