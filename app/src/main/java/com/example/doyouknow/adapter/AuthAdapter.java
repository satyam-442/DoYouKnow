package com.example.doyouknow.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.doyouknow.fragments.SigninFragment;
import com.example.doyouknow.fragments.SingupFragment;

public class AuthAdapter extends FragmentStateAdapter {

    public AuthAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new SigninFragment();
            case 1:
                return new SingupFragment();
        }

        return new SigninFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
