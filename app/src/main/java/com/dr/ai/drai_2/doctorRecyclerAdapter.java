package com.dr.ai.drai_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dr.ai.drai_2.model.Appointment;

import java.util.ArrayList;
import java.util.List;

public class doctorRecyclerAdapter extends RecyclerView.Adapter<doctorRecyclerAdapter.MyViewHolder> {

    Context context;
    List<Appointment> doctorRecyclers;

    public doctorRecyclerAdapter(Context context, List<Appointment> doctorRecyclers) {
        this.context = context;
        this.doctorRecyclers = doctorRecyclers;
    }

    @NonNull
    @Override
    public doctorRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate Layout (giving a look to each row)
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_row, parent, false);
        return new doctorRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull doctorRecyclerAdapter.MyViewHolder holder, int position) {
        //assign values to the views created in recycler_view_row
        //based on the position of the recycler view
        Appointment item = doctorRecyclers.get(position);
        holder.dateView.setText(item.getDate());
        holder.typeOAView.setText(item.getType());
        holder.patientView.setText(item.getPatientName());
    }

    @Override
    public int getItemCount() {
        // number of items displayed
        return doctorRecyclers.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        // takes views from recycler_view_row layout file

        TextView patientView, typeOAView, dateView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            patientView = itemView.findViewById(R.id.patientView);
            typeOAView = itemView.findViewById(R.id.typeOAView);
            dateView = itemView.findViewById(R.id.dateView);

        }
    }
}
