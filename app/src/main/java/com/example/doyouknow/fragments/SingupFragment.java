package com.example.doyouknow.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.doyouknow.R;
import com.example.doyouknow.VerifyPhoneActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class SingupFragment extends Fragment {

    TextView signUpBtn;
    TextInputLayout nameSignUp, emailSignUp, passwordSignUp, phoneSignUp;
    FirebaseAuth mAuth;
    ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_singup, container, false);

        mAuth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(getContext());

        //HOOKS FOR SIGNUP
        nameSignUp = view.findViewById(R.id.nameSignUp);
        emailSignUp = view.findViewById(R.id.emailSignUp);
        passwordSignUp = view.findViewById(R.id.passwordSignUp);
        phoneSignUp = view.findViewById(R.id.phoneSignUp);

        signUpBtn = view.findViewById(R.id.signUpBtn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameSignUp.getEditText().getText().toString();
                String email = emailSignUp.getEditText().getText().toString();
                String password = passwordSignUp.getEditText().getText().toString();
                String phone = phoneSignUp.getEditText().getText().toString();

                if (name.isEmpty() && email.isEmpty() && password.isEmpty() && phone.isEmpty()){
                    Toast.makeText(getContext(), "Field's are empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getContext(), VerifyPhoneActivity.class);
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);
                    intent.putExtra("phone", phone);
                    intent.putExtra("name", name);
                    startActivity(intent);

                }
            }
        });
        return view;
    }
}