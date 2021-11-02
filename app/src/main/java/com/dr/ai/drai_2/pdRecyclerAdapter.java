package com.dr.ai.drai_2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class pdRecyclerAdapter extends RecyclerView.Adapter<pdRecyclerAdapter.MyViewHolder>{

    Context context;
    ArrayList<pdRecyclerAdapter> pdRecyclerAdapters;
    public pdRecyclerAdapter(Context context, ArrayList<pdRecyclerAdapter> pdRecyclerAdapters) {
        this.context = context;
        this.pdRecyclerAdapters = pdRecyclerAdapters;
    }
    @NonNull
    @Override
    public pdRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull pdRecyclerAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView pdView, diagnosisView, prescriptionView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            pdView = itemView.findViewById(R.id.pdView);
            diagnosisView = itemView.findViewById(R.id.diagnosisView);
            prescriptionView = itemView.findViewById(R.id.prescriptionView);


        }
    }
}
