package com.example.doyouknow.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.example.doyouknow.HomeActivity;
import com.example.doyouknow.R;
public class QuoteDisplayFragment extends Fragment {
    LottieAnimationView lottieLeft, lottieRight;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quote_display, container, false);

        ((HomeActivity) getActivity()).getSupportActionBar().hide();

        lottieLeft = view.findViewById(R.id.lottieLeft);
        lottieLeft.setRepeatCount(LottieDrawable.INFINITE);

        lottieRight = view.findViewById(R.id.lottieRight);
        lottieRight.setRepeatCount(LottieDrawable.INFINITE);

        return view;
    }
}