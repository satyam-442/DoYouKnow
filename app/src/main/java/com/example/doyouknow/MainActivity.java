package com.example.doyouknow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout interesting, mostPopular, animal, nature, worldCulture, science, technology, funny;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        interesting = findViewById(R.id.interesting);
        mostPopular = findViewById(R.id.mostPopular);
        animal = findViewById(R.id.animal);
        nature = findViewById(R.id.nature);
        worldCulture = findViewById(R.id.worldCulture);
        science = findViewById(R.id.science);
        technology = findViewById(R.id.technology);
        funny = findViewById(R.id.funny);

        interesting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FactsPageActivity.class);
                intent.putExtra("category","Interesting");
                startActivity(intent);
            }
        });
        mostPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FactsPageActivity.class);
                intent.putExtra("category","MostPopular");
                startActivity(intent);
            }
        });
        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FactsPageActivity.class);
                intent.putExtra("category","Animal");
                startActivity(intent);
            }
        });
        nature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FactsPageActivity.class);
                intent.putExtra("category","Nature");
                startActivity(intent);
            }
        });
        worldCulture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FactsPageActivity.class);
                intent.putExtra("category","WorldCulture");
                startActivity(intent);
            }
        });
        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FactsPageActivity.class);
                intent.putExtra("category","Science");
                startActivity(intent);
            }
        });
        technology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FactsPageActivity.class);
                intent.putExtra("category","Technology");
                startActivity(intent);
            }
        });
        funny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FactsPageActivity.class);
                intent.putExtra("category","Funny");
                startActivity(intent);
            }
        });
    }

    public void sendToBlog(View view) {
        Intent intent = new Intent(MainActivity.this, BlogPageActivity.class);
        startActivity(intent);
    }
}