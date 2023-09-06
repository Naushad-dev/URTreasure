package com.example.bookdon.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bookdon.R;
import com.example.bookdon.posts;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.util.Date;


public class uploadFrag extends Fragment {

    Button upload;
    FloatingActionButton select;
    TextInputEditText title,description,college;
    ImageView image;
    Uri uri;

   ProgressDialog dialog;

 FirebaseAuth auth;
 FirebaseDatabase database;
 FirebaseStorage storage;



    public uploadFrag() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storage=FirebaseStorage.getInstance();
        database=FirebaseDatabase.getInstance();
        dialog=new ProgressDialog(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_upload, container, false);
        upload=v.findViewById(R.id.upload);
        select=v.findViewById(R.id.selectbtn);
        title=v.findViewById(R.id.utitle);

        description=v.findViewById(R.id.description);
        image=v.findViewById(R.id.Rimg);

        dialog.setProgress(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Please Wait Uploading");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(uploadFrag.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

            upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    String Title=title.getText().toString();
                    String desc=description.getText().toString();

                    dialog.show();

                    StorageReference reference=storage.getReference().child("Posts").child(FirebaseAuth.getInstance().getUid()).child(new Date().getTime()+"");
                    reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                   posts Post=new posts();
                                   Post.setImage(uri.toString());
                                   Post.setPostTitle(Title);

                                   Post.setDescription(desc);
                                   Post.setPostedBy(FirebaseAuth.getInstance().getUid());
                                   Post.setPostedAt(new Date().getTime());

                                   database.getReference().child("posts").push().setValue(Post).addOnSuccessListener(new OnSuccessListener<Void>() {
                                       @Override
                                       public void onSuccess(Void unused) {
                                           dialog.dismiss();
                                           title.setText("");
                                           description.setText("");
                                           Toast.makeText(getContext(), "Uploades successfully", Toast.LENGTH_SHORT).show();
                                       }
                                   });

                                }
                            });

                        }
                    });

                }
            });














        return  v;





    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        assert data != null;
        uri=data.getData();
        image.setImageURI(uri);
    }
}


