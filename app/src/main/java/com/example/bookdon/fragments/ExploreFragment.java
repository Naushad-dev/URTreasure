package com.example.bookdon.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookdon.Eclass;
import com.example.bookdon.R;
import com.example.bookdon.toolBox;
import com.google.android.material.button.MaterialButton;


public class ExploreFragment extends Fragment {

   private MaterialButton notes_btn,yt_video_btn,sciCalc_btn;



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ExploreFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ExploreFragment newInstance(String param1, String param2) {
        ExploreFragment fragment = new ExploreFragment();
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
         View v=inflater.inflate(R.layout.fragment_explore, container, false);

         notes_btn=v.findViewById(R.id.notes_btn);
         yt_video_btn=v.findViewById(R.id.yt_video_btn);
         sciCalc_btn=v.findViewById(R.id.sci_Calc_btn);

         notes_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Uri uri=Uri.parse("https://t.me/compengnotes_bot");
                 startActivity(new Intent(Intent.ACTION_VIEW,uri));
             }
         });

         yt_video_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(getActivity(),Eclass.class));

             }
         });
         sciCalc_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(getActivity(), toolBox.class));


             }
         });


    return v;
    }
}