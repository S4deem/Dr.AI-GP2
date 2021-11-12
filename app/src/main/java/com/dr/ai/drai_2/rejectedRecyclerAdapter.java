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

public class rejectedRecyclerAdapter extends RecyclerView.Adapter<rejectedRecyclerAdapter.MyViewHolder> {

    Context context;
    List<User> pendingRecyclers;
    DatabaseHandler handler;

    public rejectedRecyclerAdapter(Context context, List<User> rejectedRecyclers, DatabaseHandler handler) {
        this.context = context;
        this.pendingRecyclers = rejectedRecyclers;
        this.handler = handler;
    }

    @NonNull
    @Override
    public rejectedRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate Layout (giving a look to each row)
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_rejected_rows, parent, false);
        return new rejectedRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull rejectedRecyclerAdapter.MyViewHolder holder, int position) {
        User item = pendingRecyclers.get(position);
        holder.idViewR.setText(item.getPersonal_id());
        holder.emailViewR.setText(item.getEmail());
        holder.nameViewR.setText(item.getName());
        holder.numberViewR.setText(item.getPhone());

        holder.imageViewR.post(new Runnable() {
            @Override
            public void run() {
                holder.imageViewR.setImageBitmap(Utils.getImage(item.getCertificate()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return pendingRecyclers.size();
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
