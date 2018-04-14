package com.example.sravanreddy.bmwassignment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>{
    ArrayList<Places> list;
    Context context;

    public MyRecyclerViewAdapter(ArrayList<Places> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Places places=list.get(position);
        holder.tv_Id.setText("ID: "+places.getID());
        holder.tv_name.setText("Name: "+places.getName());
        holder.tv_latitude.setText("Latitude: "+places.getLatitude()+"");
        holder.tv_longitude.setText("Longitude: "+places.getLongitude()+"");
        holder.tv_address.setText("Address: "+places.getAddress());
        holder.tv_arrivalTime.setText("Arrival Time: "+places.getArrivalTime());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, MapsActivity.class);
                Bundle bundleLatLong=new Bundle();
                bundleLatLong.putParcelable("Place", places);
                intent.putExtras(bundleLatLong);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        LinearLayout linearLayout;
        TextView tv_Id, tv_name, tv_latitude, tv_longitude, tv_address, tv_arrivalTime;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_Id=itemView.findViewById(R.id.Id_tv);
            tv_name=itemView.findViewById(R.id.name_tv);
            tv_latitude=itemView.findViewById(R.id.latitude_tv);
            tv_longitude=itemView.findViewById(R.id.logitude_tv);
            tv_address=itemView.findViewById(R.id.address_tv);
            tv_arrivalTime=itemView.findViewById(R.id.arrivaltime_tv);
            linearLayout=itemView.findViewById(R.id.recyclerView_linearLayout);
        }
    }
}
