package com.example.doyouknow.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.doyouknow.fragments.BlogFragment;

public class VPAdapter extends FragmentStateAdapter {

    public VPAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle bundle = new Bundle();
        BlogFragment blogFragment = new BlogFragment();

        switch (position){
            case 0:
                bundle.putString("cat","TECHNOLOGY");
                blogFragment.setArguments(bundle);
                return blogFragment;
                //return new TechnologyBlog();
            case 1:
                bundle.putString("cat","NATURE");
                blogFragment.setArguments(bundle);
                return blogFragment;
                //return new NatureBlog();
            case 2:
                bundle.putString("cat","SCIENCE");
                blogFragment.setArguments(bundle);
                return blogFragment;
                //return new ScienceBlog();
            case 3:
                bundle.putString("cat","ANIMAL");
                blogFragment.setArguments(bundle);
                return blogFragment;
                //return new AnimalBlog();
            case 4:
                bundle.putString("cat","FINANCE");
                blogFragment.setArguments(bundle);
                return blogFragment;
                //return new FinanceBlog();
        }
        bundle.putString("cat","TECHNOLOGY");
        blogFragment.setArguments(bundle);
        return blogFragment;
        //return new TechnologyBlog();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
