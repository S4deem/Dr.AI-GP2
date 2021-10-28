package com.dr.ai.drai_2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class acceptedRecyclerAdapter extends RecyclerView.Adapter<acceptedRecyclerAdapter.MyViewHolder> {
    Context context;
    ArrayList<acceptedRecycler> acceptedRecyclers;

    public acceptedRecyclerAdapter(Context context, ArrayList<acceptedRecycler> acceptedRecyclers) {
        this.context = context;
        this.acceptedRecyclers = acceptedRecyclers;
    }

    @NonNull
    @Override
    public acceptedRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull acceptedRecyclerAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewA;
        TextView nameViewA, numberViewA, emailViewA, idViewA;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameViewA = itemView.findViewById(R.id.nameViewA);
            imageViewA = itemView.findViewById(R.id.certificateViewA);
            numberViewA = itemView.findViewById(R.id.numberViewA);
            emailViewA = itemView.findViewById(R.id.emailViewA);
            idViewA = itemView.findViewById(R.id.idViewA);
        }
    }
}
