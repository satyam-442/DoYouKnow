package com.example.doyouknow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doyouknow.modal.Facts;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class FactsPageActivity extends AppCompatActivity {

    String category;
    TextView headingText;
    ProgressDialog dialog;
    RecyclerView factLists;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference factsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts_page);

        dialog = new ProgressDialog(this);

        category = getIntent().getStringExtra("category");
        headingText = findViewById(R.id.headingText);
        headingText.setText(category+" Facts");

        factsRef = db.collection(category);

        factLists = findViewById(R.id.factLists);
        factLists.setHasFixedSize(true);
        factLists.setLayoutManager(new LinearLayoutManager(this));
        startListen();
    }

    @Override
    protected void onStart() {
        super.onStart();
        dialog.setMessage("please wait");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        startListen();
    }

    private void startListen() {
        Query query = factsRef.orderBy("Time", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Facts> options = new FirestoreRecyclerOptions.Builder<Facts>().setQuery(query, Facts.class).build();

        FirestoreRecyclerAdapter<Facts, FactHolder> fireAdapter = new FirestoreRecyclerAdapter<Facts, FactHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FactHolder holder, int position, @NonNull Facts model) {
                holder.factContent.setText(model.getContent());
                //holder.factCategory.setText(model.getCategory());
                holder.factDate.setText("Date: "+model.getDate());
                holder.factTime.setText("Time: "+model.getTime());
                /*holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), ViewBuildingProfileActivity.class);
                        intent.putExtra("bldgID",model.getBuildingID());
                        startActivity(intent);
                    }
                });*/
                dialog.dismiss();
            }

            @NonNull
            @Override
            public FactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.facts_card,parent,false);
                return new FactHolder(view);
            }
        };
        factLists.setAdapter(fireAdapter);
        fireAdapter.startListening();
    }

    class FactHolder extends RecyclerView.ViewHolder{

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