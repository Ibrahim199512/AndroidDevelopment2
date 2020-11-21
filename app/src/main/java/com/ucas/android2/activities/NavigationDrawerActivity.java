package com.ucas.android2.activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.ucas.android2.R;
import com.ucas.android2.fragments.HomeFragment;
import com.ucas.android2.fragments.NewPostFragment;
import com.ucas.android2.fragments.NotificationsFragment;

import java.util.ArrayList;
import java.util.List;

public class NavigationDrawerActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    String CHANNEL_ID = "NEW_CHANNEL_ID5";
    ArrayList<MenuItem> menuItems = new ArrayList<>();
    int oldPostion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        drawerToggle.setDrawerIndicatorEnabled(true);
        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        nvDrawer = findViewById(R.id.nvView);
        View headerView = nvDrawer.inflateHeaderView(R.layout.nav_header);
        TextView ivHeaderPhoto = headerView.findViewById(R.id.text);
        ivHeaderPhoto.setText("Ibrahim Alwadiya");


        mDrawer = findViewById(R.id.drawer_layout);
        setupDrawerContent(nvDrawer);

        for (int i = 0; i < nvDrawer.getMenu().size(); i++) {
            menuItems.add(nvDrawer.getMenu().getItem(i));
        }

        openFragment(HomeFragment.newInstance("", ""), R.id.container);
        nvDrawer.getMenu().getItem(oldPostion).setChecked(true);
        setTitle(nvDrawer.getMenu().getItem(oldPostion).getTitle());


        createNotificationChannel();

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                selectDrawerItem(menuItem);
                return true;
            }
        });

    }

    public void selectDrawerItem(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.home:
                openFragment(HomeFragment.newInstance("", ""), R.id.container);
                createNotification("home",0);
                break;
            case R.id.new_post:
                openFragment(NewPostFragment.newInstance("", ""), R.id.container);
                createNotification("new_post",1);
                break;
            case R.id.notifications:
                openFragment(NotificationsFragment.newInstance("", ""), R.id.container);
                break;
        }

        menuItem.setChecked(true);

        nvDrawer.getMenu().getItem(oldPostion).setChecked(false);
        oldPostion = menuItems.indexOf(menuItem);
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();
    }

    public void openFragment(Fragment fragment, int container) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(container, fragment);
        transaction.commit();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this
                , mDrawer
                , toolbar
                , R.string.drawer_open
                , R.string.drawer_close);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "NEW_CHANNEL_NAME5", importance);
            channel.setDescription("DESCRIPTION");
            channel.setSound(null, null);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void createNotification(String title,int id) {
        Intent intent = new Intent(NavigationDrawerActivity.this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity
                (NavigationDrawerActivity.this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder
                (NavigationDrawerActivity.this, CHANNEL_ID)
                .setSmallIcon(R.drawable.notifications_icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.notifications_icon))
                .setContentTitle(title)
                .setContentText("textContent")
                .setContentIntent(pendingIntent)
                .setOngoing(false)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("fdsssssssssssssssssssssssssssssdsfffffffffffffffffffff"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        final Uri NOTIFICATION_SOUND = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        RingtoneManager.getRingtone(NavigationDrawerActivity.this, NOTIFICATION_SOUND).play();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(NavigationDrawerActivity.this);
        notificationManager.notify(id, builder.build());
    }
}