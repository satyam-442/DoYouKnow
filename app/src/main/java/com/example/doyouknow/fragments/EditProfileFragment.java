package com.example.doyouknow.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.doyouknow.HomeActivity;
import com.example.doyouknow.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditProfileFragment extends Fragment {

    ImageView editProfilePicture;
    TextInputLayout nameEdit, phoneEdit, emailEdit, aboutEdit;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String currentUser = mAuth.getCurrentUser().getUid();
    DocumentReference usersRef;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProgressDialog dialog;
    CardView editBtnCard;
    //Dialog myDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        usersRef = db.collection("Users").document(currentUser);
        dialog = new ProgressDialog(getContext());

        nameEdit = view.findViewById(R.id.nameEdit);

        phoneEdit = view.findViewById(R.id.phoneEdit);
        emailEdit = view.findViewById(R.id.emailEdit);
        aboutEdit = view.findViewById(R.id.aboutEdit);
        editBtnCard = view.findViewById(R.id.editBtnCard);

        editProfilePicture = view.findViewById(R.id.editProfilePicture);
        ((HomeActivity) getActivity()).getSupportActionBar().hide();
        ((HomeActivity) getActivity()).setDrawer_Locked();

        editProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*TextView txtclose, select_avatar, select_photo;
                myDialog.setContentView(R.layout.custom_popup);
                txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
                txtclose.setText("X");
                select_avatar = myDialog.findViewById(R.id.select_avatar);
                select_avatar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BottomSheetFragment fragment = new BottomSheetFragment();
                        fragment.show(getParentFragmentManager(), fragment.getTag());
                        myDialog.dismiss();
                    }
                });*/
                /*select_photo = myDialog.findViewById(R.id.select_photo);
                select_photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Select Photo", Toast.LENGTH_SHORT).show();
                    }
                });

                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();*/
                Toast.makeText(getContext(), "Logic yet to implement", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getUserDetails();
    }

    private void getUserDetails() {
        dialog.setMessage("please wait");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        usersRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot snapshot) {
                if (snapshot.exists()){
                    String name = snapshot.getString("Name");
                    String email = snapshot.getString("Email");
                    String phone = snapshot.getString("Phone");
                    String about = snapshot.getString("About");
                    nameEdit.getEditText().setText(name);
                    nameEdit.getEditText().addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

                        @Override
                        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                            editBtnCard.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    });
                    emailEdit.getEditText().setText(email);
                    phoneEdit.getEditText().setText(phone);
                    aboutEdit.getEditText().setText(about);
                }
                else {
                    Toast.makeText(getContext(), "Error Occurred!", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((HomeActivity) getActivity()).setDrawer_UnLocked();
    }
}