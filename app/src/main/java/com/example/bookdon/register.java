package com.example.bookdon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.bookdon.databinding.ActivityMainBinding;
import com.example.bookdon.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class register extends AppCompatActivity {
    ActivityRegisterBinding binding;
private FirebaseAuth auth;
      FirebaseFirestore database;
      FirebaseDatabase mdatabase;
    ProgressDialog dialog;
    TextInputLayout branch_layout,year_layout;
    AutoCompleteTextView branch_input,yearinput;
    String select_branch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        DatabaseReference dref=FirebaseDatabase.getInstance().getReference();
        database=FirebaseFirestore.getInstance();
        dialog = new ProgressDialog(this);
        dialog.setMessage("We're creating new account...");
       branch_layout=findViewById(R.id.branch_layout);
       branch_input=findViewById(R.id.branchinput);
       year_layout=findViewById(R.id.year_layout);
       yearinput=findViewById(R.id.yearinput);

       String[] Branch={"AIDS","CS","Mech","Civil"};
        ArrayAdapter<String> branch= new ArrayAdapter<>(this,R.layout.branch_dropdown,Branch);
        branch_input.setAdapter(branch);

        String[] year={"FE","SE","TE","Final"};

        ArrayAdapter<String> Year= new ArrayAdapter<>(this,R.layout.year_dropdown,year);
        yearinput.setAdapter(Year);




        binding.register.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(binding.name.getEditText()==null || binding.email.getEditText()==null || binding.password.getEditText()==null){
                  Toast.makeText(getApplicationContext(), "Please Fill All Fields", Toast.LENGTH_SHORT).show();
              }else {
                  String usname, uspassword, usemail, branch, slyear;
                  usname = binding.name.getEditText().getText().toString();
                  usemail = binding.email.getEditText().getText().toString();
                  uspassword = binding.password.getEditText().getText().toString();
                  branch = branch_input.getText().toString();
                  slyear = yearinput.getText().toString();
                  final user User = new user(usname, usemail, uspassword, branch, slyear);
                  dialog.show();
                  auth.createUserWithEmailAndPassword(usemail, uspassword)
                          .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                              @Override
                              public void onComplete(@NonNull Task<AuthResult> task) {
                                  if (task.isSuccessful()) {
                                      dref.child("users").push().setValue(User);
                                      String uid = task.getResult().getUser().getUid();

                                      database
                                              .collection("users")
                                              .document(uid)
                                              .set(User).addOnCompleteListener(new OnCompleteListener<Void>() {
                                          @Override
                                          public void onComplete(@NonNull Task<Void> task) {
                                              if (task.isSuccessful()) {

                                                  startActivity(new Intent(register.this, MainActivity.class));
                                                  finish();
                                              } else {
                                                  Toast.makeText(register.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                              }

                                          }
                                      });
                                      Toast.makeText(register.this, "success", Toast.LENGTH_SHORT).show();
//                                 startActivity(new Intent(register.this,MainActivity.class));
//                                 finish();
                                  } else {
                                      dialog.dismiss();
                                      Toast.makeText(register.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                  }
                              }
                          });

              }
          }
      });
    }
}