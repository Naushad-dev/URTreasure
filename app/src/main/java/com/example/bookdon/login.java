package com.example.bookdon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bookdon.databinding.ActivityLoginBinding;
import com.example.bookdon.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class login extends AppCompatActivity {
 ActivityLoginBinding binding;
 FirebaseAuth auth;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
        dialog.setMessage("Logging in...");
        if(auth.getCurrentUser() != null) {
            startActivity(new Intent(login.this, MainActivity.class));
            finish();
        }
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usname, uspassword, usemail;
                usemail = binding.email.getEditText().getText().toString();
                uspassword = binding.password.getEditText().getText().toString();

                    dialog.show();
                    auth.signInWithEmailAndPassword(usemail, uspassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            dialog.dismiss();
                            if (task.isSuccessful()) {
                                startActivity(new Intent(login.this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(login.this, (task.getException()).getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


            }
        });
        binding.newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, register.class));
            }
        });



    }
}