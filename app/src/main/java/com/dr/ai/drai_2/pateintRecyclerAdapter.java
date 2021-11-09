package com.dr.ai.drai_2;

import android.content.Context;
import android.view.LayoutInflater;
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
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_p_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        patientPRecycler item = patientPRecyclers.get(position);
        holder.dateView.setText(item.getDate());
        holder.typeOAView.setText(item.getTypeOfSession());
        holder.doctorView.setText(item.getDoctorName());
    }

    @Override
    public int getItemCount() {
        // number of items displayed
        return patientPRecyclers.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
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
