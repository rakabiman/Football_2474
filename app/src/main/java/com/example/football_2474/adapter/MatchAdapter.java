package com.example.football_2474.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.football_2474.OnItemClickCallback;
import com.example.football_2474.R;
import com.example.football_2474.model.Match;

import java.util.ArrayList;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ListViewHolder> {

    private ArrayList<Match> listEvents;

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public MatchAdapter(ArrayList<Match> listEvents) {
        this.listEvents = listEvents;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchAdapter.ListViewHolder holder, int position) {
            Match match = listEvents.get(position);

            holder.txtEvent.setText(match.getStrEvent());
            holder.txtDate.setText(match.getDateEvent());
            if((match.getStrStatus().equalsIgnoreCase("Match Finished"))&&(match.getIntHomeScore()!=null)){
                setScore(holder.txtScoreHome, match.getIntHomeScore());
                setScore(holder.txtScoreAway, match.getIntAwayScore());
                hideView(holder.txtTime);
            }else{
                setScore(holder.txtTime, match.getStrStatus());
                holder.txtTime.setTextSize(14);
                hideView(holder.scoreBoard);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickCallback.onItemClicked(match);
                }
            });
    }

    private void setScore(TextView textView, String score) {
        textView.setVisibility(View.VISIBLE);
        textView.setText(score);
    }
    private void hideView(View view) {
        view.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return listEvents.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView txtEvent;
        TextView txtDate;
        TextView txtScoreHome;
        TextView txtScoreAway;
        TextView txtTime;
        View scoreBoard;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            txtScoreHome = itemView.findViewById(R.id.txtScoreHome);
            txtScoreAway = itemView.findViewById(R.id.txtScoreAway);
            txtEvent = itemView.findViewById(R.id.txtEvent);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtTime = itemView.findViewById(R.id.txtTime);
            scoreBoard = itemView.findViewById(R.id.scoreBoard);
        }
    }
}
