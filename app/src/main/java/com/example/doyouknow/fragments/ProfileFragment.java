package com.example.doyouknow.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.doyouknow.AuthActivity;
import com.example.doyouknow.HomeActivity;
import com.example.doyouknow.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileFragment extends Fragment {

    TextView reqBecomeBlogger, usernameProfile, userEmailProfile, emailOfUser, phoneOfUser, aboutOfUser, verifiedText;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String currentUser = mAuth.getCurrentUser().getUid();
    FirebaseUser user = mAuth.getCurrentUser();
    DocumentReference usersRef;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProgressDialog dialog;
    ImageView editProfileImg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        usersRef = db.collection("Users").document(currentUser);

        dialog = new ProgressDialog(getContext());

        usernameProfile = view.findViewById(R.id.usernameProfile);
        userEmailProfile = view.findViewById(R.id.userEmailProfile);
        emailOfUser = view.findViewById(R.id.emailOfUser);
        phoneOfUser = view.findViewById(R.id.phoneOfUser);
        aboutOfUser = view.findViewById(R.id.aboutOfUser);
        verifiedText = view.findViewById(R.id.verifiedText);

        editProfileImg = view.findViewById(R.id.editProfileImg);
        editProfileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditProfileFragment factsPage = new EditProfileFragment();
                //getParentFragmentManager().beginTransaction().replace(R.id.container, factsPage).commit();
                ((HomeActivity) getActivity()).replaceFragment(factsPage, "fragmentB");
            }
        });

        reqBecomeBlogger = view.findViewById(R.id.reqBecomeBlogger);
        reqBecomeBlogger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        if (!user.isEmailVerified()) {
            Snackbar.make(getActivity().findViewById(android.R.id.content), "E-mail not verified", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Verify", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getContext(), "Link Sent", Toast.LENGTH_SHORT).show();
                                    mAuth.signOut();
                                    Intent loginIntent = new Intent(getContext(), AuthActivity.class);
                                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(loginIntent);
                                    getActivity().finish();
                                }
                            });
                        }
                    })
                    .show();
        }else {
            verifiedText.setVisibility(View.VISIBLE);
        }

        return view;
    }

    @Override
    public void onStart() {

        super.onStart();
        getUserDetails();
    }

    private void getUserDetails() {
        dialog.show();
        usersRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot snapshot) {
                if (snapshot.exists()) {
                    String name = snapshot.getString("Name");
                    String email = snapshot.getString("Email");
                    String phone = snapshot.getString("Phone");
                    String about = snapshot.getString("About");
                    usernameProfile.setText(name);
                    userEmailProfile.setText(email);
                    emailOfUser.setText(email);
                    phoneOfUser.setText(phone);
                    aboutOfUser.setText(about);
                } else {
                    Toast.makeText(getContext(), "Error Occurred!", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
    }
}