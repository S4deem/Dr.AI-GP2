package com.dr.ai.drai_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class pdRecyclerAdapter extends RecyclerView.Adapter<pdRecyclerAdapter.MyViewHolder> {

    Context context;
    List<pdRecycler> pdRecyclers;

    public pdRecyclerAdapter(Context context, List<pdRecycler> pdRecyclers) {
        this.context = context;
        this.pdRecyclers = pdRecyclers;
    }

    @NonNull
    @Override
    public pdRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_hr_rows, parent, false);
        return new pdRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull pdRecyclerAdapter.MyViewHolder holder, int position) {
        pdRecycler item = pdRecyclers.get(position);
        holder.pdView.setText(item.getpName());
        holder.diagnosisView.setText(item.getDiagnose());
        holder.prescriptionView.setText(item.getPrescription());
    }

    @Override
    public int getItemCount() {
        return pdRecyclers.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView pdView, diagnosisView, prescriptionView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            pdView = itemView.findViewById(R.id.pdView);
            diagnosisView = itemView.findViewById(R.id.diagnosisView);
            prescriptionView = itemView.findViewById(R.id.prescriptionView);


        }
    }
}
