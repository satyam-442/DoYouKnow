package com.example.doyouknow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.doyouknow.adapter.VPAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class BlogPageActivity extends AppCompatActivity {

    TabLayout blogTabLayout;
    ViewPager2 viewPager;
    VPAdapter adapter;
    String[] tabName = {"Technology", "Nature", "Science", "Animal", "Finance"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_page);

        blogTabLayout = findViewById(R.id.blogTabLayout);
        viewPager = findViewById(R.id.viewPager);
        adapter = new VPAdapter(this);

        viewPager.setAdapter(adapter);

        new TabLayoutMediator(
                blogTabLayout,
                viewPager,
                (tab, position) -> {
                    tab.setText(tabName[position]);
                }
        ).attach();
    }
}