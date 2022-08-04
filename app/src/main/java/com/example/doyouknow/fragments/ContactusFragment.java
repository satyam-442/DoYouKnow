package com.example.doyouknow.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doyouknow.R;

public class ContactusFragment extends Fragment {

    TextView ownerPhone, ownerEmail;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contactus, container, false);

        ownerPhone = view.findViewById(R.id.ownerPhone);
        ownerPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:8010763924")); //Replace with valid phone number. Remember to add the tel: prefix, otherwise it will crash.
                startActivity(intent);
            }
        });

        ownerEmail = view.findViewById(R.id.ownerEmail);
        ownerEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","sbmsolutions95@gmail.com", null));
                // Subject
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Query for DYK!!!");
                // Body of email
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello,");
                // Check if the device has an email client
                startActivity(Intent.createChooser(emailIntent,"Choose your mail application"));
            }
        });

        return view;
    }
}