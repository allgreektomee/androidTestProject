package com.devcation.project.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.devcation.project.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainBottomActivity extends AppCompatActivity {

    Toolbar toolbar;

    f1_feed f1Feed;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Fragment4 fragment4;
    Fragment5 fragment5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainbottom);

        f1Feed = new f1_feed();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        fragment5 = new Fragment5();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, f1Feed).commit();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_bottom_1:

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, f1Feed).commit();

                        return true;
                    case R.id.menu_bottom_2:

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment2).commit();

                        return true;
                    case R.id.menu_bottom_3:

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment3).commit();

                        return true;
                    case R.id.menu_bottom_4:

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment4).commit();

                        return true;

                    case R.id.menu_bottom_5:

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment5).commit();

                        return true;
                }

                return false;
            }
        });
    }
}