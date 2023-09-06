package com.example.bookdon.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;




import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.bookdon.MainActivity;
import com.example.bookdon.R;
import com.example.bookdon.myadapter;
import com.example.bookdon.posts;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.annotations.Nullable;


public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    // Initializing Classes and view here

    RecyclerView recyclerView;
    myadapter adapter;



    public HomeFragment() {
        // Required empty public constructor
    }



    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
 // calling or making instance of object here.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);

         recyclerView=v.findViewById(R.id.recview);

         recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        load();




        return v;
    }

 public void process(String s){
     FirebaseRecyclerOptions<posts> options =
             new FirebaseRecyclerOptions.Builder<posts>()
                     .setQuery(FirebaseDatabase.getInstance().getReference().child("posts").orderByChild("title").startAt(s).endAt(s+"~"), posts.class)
                     .build();
     adapter=new myadapter(options);
     adapter.startListening();
     recyclerView.setAdapter(adapter);
 }

 // recycler View code
    public void load( ){
     FirebaseRecyclerOptions<posts> options =
             new FirebaseRecyclerOptions.Builder<posts>()
                     .setQuery(FirebaseDatabase.getInstance().getReference().child("posts"), posts.class)
                     .build();
     adapter=new myadapter(options);
     recyclerView.setAdapter(adapter);
 }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        setHasOptionsMenu(true);

    }
}