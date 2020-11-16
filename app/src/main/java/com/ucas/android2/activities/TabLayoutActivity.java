package com.ucas.android2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.ucas.android2.EventBus.MyEvent;
import com.ucas.android2.R;
import com.ucas.android2.adapters.ViewPagerAdapter;
import com.ucas.android2.fragments.HomeFragment;
import com.ucas.android2.fragments.NewPostFragment;
import com.ucas.android2.fragments.NotificationsFragment;
import com.ucas.android2.modules.MyTab;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class TabLayoutActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    ArrayList<MyTab> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayout);

        fragments = new ArrayList<>();
        fragments.add(new MyTab("Home", HomeFragment.newInstance("", "")));
        fragments.add(new MyTab("New Post", NewPostFragment.newInstance("", "")));
        fragments.add(new MyTab("Notifications", NotificationsFragment.newInstance("", "")));

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setOffscreenPageLimit(1);
        setupTabIcons();
//        tabLayout.getTabAt(1).select();
    }

    private void setupTabIcons() {
        int[] tabIcons = {R.drawable.home, R.drawable.new_post_icon, R.drawable.notifications_icon};
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(MyEvent event) {
        if (event.getType() == 0) {
            //Notifications Number
            Log.e("onEvent,Notifications", event.getNumber() + "");
            Toast.makeText(this, "Notifications Number = " + event.getNumber(), Toast.LENGTH_SHORT).show();
        } else if (event.getType() == 1) {
            //Cart Number
            Log.e("onEvent,Cart", event.getNumber() + "");
            Toast.makeText(this, "Cart Number = " + event.getNumber(), Toast.LENGTH_SHORT).show();
        }

    }
}