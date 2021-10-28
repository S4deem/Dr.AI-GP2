package com.dr.ai.drai_2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class pendingRecyclerAdapter extends RecyclerView.Adapter<pendingRecyclerAdapter.MyViewHolder> {

    Context context;
    ArrayList<pendingRecycler> pendingRecyclers;

    public pendingRecyclerAdapter(Context context, ArrayList<pendingRecycler> pendingRecyclers) {
        this.context = context;
        this.pendingRecyclers = pendingRecyclers;
    }
    @NonNull
    @Override
    public pendingRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull pendingRecyclerAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView nameView, numberView, emailView, idView;

         public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameView = itemView.findViewById(R.id.nameView);
            imageView = itemView.findViewById(R.id.certificateView);
            numberView = itemView.findViewById(R.id.numberView);
            emailView = itemView.findViewById(R.id.emailView);
            idView = itemView.findViewById(R.id.idView);

        }
    }
}
