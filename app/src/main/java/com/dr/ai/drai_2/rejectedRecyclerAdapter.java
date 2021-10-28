package com.dr.ai.drai_2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class rejectedRecyclerAdapter extends RecyclerView.Adapter<rejectedRecyclerAdapter.MyViewHolder> {

    Context context;
    ArrayList<rejectedRecycler> rejectedRecyclers;

    public rejectedRecyclerAdapter(Context context, ArrayList<rejectedRecycler> rejectedRecyclers) {
        this.context = context;
        this.rejectedRecyclers = rejectedRecyclers;
    }

    @NonNull
    @Override
    public rejectedRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull rejectedRecyclerAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewR;
        TextView nameViewR, numberViewR, emailViewR, idViewR;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameViewR = itemView.findViewById(R.id.nameViewR);
            imageViewR = itemView.findViewById(R.id.certificateViewR);
            numberViewR = itemView.findViewById(R.id.numberViewR);
            emailViewR = itemView.findViewById(R.id.emailViewR);
            idViewR = itemView.findViewById(R.id.idViewR);
        }
    }
}
