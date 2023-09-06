package com.example.bookdon.fragments;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bookdon.R;
import com.example.bookdon.login;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class ProfileFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
   Button logout;
   TextView user_name,college_name;
   FirebaseAuth mAuth;
   FirebaseDatabase firebaseDatabase;
   FirebaseUser user;
  DatabaseReference databaseReference;



    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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

         View v=inflater.inflate(R.layout.fragment_profile, container, false);
            // Instances of all objects
         mAuth=FirebaseAuth.getInstance();
         firebaseDatabase=FirebaseDatabase.getInstance();
         databaseReference=firebaseDatabase.getReference("users");
         user=mAuth.getCurrentUser();


         logout=v.findViewById(R.id.logout);
         user_name=v.findViewById(R.id.user_name);
         college_name=v.findViewById(R.id.college_name);

        Query query=databaseReference.orderByChild("email").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    String Name=""+ ds.child("name").getValue();
                    String Email=""+ds.child("email").getValue();

                    user_name.setText(Name);
                    college_name.setText(Email);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        //listner for logout button
         logout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 FirebaseAuth.getInstance().signOut();
                 startActivity(new Intent(getActivity(), login.class));
             }
         });
    return v;
    }
}