package com.example.doyouknow.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.doyouknow.HomeActivity;
import com.example.doyouknow.R;
import com.example.doyouknow.adapter.QuoteAdapter;
import com.example.doyouknow.modal.Quote;

import java.util.ArrayList;
import java.util.List;

public class QuotesFragment extends Fragment {

    RelativeLayout attitude, motivated, success, love, lonely, heartBroken;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quotes, container, false);

        attitude = view.findViewById(R.id.attitude);
        motivated = view.findViewById(R.id.motivated);
        success = view.findViewById(R.id.success);
        love = view.findViewById(R.id.love);
        lonely = view.findViewById(R.id.lonely);
        heartBroken = view.findViewById(R.id.heartBroken);

        attitude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("quote", "Attitude");
                QuoteDisplayFragment factsPage = new QuoteDisplayFragment();
                factsPage.setArguments(bundle);
                //getParentFragmentManager().beginTransaction().replace(R.id.container, factsPage).commit();
                ((HomeActivity) getActivity()).replaceFragment(factsPage,"fragmentB");
            }
        });

        return view;
    }
}