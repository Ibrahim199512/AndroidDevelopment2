package com.ucas.android2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.ucas.android2.R;
import com.ucas.android2.fragments.HomeFragment;
import com.ucas.android2.fragments.NewPostFragment;

public class FragmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);
        openFragment( HomeFragment.newInstance("",""), R.id.container1);
        openFragment( NewPostFragment.newInstance("",""), R.id.container2);

        findViewById(R.id.remove_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFragment(new HomeFragment());
            }
        });
    }


    public void openFragment(Fragment fragment, int container) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void removeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        getSupportFragmentManager().popBackStack();
    }
}