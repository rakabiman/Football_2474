package com.example.football_2474.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.football_2474.model.Match;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db_football_2474";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "table_fav_2474";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EVENTS = "events";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_STATUS = "status";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_EVENTS + " TEXT," +
                        COLUMN_DATE + " DATE," +
                        COLUMN_STATUS + " TEXT" +
                        ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public ArrayList<Match> getAllEvents() {
        ArrayList<Match> listEvents = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_NAME,new String[]{COLUMN_ID,COLUMN_EVENTS,COLUMN_DATE,COLUMN_STATUS}, null,null,null,null,null);

        if (c != null && c.moveToFirst()) {
            do {
                Match match = new Match();

                match.setIdEvent(c.getString(0));
                match.setStrEvent(c.getString(1));
                match.setDateEvent(c.getString(2));
                match.setStrStatus(c.getString(3));

                listEvents.add(match);
            } while (c.moveToNext());
        }
        db.close();
        return listEvents;
    }

    public void addEvents(Match match) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_EVENTS, match.getStrEvent());
        values.put(COLUMN_DATE, match.getDateEvent());
        values.put(COLUMN_STATUS, match.getStrStatus());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public void deleteEvents(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, "id = " + id, null);
        db.close();
    }
}
