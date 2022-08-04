package com.example.doyouknow.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.doyouknow.R;
import com.example.doyouknow.modal.Avatar;

import java.util.List;

public class AvatarAdapter extends RecyclerView.Adapter<AvatarAdapter.AdapterViewHolder> {

    List<Avatar> aData;

    public AvatarAdapter(List<Avatar> aData) {
        this.aData = aData;
    }

    @NonNull
    @Override
    public AvatarAdapter.AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bottom_sheet, parent,false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load(aData.get(position).getImage()).into(holder.demo_tv);
    }

    @Override
    public int getItemCount() {
        return aData.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView demo_tv;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            demo_tv = itemView.findViewById(R.id.demo_tv);
        }
    }
}
