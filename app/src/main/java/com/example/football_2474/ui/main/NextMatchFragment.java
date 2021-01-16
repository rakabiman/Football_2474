package com.example.football_2474.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.football_2474.OnItemClickCallback;
import com.example.football_2474.activity.DetailActivity;
import com.example.football_2474.adapter.MatchAdapter;
import com.example.football_2474.R;
import com.example.football_2474.api.ApiClient;
import com.example.football_2474.api.ApiService;
import com.example.football_2474.model.Match;
import com.example.football_2474.model.MatchResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NextMatchFragment extends Fragment {

    private ArrayList<Match> listNextEvents = new ArrayList<>();
    private RecyclerView rvNextMatch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_next_match, container, false);

        rvNextMatch = view.findViewById(R.id.rv_next);
        rvNextMatch.setHasFixedSize(true);

        rvNextMatch.setLayoutManager(new LinearLayoutManager(getContext()));

        ApiService service = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<MatchResponse> call = service.getNextEvents("1");
        call.enqueue(new Callback<MatchResponse>() {
            @Override
            public void onResponse(Call<MatchResponse> call, Response<MatchResponse> response) {
                listNextEvents = response.body().getEvents();

                MatchAdapter nextMatchAdapter = new MatchAdapter(listNextEvents);
                rvNextMatch.setAdapter(nextMatchAdapter);

                nextMatchAdapter.setOnItemClickCallback(new OnItemClickCallback() {
                    @Override
                    public void onItemClicked(Match match) {
                        Intent gotoDetail = new Intent(getActivity(), DetailActivity.class);
                        gotoDetail.putExtra(DetailActivity.ITEM_EXTRA, match);
                        startActivity(gotoDetail);
                    }
                });
            }

            @Override
            public void onFailure(Call<MatchResponse> call, Throwable t) {

            }
        });

        return view;
    }
}