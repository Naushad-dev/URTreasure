package com.example.bookdon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.bookdon.fragments.PostDescFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter extends FirebaseRecyclerAdapter<posts,myadapter.myviewholder>
{

    public myadapter(@NonNull FirebaseRecyclerOptions<posts> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull posts model) {
    holder.Rtitle.setText(model.getPostTitle());
        Glide.with(holder.Rimg.getContext()).load(model.getImage()).into(holder.Rimg);
        holder.Rimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             AppCompatActivity activity=(AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.MainFrame,new PostDescFragment(model.getImage(),model.getPostTitle(),model.getDescription())).addToBackStack(null).commit();
            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.postsui,parent,false);
    return new myviewholder(view);
    }

    public static class myviewholder extends RecyclerView.ViewHolder{

        ImageView Rimg;
        TextView Rtitle;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            Rimg=itemView.findViewById(R.id.Rimg);
            Rtitle=itemView.findViewById(R.id.Rtitle);


        }
    }
}
