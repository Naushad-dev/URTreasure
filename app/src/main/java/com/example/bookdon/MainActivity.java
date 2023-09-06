package com.example.bookdon;

import static com.example.bookdon.R.id.MainFrame;
import static com.example.bookdon.R.id.home_tab;
import static com.example.bookdon.R.id.searchRv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.bookdon.databinding.ActivityMainBinding;
import com.example.bookdon.fragments.ExploreFragment;
import com.example.bookdon.fragments.HomeFragment;
import com.example.bookdon.fragments.ProfileFragment;
import com.example.bookdon.fragments.uploadFrag;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FragmentTransaction homeTrans=getSupportFragmentManager().beginTransaction();
        homeTrans.replace(MainFrame,new HomeFragment());
        homeTrans.commit();
        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction(); // for making code optimise
                int id = item.getItemId();
                switch(id){
                    //check id
                    case home_tab:
                        //FragmentTransaction homeTrans=getSupportFragmentManager().beginTransaction();
                        transaction.replace(MainFrame,new HomeFragment());
                        //homeTrans.commit();
                       // Toast.makeText(MainActivity.this, "This is Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.upload_tab:
                       // FragmentTransaction uploadTrans=getSupportFragmentManager().beginTransaction();
                        transaction.replace(MainFrame,new uploadFrag());
                        //uploadTrans.commit();
                        //Toast.makeText(MainActivity.this, "This is Upload", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.explore_tab:
                        FragmentTransaction exploreTrans=getSupportFragmentManager().beginTransaction();
                        transaction.replace(MainFrame,new ExploreFragment());
                        //exploreTrans.commit();
                       // Toast.makeText(MainActivity.this, "This is Explore", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.profile_tab:
                        FragmentTransaction profileTrans=getSupportFragmentManager().beginTransaction();
                        transaction.replace(MainFrame,new ProfileFragment());
                        //profileTrans.commit();
                       // Toast.makeText(MainActivity.this, "This is Profile", Toast.LENGTH_SHORT).show();
                        break;
                }
                transaction.commit();
                return true;

            }
        });


    }
}
