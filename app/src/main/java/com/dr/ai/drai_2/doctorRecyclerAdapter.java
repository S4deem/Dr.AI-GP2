package com.dr.ai.drai_2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class doctorRecyclerAdapter extends RecyclerView.Adapter<doctorRecyclerAdapter.MyViewHolder> {

    Context context;
    ArrayList<doctorRecycler> doctorRecyclers;

    public doctorRecyclerAdapter(Context context, ArrayList<doctorRecycler> doctorRecyclers) {
        this.context = context;
        this.doctorRecyclers = doctorRecyclers;
    }
    @NonNull
    @Override
    public doctorRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate Layout (giving a look to each row)
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull doctorRecyclerAdapter.MyViewHolder holder, int position) {
        //assign values to the views created in recycler_view_row
        //based on the position of the recycler view
    }

    @Override
    public int getItemCount() {
        // number of items displayed
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
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
