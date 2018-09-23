package com.example.oudom.homework;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class CouponDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_detail);

        //hide default action bar
//        getSupportActionBar().hide();

        //recieve data
        String name=  getIntent().getExtras().getString("food_detail_name");
        String img_url=  getIntent().getExtras().getString("food_detail_image");
        // init view
//        CollapsingToolbarLayout collapsingtoolbarlayout=findViewById(R.id.collaspingtoolbar_id);
//        collapsingtoolbarlayout.setTitleEnabled(true);

        TextView fname= findViewById(R.id.aa_foodName);
        ImageView fimg= findViewById(R.id.aa_foodImage);

        fname.setText(name);
        //set image uri using glide

        RequestOptions requestoption = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        Glide.with(this).load(img_url).into(fimg);
    }

}
