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

public class LastMatchFragment extends Fragment {

    private ArrayList<Match> listEvents = new ArrayList<>();
    private RecyclerView rvLastMatch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_last_match, container, false);

        rvLastMatch = view.findViewById(R.id.rv_last);
        rvLastMatch.setHasFixedSize(true);

        rvLastMatch.setLayoutManager(new LinearLayoutManager(getContext()));

        ApiService service = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<MatchResponse> call = service.getLastEvents("1");
        call.enqueue(new Callback<MatchResponse>() {
            @Override
            public void onResponse(Call<MatchResponse> call, Response<MatchResponse> response) {
                listEvents = response.body().getEvents();

                MatchAdapter matchAdapter = new MatchAdapter(listEvents);
                rvLastMatch.setAdapter(matchAdapter);

                matchAdapter.setOnItemClickCallback(new OnItemClickCallback() {
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