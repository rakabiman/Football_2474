package com.example.football_2474.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.football_2474.R;
import com.example.football_2474.database.DatabaseHelper;
import com.example.football_2474.model.Match;

public class DetailActivity extends AppCompatActivity {

    public static final String ITEM_EXTRA = "item_extra";
    TextView league;
    ImageView imgDetail;
    Button btnFavorite;
    TextView eventDetail;
    TextView homeScore;
    TextView awayScore;
    TextView venueName;
    TextView dateDetail;
    TextView status;
    TextView timeDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        league = findViewById(R.id.leagueName);
        imgDetail = findViewById(R.id.imgDetail);
        btnFavorite = findViewById(R.id.btnFavorite);
        eventDetail = findViewById(R.id.txtEventDetail);
        homeScore = findViewById(R.id.homeScore);
        awayScore = findViewById(R.id.awayScore);
        venueName = findViewById(R.id.venueName);
        dateDetail = findViewById(R.id.txtDateDetail);
        timeDetail = findViewById(R.id.txtTimeDetail);
        status = findViewById(R.id.txtStatus);

        final Match match = getIntent().getParcelableExtra(ITEM_EXTRA);

        if (match != null) {
            Glide.with(this)
                    .load(match.getStrThumb())
                    .into(imgDetail);
            league.setText(match.getStrLeague());
            eventDetail.setText(match.getStrEvent());
            homeScore.setText(match.getIntHomeScore());
            awayScore.setText(match.getIntAwayScore());
            venueName.setText(match.getStrVenue());
            dateDetail.setText(match.getDateEvent());
            timeDetail.setText(match.getStrTimeLocal().substring(0, match.getStrTimeLocal().lastIndexOf(":")));
            status.setText(match.getStrStatus());
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(match.getStrEvent());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String event = eventDetail.getText().toString();
                String date = dateDetail.getText().toString();
                String statusDb = status.getText().toString();

                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                Match match = new Match();

                match.setStrThumb(match.getStrThumb());
                match.setStrEvent(event);
                match.setDateEvent(date);
                match.setStrStatus(statusDb);

                db.addEvents(match);

                Intent gotoDetail = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(gotoDetail);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}