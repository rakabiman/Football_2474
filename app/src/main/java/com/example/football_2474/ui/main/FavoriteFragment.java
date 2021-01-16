package com.example.football_2474.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.football_2474.R;
import com.example.football_2474.adapter.DatabaseAdapter;
import com.example.football_2474.database.DatabaseHelper;
import com.example.football_2474.model.Match;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

    RecyclerView rvMatchFav;
    private ArrayList<Match> listEvents;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        rvMatchFav = view.findViewById(R.id.rv_fav);

        rvMatchFav.setLayoutManager(new LinearLayoutManager(getContext()));

        DatabaseHelper db = new DatabaseHelper(getContext());
        listEvents = db.getAllEvents();

        if (listEvents.size() != 0) {
            DatabaseAdapter adapter = new DatabaseAdapter(getContext(),listEvents);
            rvMatchFav.setAdapter(adapter);
        }

        return view;
    }
}