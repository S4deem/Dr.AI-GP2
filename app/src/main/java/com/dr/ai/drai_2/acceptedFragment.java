package com.dr.ai.drai_2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dr.ai.drai_2.db.DatabaseHandler;
import com.dr.ai.drai_2.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link acceptedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class acceptedFragment extends Fragment {

    // ArrayList<acceptedRecycler> acceptedRecyclers = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView aRecyclerView;
    RecyclerView.Adapter adapter;
    List<User> list = new ArrayList<>();
    DatabaseHandler handler;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public acceptedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment acceptedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static acceptedFragment newInstance(String param1, String param2) {
        acceptedFragment fragment = new acceptedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_accepted, container, false);
        aRecyclerView = v.findViewById(R.id.aRecyclerView);
        handler = new DatabaseHandler(requireActivity());
        return v;
    }
    @Override
    public void onStart() {
        super.onStart();
        list = handler.getAllAcceptedDoctors();
        adapter = new acceptedRecyclerAdapter(requireActivity(), list, handler);
        aRecyclerView.setAdapter(adapter);
    }
}