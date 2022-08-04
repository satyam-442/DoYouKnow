package com.example.doyouknow.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.doyouknow.R;

public class FactsMainPage extends Fragment {

    RelativeLayout interesting, mostPopular, animal, nature, worldCulture, science, technology, funny;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_facts_main_page, container, false);

        interesting = view.findViewById(R.id.interesting);
        mostPopular = view.findViewById(R.id.mostPopular);
        animal = view.findViewById(R.id.animal);
        nature = view.findViewById(R.id.nature);
        worldCulture = view.findViewById(R.id.worldCulture);
        science = view.findViewById(R.id.science);
        technology = view.findViewById(R.id.technology);
        funny = view.findViewById(R.id.funny);

        /*if (null == savedInstanceState) {
            getParentFragmentManager().beginTransaction()
                    .addToBackStack("fragmentA")
                    .replace(R.id.container, new FactsMainPage(), "fragmentA")
                    .commit();
        }*/

        interesting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("category", "Interesting");
                FactsPage factsPage = new FactsPage();
                factsPage.setArguments(bundle);
                //getParentFragmentManager().beginTransaction().replace(R.id.container, factsPage).commit();
                replaceFragment(factsPage,"fragmentB");

            }
        });
        mostPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("category", "MostPopular");
                FactsPage factsPage = new FactsPage();
                factsPage.setArguments(bundle);
                //getParentFragmentManager().beginTransaction().replace(R.id.container, factsPage).commit();
                replaceFragment(factsPage,"fragmentB");
            }
        });
        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("category", "Animal");
                FactsPage factsPage = new FactsPage();
                factsPage.setArguments(bundle);
                //getParentFragmentManager().beginTransaction().replace(R.id.container, factsPage).commit();
                replaceFragment(factsPage,"fragmentB");
            }
        });
        nature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("category", "Nature");
                FactsPage factsPage = new FactsPage();
                factsPage.setArguments(bundle);
                //getParentFragmentManager().beginTransaction().replace(R.id.container, factsPage).commit();
                replaceFragment(factsPage,"fragmentB");
            }
        });
        worldCulture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("category", "WorldCulture");
                FactsPage factsPage = new FactsPage();
                factsPage.setArguments(bundle);
                //getParentFragmentManager().beginTransaction().replace(R.id.container, factsPage).commit();
                replaceFragment(factsPage,"fragmentB");
            }
        });
        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("category", "Science");
                FactsPage factsPage = new FactsPage();
                factsPage.setArguments(bundle);
                //getParentFragmentManager().beginTransaction().replace(R.id.container, factsPage).commit();
                replaceFragment(factsPage,"fragmentB");
            }
        });
        technology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("category", "Technology");
                FactsPage factsPage = new FactsPage();
                factsPage.setArguments(bundle);
                //getParentFragmentManager().beginTransaction().replace(R.id.container, factsPage).commit();
                replaceFragment(factsPage,"fragmentB");
            }
        });
        funny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("category", "Funny");
                FactsPage factsPage = new FactsPage();
                factsPage.setArguments(bundle);
                //getParentFragmentManager().beginTransaction().replace(R.id.container, factsPage).commit();
                replaceFragment(factsPage,"fragmentB");
            }
        });

        return view;
    }

    public void replaceFragment(Fragment fragment, String tag) {
        //Get current fragment placed in container
        Fragment currentFragment = getParentFragmentManager().findFragmentById(R.id.container);
        //Prevent adding same fragment on top
        if (currentFragment.getClass() == fragment.getClass()) {
            return;
        }
        //If fragment is already on stack, we can pop back stack to prevent stack infinite growth
        if (getParentFragmentManager().findFragmentByTag(tag) != null) {
            getParentFragmentManager().popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        //Otherwise, just replace fragment
        getParentFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(R.id.container, fragment, tag)
                .commit();
    }

}