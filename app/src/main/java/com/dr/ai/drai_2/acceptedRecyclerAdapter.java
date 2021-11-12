package com.dr.ai.drai_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dr.ai.drai_2.db.DatabaseHandler;
import com.dr.ai.drai_2.model.User;
import com.dr.ai.drai_2.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class acceptedRecyclerAdapter extends RecyclerView.Adapter<acceptedRecyclerAdapter.MyViewHolder> {
    Context context;
    List<User> pendingRecyclers;
    DatabaseHandler handler;

    public acceptedRecyclerAdapter(Context context, List<User> pendingRecyclers, DatabaseHandler handler) {
        this.context = context;
        this.pendingRecyclers = pendingRecyclers;
        this.handler = handler;
    }

    @NonNull
    @Override
    public acceptedRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate Layout (giving a look to each row)
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_accepted_rows, parent, false);
        return new acceptedRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull acceptedRecyclerAdapter.MyViewHolder holder, int position) {
        User item = pendingRecyclers.get(position);
        holder.idViewA.setText(item.getPersonal_id());
        holder.emailViewA.setText(item.getEmail());
        holder.nameViewA.setText(item.getName());
        holder.numberViewA.setText(item.getPhone());

        holder.imageViewA.post(new Runnable() {
            @Override
            public void run() {
                holder.imageViewA.setImageBitmap(Utils.getImage(item.getCertificate()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return pendingRecyclers.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

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
