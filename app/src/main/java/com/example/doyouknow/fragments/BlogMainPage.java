package com.example.doyouknow.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doyouknow.R;
import com.example.doyouknow.adapter.VPAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Arrays;

public class BlogMainPage extends Fragment {

    TabLayout blogTabLayout;
    ViewPager2 viewPager;
    VPAdapter adapter;
    String[] tabName = {"Technology", "Nature", "Science", "Animal", "Finance"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blog_main_page, container, false);

        blogTabLayout = view.findViewById(R.id.blogTabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        adapter = new VPAdapter(getActivity());

        viewPager.setAdapter(adapter);

        new TabLayoutMediator(
                blogTabLayout,
                viewPager,
                (tab, position) -> {
                    tab.setText(tabName[position]);
                }
        ).attach();

        return view;
    }
}