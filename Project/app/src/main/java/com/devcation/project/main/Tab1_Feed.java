package com.devcation.project.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.devcation.project.R;

import com.devcation.project.feed.Fragment1;
import com.devcation.project.feed.Fragment2;
import com.devcation.project.feed.Fragment3;
import com.devcation.project.feed.Fragment4;
import com.google.android.material.tabs.TabLayout;

public class Tab1_Feed extends Fragment {
    Toolbar toolbar;

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Fragment4 fragment4;

    public String test;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.tab1_feed, container, false);

        toolbar = rootView.findViewById(R.id.toolbar);
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.f1_container, fragment1).commit();

        TabLayout tabs = rootView.findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("tab1"));
        tabs.addTab(tabs.newTab().setText("tab2"));
        tabs.addTab(tabs.newTab().setText("tab3"));
        tabs.addTab(tabs.newTab().setText("tab4"));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment selected = null;
                if (position == 0) {
                    selected = fragment1;

                } else if (position == 1) {
                    selected = fragment2;
                } else if (position == 2) {
                    selected = fragment3;
                }else if (position == 3) {
                    selected = fragment4;
                }
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.f1_container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        return rootView;
    }

}
