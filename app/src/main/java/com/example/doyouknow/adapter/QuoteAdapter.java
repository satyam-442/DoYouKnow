package com.example.doyouknow.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doyouknow.HomeActivity;
import com.example.doyouknow.R;
import com.example.doyouknow.fragments.FactsPage;
import com.example.doyouknow.fragments.QuoteDisplayFragment;
import com.example.doyouknow.modal.Quote;

import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder> {

    final List<Quote> mList;
    final Context context;

    public QuoteAdapter(List<Quote> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public QuoteAdapter.QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuoteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.quote_layout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteAdapter.QuoteViewHolder holder, int position) {
        Quote quote = mList.get(position);
        holder.quoteName.setText(quote.getQuoteName());
        holder.quoteImage.setImageResource(quote.getQuoteImg());
        /*holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("category", "Interesting");
                QuoteDisplayFragment factsPage = new QuoteDisplayFragment();
                //factsPage.setArguments(bundle);
                //getParentFragmentManager().beginTransaction().replace(R.id.container, factsPage).commit();
                ((HomeActivity) context).replaceFragment(factsPage,"fragmentB");
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class QuoteViewHolder extends RecyclerView.ViewHolder{
        TextView quoteName;
        ImageView quoteImage;
        CardView rootLayout;
        public QuoteViewHolder(@NonNull View itemView) {
            super(itemView);

            quoteName = itemView.findViewById(R.id.quoteName);
            quoteImage = itemView.findViewById(R.id.quoteImage);
            rootLayout = itemView.findViewById(R.id.rootLayout);
        }
    }
}
