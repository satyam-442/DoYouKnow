package com.example.doyouknow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.doyouknow.adapter.AuthAdapter;
import com.example.doyouknow.adapter.VPAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Collections;
import java.util.List;

public class AuthActivity extends AppCompatActivity {

    TabLayout authTabLayout;
    ViewPager2 viewPager;
    AuthAdapter adapter;
    String[] tabName = {"Sign In", "Sign Up"};
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        authTabLayout = findViewById(R.id.authTabLayout);
        viewPager = findViewById(R.id.viewPager);
        adapter = new AuthAdapter(this);

        viewPager.setAdapter(adapter);

        new TabLayoutMediator(
                authTabLayout,
                viewPager,
                (tab, position) -> {
                    tab.setText(tabName[position]);
                }
        ).attach();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // User is signed in
            Intent mainIntent = new Intent(AuthActivity.this,HomeActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(mainIntent);
            finish();
        }
    }
}