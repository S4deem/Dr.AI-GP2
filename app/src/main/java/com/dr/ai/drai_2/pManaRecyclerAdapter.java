package com.dr.ai.drai_2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class pManaRecyclerAdapter extends RecyclerView.Adapter<pManaRecyclerAdapter.MyViewHolder>{

    Context context;
    ArrayList<pManaRecyclerAdapter> pManaRecyclerAdapters;

    public pManaRecyclerAdapter(Context context, ArrayList<pManaRecyclerAdapter> pManaRecyclerAdapters){
        this.context = context;
        this.pManaRecyclerAdapters = pManaRecyclerAdapters;
    }

    @NonNull
    @Override
    public pManaRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull pManaRecyclerAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

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
