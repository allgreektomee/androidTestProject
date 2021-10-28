package com.devcation.project.main;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.devcation.project.R;
import com.devcation.project.post.PostDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainBottomActivity extends AppCompatActivity {

    Toolbar toolbar;

    Tab1_Feed feed;
    Tab2_Search search;
    Tab3_Post post;
    Tab4_Like like;
    Tab5_Profile profile;


    PostDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainbottom);

        feed = new Tab1_Feed();
        search = new Tab2_Search();
        post = new Tab3_Post();
        like = new Tab4_Like();
        profile = new Tab5_Profile();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, feed).commit();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_bottom_1:

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, feed).commit();

                        return true;
                    case R.id.menu_bottom_2:

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, search).commit();

                        return true;
                    case R.id.menu_bottom_3:

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, post).commit();

                        return true;
                    case R.id.menu_bottom_4:

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, like).commit();

                        return true;

                    case R.id.menu_bottom_5:

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, profile).commit();

                        return true;
                }

                return false;
            }
        });


        if (database != null) {
            database.close();
            database = null;
        }

        database = PostDatabase.getInstance(this);

        boolean isOpen = database.open();
        if (isOpen) {
            Log.d("PostDatabase", "database is open.");
        } else {
            Log.d("PostDatabase", "database is not open.");
        }
    }
}