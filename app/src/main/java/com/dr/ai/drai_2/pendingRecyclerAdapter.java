package com.dr.ai.drai_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dr.ai.drai_2.db.DatabaseHandler;
import com.dr.ai.drai_2.model.User;
import com.dr.ai.drai_2.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class pendingRecyclerAdapter extends RecyclerView.Adapter<pendingRecyclerAdapter.MyViewHolder> {

    Context context;
    List<User> pendingRecyclers;
    DatabaseHandler handler;

    public pendingRecyclerAdapter(Context context, List<User> pendingRecyclers, DatabaseHandler handler) {
        this.context = context;
        this.pendingRecyclers = pendingRecyclers;
        this.handler = handler;
    }

    @NonNull
    @Override
    public pendingRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate Layout (giving a look to each row)
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_pending_rows, parent, false);
        return new pendingRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull pendingRecyclerAdapter.MyViewHolder holder, int position) {
        User item = pendingRecyclers.get(position);
        holder.idView.setText(item.getPersonal_id());
        holder.emailView.setText(item.getEmail());
        holder.nameView.setText(item.getName());
        holder.numberView.setText(item.getPhone());

        holder.imageView.post(new Runnable() {
            @Override
            public void run() {
                holder.imageView.setImageBitmap(Utils.getImage(item.getCertificate()));
            }
        });

        holder.acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.confirmDoctorApprobation(item);
            }
        });

        holder.rejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.rejectDoctorApprobation(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pendingRecyclers.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nameView, numberView, emailView, idView;
        Button acceptBtn, rejectBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameView = itemView.findViewById(R.id.nameView);
            imageView = itemView.findViewById(R.id.certificateView);
            numberView = itemView.findViewById(R.id.numberView);
            emailView = itemView.findViewById(R.id.emailView);
            idView = itemView.findViewById(R.id.idView);
            acceptBtn = itemView.findViewById(R.id.acceptBtn);
            rejectBtn = itemView.findViewById(R.id.rejectBtn);

        }
    }
}
