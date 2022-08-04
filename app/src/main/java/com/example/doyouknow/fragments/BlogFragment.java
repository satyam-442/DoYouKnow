package com.example.doyouknow.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doyouknow.R;
import com.example.doyouknow.modal.Blog;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.ms.square.android.expandabletextview.ExpandableTextView;

public class BlogFragment extends Fragment {

    ProgressDialog dialog;
    RecyclerView blogLists;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference factsRef;
    String category;
    TextView noBlogText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blog, container, false);

        dialog = new ProgressDialog(getContext());

        category = this.getArguments().getString("cat");

        factsRef = db.collection("Blog");

        noBlogText = view.findViewById(R.id.noBlogText);
        blogLists = view.findViewById(R.id.blogLists);
        blogLists.setHasFixedSize(true);
        blogLists.setLayoutManager(new LinearLayoutManager(getContext()));
        startListen();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        dialog.setMessage("please wait");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        startListen();
    }

    private void startListen() {
        //Query query = factsRef.whereEqualTo("Category","TECHNOLOGY");
        factsRef.whereEqualTo("Category",category).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot snapshot) {
                if (snapshot.isEmpty()){
                    noBlogText.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                }
                else {
                    Query query = factsRef.whereEqualTo("Category",category).orderBy("Time", Query.Direction.ASCENDING);
                    FirestoreRecyclerOptions<Blog> options = new FirestoreRecyclerOptions.Builder<Blog>().setQuery(query, Blog.class).build();
                    FirestoreRecyclerAdapter<Blog, BlogMainHolder> fireAdapter = new FirestoreRecyclerAdapter<Blog, BlogMainHolder>(options) {
                        @Override
                        protected void onBindViewHolder(@NonNull BlogMainHolder holder, int position, @NonNull Blog model) {
                            holder.blogHeadingCard.setText(model.getHeading()+"?");
                            //holder.factCategory.setText(model.getCategory());
                            holder.blogDateCard.setText("Date: "+model.getDate());
                            holder.blogTimeCard.setText("Time: "+model.getTime());
                            holder.blogWriterCard.setText("- by "+model.getWriter());
                            holder.expandable_text_view.setText(model.getBlog());
                            dialog.dismiss();
                        }

                        @NonNull
                        @Override
                        public BlogMainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_card,parent,false);
                            return new BlogMainHolder(view);
                        }
                    };
                    blogLists.setAdapter(fireAdapter);
                    fireAdapter.startListening();
                }
            }
        });

    }

    class BlogMainHolder extends RecyclerView.ViewHolder{

        TextView blogHeadingCard, blogWriterCard, blogDateCard, blogTimeCard;
        ExpandableTextView expandable_text_view;

        public BlogMainHolder(@NonNull View itemView) {
            super(itemView);
            blogHeadingCard = itemView.findViewById(R.id.blogHeadingCard);
            blogWriterCard = itemView.findViewById(R.id.blogWriterCard);
            blogDateCard = itemView.findViewById(R.id.blogDateCard);
            blogTimeCard = itemView.findViewById(R.id.blogTimeCard);
            expandable_text_view = itemView.findViewById(R.id.expandable_text_view);
        }
    }

}