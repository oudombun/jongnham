package com.example.oudom.homework;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.oudom.homework.adapter.RecyclerViewAdapter;
import com.example.oudom.homework.fragment.CouponFragment;
import com.example.oudom.homework.fragment.FavFragment;
import com.example.oudom.homework.fragment.HomeFragment;
import com.example.oudom.homework.fragment.UserFragment;
import com.example.oudom.homework.model.Information;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String json_url="https://getandroiddata.000webhostapp.com/";
//    private final String json_url="http://localhost/getJson.php";
    private JsonArrayRequest jrequest;
    private RequestQueue requestQueue;
    private List<Information> lstInfomation;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        BottomNavigationView bottonNav= findViewById(R.id.navigation_id);
//        bottonNav.setOnNavigationItemSelectedListener(navListener);


        lstInfomation =new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        jsonrequest();
    }

    private void jsonrequest() {
        jrequest = new JsonArrayRequest(json_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObj = null;

                for (int i = 0 ; i<response.length();i++){
                    try {
                        jsonObj= response.getJSONObject(i);
                        Information info = new Information();
                        info.setFoodName(jsonObj.getString("food_name"));
                        info.setFoodDisc(jsonObj.getString("food_disc"));
                        info.setFoodImage(jsonObj.getString("food_img"));
                        lstInfomation.add(info);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }

                setuprecyclerview(lstInfomation);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"errror",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jrequest);
    }

    private void setuprecyclerview(List<Information> lstInfomation) {
        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this,lstInfomation);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);
    }



    //    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
//                new BottomNavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    Fragment selectedFragment= null;
//                    switch (item.getItemId()){
//                        case R.id.nav_home:
//                            selectedFragment = new HomeFragment();
//                            break;
//                        case R.id.nav_coupon:
//                            selectedFragment = new CouponFragment();
//                            break;
//                        case R.id.nav_fav:
//                            selectedFragment = new FavFragment();
//                            break;
//                        case R.id.nav_user:
//                            selectedFragment = new UserFragment();
//                            break;
//                    }
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                            selectedFragment).commit();
//                    return true;
//                }
//            };
}
