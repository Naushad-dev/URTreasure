package com.example.bookdon.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bookdon.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class PostDescFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String image,title,descrip;
    TextView userpostProfile_Name,userpostProfile_branch,userpostProfile_year;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser user;
    DatabaseReference databaseReference;

    public PostDescFragment() {

    }
    public PostDescFragment(String image,String title,String descrip) {
        this.image=image;
        this.title=title;
        this.descrip=descrip;


    }


    // TODO: Rename and change types and number of parameters
    public static PostDescFragment newInstance(String param1, String param2) {
        PostDescFragment fragment = new PostDescFragment();
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
        View v= inflater.inflate(R.layout.fragment_poost_desc, container, false);
        userpostProfile_Name=v.findViewById(R.id.Rtitle);
        userpostProfile_branch=v.findViewById(R.id.userpostProfile_branch);
        userpostProfile_year=v.findViewById(R.id.userpostProfile_year);
        mAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("users");
        user=mAuth.getCurrentUser();

        Query query=databaseReference.orderByChild("email").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    String User_Name=""+ ds.child("name").getValue();
                    String User_branch=""+ds.child("branch").getValue();
                    String User_year=""+ds.child("year").getValue();
                    userpostProfile_Name.setText(User_Name);
                    userpostProfile_branch.setText(User_branch);
                    userpostProfile_year.setText(User_year);



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        ImageView holderimg=v.findViewById(R.id.holderimg);
        TextView holder_title=v.findViewById(R.id.holder_title);
        TextView holder_desc=v.findViewById(R.id.holder_desc);
        Glide.with(getContext()).load(image).into(holderimg);
        holder_title.setText(title);
        holder_desc.setText(descrip);



    return v;
    }

    public void onBackPressed()
    {
        AppCompatActivity activity=(AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.MainFrame,new HomeFragment()).addToBackStack(null).commit();

    }
}