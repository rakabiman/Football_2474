package com.example.football_2474.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.football_2474.R;
import com.example.football_2474.database.DatabaseHelper;
import com.example.football_2474.model.Match;

import java.util.ArrayList;

public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.ViewHolder> {

    private ArrayList<Match> listEvents;
    private Match match;

    private Context mContext;

    public DatabaseAdapter(Context context, ArrayList<Match> listEvents) {
        this.listEvents = listEvents;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_fav,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        match = listEvents.get(position);

        holder.eventFav.setText(match.getStrEvent());
        holder.dateFav.setText(match.getDateEvent());
        holder.statusFav.setText(match.getStrStatus());


        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                match = listEvents.get(position);

                DatabaseHelper db = new DatabaseHelper(mContext);
                db.deleteEvents(String.valueOf(match.getIdEvent()));

                notifyItemRemoved(position);
                listEvents.remove(position);

                Toast.makeText(mContext, "Match " + match.getStrEvent() + " telah dihapus", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return listEvents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView eventFav;
        TextView dateFav;
        TextView statusFav;
        Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            eventFav = itemView.findViewById(R.id.txtEventFav);
            dateFav = itemView.findViewById(R.id.txtDateFav);
            statusFav = itemView.findViewById(R.id.txtStatusFav);
            btnDelete = itemView.findViewById(R.id.btnDelete);

        }
    }
}
