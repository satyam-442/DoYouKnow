package com.example.doyouknow.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doyouknow.HomeActivity;
import com.example.doyouknow.R;
public class QuizMainFragment extends Fragment {

    TextView technologyTV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_main, container, false);

        ((HomeActivity) getActivity()).getSupportActionBar().hide();
        ((HomeActivity) getActivity()).setDrawer_Locked();

        technologyTV = view.findViewById(R.id.technologyTV);
        technologyTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayQuizFragment factsPage = new PlayQuizFragment();
                //getParentFragmentManager().beginTransaction().replace(R.id.container, factsPage).commit();
                ((HomeActivity) getActivity()).replaceFragment(factsPage, "fragmentB");
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((HomeActivity) getActivity()).setDrawer_UnLocked();
    }
}