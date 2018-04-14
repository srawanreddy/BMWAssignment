package com.example.sravanreddy.bmwassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
private RecyclerView placesList;
private ArrayList<Places> places_list;
private MyRecyclerViewAdapter myRecyclerViewAdapter;
private LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        places_list=new ArrayList<>();
        myRecyclerViewAdapter=new MyRecyclerViewAdapter(places_list, MainActivity.this);
        linearLayoutManager=new LinearLayoutManager(getApplicationContext());

        jasonRequest();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.options_menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sort_name:
                Collections.sort(places_list,new PlacesSortByNames());
                myRecyclerViewAdapter.notifyDataSetChanged();
                return true;
            case R.id.sort_address:
                Collections.sort(places_list, new PlacesSortByAddress());
                myRecyclerViewAdapter.notifyDataSetChanged();
                return true;
            case R.id.sort_distance:
                Toast.makeText(this, "I did not sort by distance.",Toast.LENGTH_SHORT).show();
                myRecyclerViewAdapter.notifyDataSetChanged();
                return true;
                default:
                    return false;
        }
    }

    private void jasonRequest() {
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, "http://localsearch.azurewebsites.net/api/Locations", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i("Response Listener", response.toString());
                try {
                for(int i=0;i<response.length();i++) {

                        JSONObject jsonObject = (JSONObject) response.get(i);
                        long ID = jsonObject.getLong("ID");
                        String Name = jsonObject.getString("Name");
                        double Latitude = jsonObject.getDouble("Latitude");
                        double Longitude = jsonObject.getDouble("Longitude");
                        String Address = jsonObject.getString("Address");
                        String ArrivalTime = jsonObject.getString("ArrivalTime");
                        places_list.add(new Places(ID, Name, Latitude, Longitude, Address, ArrivalTime));

                    }
                }catch (JSONException e) {
                        e.printStackTrace();
                    }


                placesList=findViewById(R.id.list_places);
                placesList.setHasFixedSize(true);
                placesList.setItemAnimator(new DefaultItemAnimator());
                placesList.setLayoutManager(linearLayoutManager);
                placesList.setAdapter(myRecyclerViewAdapter);
                myRecyclerViewAdapter.notifyDataSetChanged();
                DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(placesList.getContext(), linearLayoutManager.getOrientation() );
                placesList.addItemDecoration(dividerItemDecoration);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Response Error", error.toString());
            }
        });

        RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
        queue.add(jsonArrayRequest);
    }
}
