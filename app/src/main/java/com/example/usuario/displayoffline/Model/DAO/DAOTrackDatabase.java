package com.example.usuario.displayoffline.Model.DAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.usuario.displayoffline.Model.POJO.Track;

import java.util.ArrayList;
import java.util.List;

//this is the track database table dao
public class DAOTrackDatabase extends DatabaseHelper{
    //public to be accesed from databaseHelper
    public static final String TABLE_NAME = "tracks";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_ALBUMID = "albumId";


    public DAOTrackDatabase(Context context) {
        super(context);
    }

    public void addTrackTrack(Track track) {
        //checks if it was available previously
        if (!estaEnLaDB(track.getId().toString())) {
            SQLiteDatabase database = getWritableDatabase();

            ContentValues row = new ContentValues();
            row.put(COLUMN_ID, track.getId());
            row.put(COLUMN_IMAGE, track.getImageUrl());
            row.put(COLUMN_TITLE, track.getTitle());
            row.put(COLUMN_ALBUMID, track.getAlbumId());

            database.insert(TABLE_NAME, null, row);
            //closes the conexion
            database.close();
        }
    }

    public void addListCard(List<Track> listaTracks) {
        for (Track track : listaTracks) {
            addTrackTrack(track);
        }
    }

    public Boolean estaEnLaDB(String id) {

        SQLiteDatabase database = getReadableDatabase();
        String query = "SELECT  "+ COLUMN_ID +
                " FROM " + TABLE_NAME +
                " WHERE " + COLUMN_ID + " = " + "'" + id + "'";

        Cursor cursor = database.rawQuery(query, null);

        Boolean esta = cursor.moveToNext();
        cursor.close();
        database.close();

        return esta;
    }

    //este nos da todas las cartas qwue tenga en la base de datos
    public List<Track> getListTrackInDatabase() {
        List<Track> listaListaTracks = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = database.rawQuery(query, null);

        while (cursor.moveToNext()) {
            Integer id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            String imageUrl=cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE));
            String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
            Integer albumId = cursor.getInt(cursor.getColumnIndex(COLUMN_ALBUMID));
            Track track = new Track(id, imageUrl, title, albumId);
            listaListaTracks.add(track);
        }

        cursor.close();
        database.close();
        return listaListaTracks;
    }
}
