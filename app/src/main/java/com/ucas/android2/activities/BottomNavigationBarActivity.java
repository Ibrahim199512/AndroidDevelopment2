package com.ucas.android2.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ucas.android2.R;
import com.ucas.android2.fragments.HomeFragment;
import com.ucas.android2.fragments.NewPostFragment;
import com.ucas.android2.fragments.NotificationsFragment;

public class BottomNavigationBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_bar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        openFragment(HomeFragment.newInstance("", ""), R.id.container);
                        break;
                    case R.id.new_post:
                        openFragment(NewPostFragment.newInstance("", ""), R.id.container);
                        break;
                    case R.id.notifications:
                        openFragment(NotificationsFragment.newInstance("", ""), R.id.container);
                        break;
                }
                return true;
            }
        });

        openFragment(HomeFragment.newInstance("", ""), R.id.container);
    }


    public void openFragment(Fragment fragment, int container) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(container, fragment);
//        transaction.addToBackStack(null);
        transaction.commit();
    }
}