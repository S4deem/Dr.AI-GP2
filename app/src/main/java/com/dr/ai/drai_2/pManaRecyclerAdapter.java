package com.dr.ai.drai_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dr.ai.drai_2.model.User;

import java.util.ArrayList;
import java.util.List;

public class pManaRecyclerAdapter extends RecyclerView.Adapter<pManaRecyclerAdapter.MyViewHolder>{

    Context context;
    List<User> pManaRecyclerAdapters;

    public pManaRecyclerAdapter(Context context, List<User> pManaRecyclerAdapters){
        this.context = context;
        this.pManaRecyclerAdapters = pManaRecyclerAdapters;
    }

    @NonNull
    @Override
    public pManaRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_patient_rows, parent, false);
        return new pManaRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull pManaRecyclerAdapter.MyViewHolder holder, int position) {
        User item = pManaRecyclerAdapters.get(position);
        holder.nameViewP.setText(item.getName());
        holder.numberViewP.setText(item.getPersonal_id());
        holder.emailViewP.setText(item.getEmail());
        holder.idViewP.setText(item.getId());
        holder.cityView.setText(item.getCity());
        holder.genderView.setText(item.getGender());
    }

    @Override
    public int getItemCount() {
        return pManaRecyclerAdapters.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nameViewP, numberViewP, emailViewP, idViewP, cityView, genderView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameViewP = itemView.findViewById(R.id.nameViewP);
            numberViewP = itemView.findViewById(R.id.numberViewP);
            emailViewP = itemView.findViewById(R.id.emailViewP);
            idViewP = itemView.findViewById(R.id.idViewP);
            cityView = itemView.findViewById(R.id.cityView);
            genderView = itemView.findViewById(R.id.genderView);

        }

    }
}
