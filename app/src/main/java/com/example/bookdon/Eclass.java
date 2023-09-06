package com.example.bookdon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Eclass extends AppCompatActivity {
    ImageButton arrow;
    LinearLayout hiddenView;
    CardView cardView;
    TextView nhitm_yt_link,nhitm_link,github_link,gfg_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eclass);

        cardView = findViewById(R.id.base_cardview);
        arrow = findViewById(R.id.arrow_button);
        hiddenView = findViewById(R.id.hidden_view);

        nhitm_yt_link=findViewById(R.id.nhitm_yt_link);
        nhitm_link=findViewById(R.id.nhitm_link);
        github_link=findViewById(R.id.github_link);
        gfg_link=findViewById(R.id.gfg_link);

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // If the CardView is already expanded, set its visibility
                //  to gone and change the expand less icon to expand more.
                if (hiddenView.getVisibility() == View.VISIBLE) {

                    // The transition of the hiddenView is carried out
                    //  by the TransitionManager class.
                    // Here we use an object of the AutoTransition
                    // Class to create a default transition.
                    TransitionManager.beginDelayedTransition(cardView,
                            new AutoTransition());
                    hiddenView.setVisibility(View.GONE);
                    arrow.setImageResource(R.drawable.ic_baseline_navigate_next_24);
                }

                // If the CardView is not expanded, set its visibility
                // to visible and change the expand more icon to expand less.
                else {

                    TransitionManager.beginDelayedTransition(cardView,
                            new AutoTransition());
                    hiddenView.setVisibility(View.VISIBLE);
                    arrow.setImageResource(R.drawable.ic_baseline_navigate_next_24);
                }
            }
        });


        nhitm_yt_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("https://www.youtube.com/channel/UCqmbFw65TSzs5bHvaL5hm_w");
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });
        nhitm_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("https://nhitm.ac.in/");
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });
        github_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("https://www.github.com");
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });
        gfg_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("https://www.geeksforgeeks.org/");
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });




    }
}