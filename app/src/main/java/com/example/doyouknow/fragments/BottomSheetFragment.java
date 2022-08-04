package com.example.doyouknow.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doyouknow.R;
import com.example.doyouknow.adapter.AvatarAdapter;
import com.example.doyouknow.modal.Avatar;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    RecyclerView avatarGridRec;
    List<Avatar> aData;
    AvatarAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        avatarGridRec = view.findViewById(R.id.avatarGridRec);
        aData = new ArrayList<>();
        aData.add(new Avatar(R.drawable.animal));
        aData.add(new Avatar(R.drawable.nature));
        aData.add(new Avatar(R.drawable.science));
        aData.add(new Avatar(R.drawable.technology));
        aData.add(new Avatar(R.drawable.world_culture));
        aData.add(new Avatar(R.drawable.funny));
        aData.add(new Avatar(R.drawable.popular));
        aData.add(new Avatar(R.drawable.animal));
        aData.add(new Avatar(R.drawable.nature));
        aData.add(new Avatar(R.drawable.science));
        aData.add(new Avatar(R.drawable.technology));
        aData.add(new Avatar(R.drawable.world_culture));
        aData.add(new Avatar(R.drawable.funny));
        aData.add(new Avatar(R.drawable.popular));

        adapter = new AvatarAdapter(aData);
        avatarGridRec.setLayoutManager(new GridLayoutManager(getActivity(),3));
        avatarGridRec.setAdapter(adapter);
    }
}