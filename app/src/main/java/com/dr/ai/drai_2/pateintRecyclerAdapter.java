package com.dr.ai.drai_2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class pateintRecyclerAdapter extends RecyclerView.Adapter<pateintRecyclerAdapter.MyViewHolder> {

    Context context;
    ArrayList<patientPRecycler> patientPRecyclers;

    public pateintRecyclerAdapter(Context context, ArrayList<patientPRecycler> patientPRecyclers) {
        this.context = context;
        this.patientPRecyclers = patientPRecyclers;
    }
    @NonNull
    @Override
    public pateintRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate Layout (giving a look to each row)
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        // number of items displayed
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // takes views from recycler_view_row layout file

        TextView doctorView, typeOAView, dateView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorView = itemView.findViewById(R.id.patientView);
            typeOAView = itemView.findViewById(R.id.typeOAView);
            dateView = itemView.findViewById(R.id.dateView);

        }
    }
}
