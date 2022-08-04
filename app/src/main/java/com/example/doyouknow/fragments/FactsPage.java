package com.example.doyouknow.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doyouknow.HomeActivity;
import com.example.doyouknow.R;
import com.example.doyouknow.modal.Facts;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class FactsPage extends Fragment {

    String category;
    TextView headingText, noFactText;
    ProgressDialog dialog;
    RecyclerView factLists;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference factsRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_facts_page, container, false);
        category = this.getArguments().getString("category");
        noFactText = view.findViewById(R.id.noFactText);
        headingText = view.findViewById(R.id.headingText);
        headingText.setText(category + " Facts");

        //getActivity().setTitle(category+" Facts");
        //((HomeActivity) getActivity()).getSupportActionBar().setTitle(category+" Facts");
        ((HomeActivity) getActivity()).getSupportActionBar().hide();

        dialog = new ProgressDialog(getContext());

        factsRef = db.collection("Facts");

        factLists = view.findViewById(R.id.factLists);
        factLists.setHasFixedSize(true);
        factLists.setLayoutManager(new LinearLayoutManager(getContext()));
        //startListen();
        /*ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(factLists);*/

        return view;
    }

    /*ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getBindingAdapterPosition();
            switch (direction) {
                case ItemTouchHelper.LEFT:
                    Toast.makeText(getContext(), "SAVE CONTENT", Toast.LENGTH_SHORT).show();
                    break;
                case ItemTouchHelper.RIGHT:
                    Toast.makeText(getContext(), "SHARE CONTENT", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };*/

    @Override
    public void onStart() {
        super.onStart();
        dialog.setMessage("please wait");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        startListen();
    }

    private void startListen() {
        factsRef.whereEqualTo("Category", category).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot snapshot) {
                if (snapshot.isEmpty()) {
                    noFactText.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                } else {
                    Query query = factsRef.whereEqualTo("Category", category).orderBy("Time", Query.Direction.ASCENDING);
                    FirestoreRecyclerOptions<Facts> options = new FirestoreRecyclerOptions.Builder<Facts>().setQuery(query, Facts.class).build();
                    FirestoreRecyclerAdapter<Facts, FactHolder> fireAdapter = new FirestoreRecyclerAdapter<Facts, FactHolder>(options) {
                        @Override
                        protected void onBindViewHolder(@NonNull FactHolder holder, int position, @NonNull Facts model) {
                            holder.factContent.setText(model.getContent());
                            //holder.factCategory.setText(model.getCategory());
                            holder.factDate.setText("Date: " + model.getDate());
                            holder.factTime.setText("Time: " + model.getTime());
                            /*holder.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //((HomeActivity) getActivity()).replaceFragment(new AnimalBlog(), "fragmentC");
                                }
                            });*/
                            dialog.dismiss();
                        }

                        @NonNull
                        @Override
                        public FactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.facts_card, parent, false);
                            return new FactHolder(view);
                        }
                    };
                    factLists.setAdapter(fireAdapter);
                    fireAdapter.startListening();
                }
            }
        });
    }

    class FactHolder extends RecyclerView.ViewHolder {

        TextView factContent, factCategory, factDate, factTime;

        public FactHolder(@NonNull View itemView) {
            super(itemView);
            factContent = itemView.findViewById(R.id.factContent);
            //factCategory = itemView.findViewById(R.id.factCategory);
            factDate = itemView.findViewById(R.id.factDate);
            factTime = itemView.findViewById(R.id.factTime);
        }
    }
}