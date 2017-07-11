package com.example.usuario.displayoffline.Model.DAO;


import android.os.AsyncTask;

import com.example.usuario.displayoffline.util.HTTPConnectionManager;
import com.example.usuario.displayoffline.Model.POJO.Track;
import com.example.usuario.displayoffline.Model.POJO.TrackContainer;
import com.example.usuario.displayoffline.util.DAOException;
import com.example.usuario.displayoffline.util.ResultListener;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Tarea extends AsyncTask<String, Void,List<Track>> {

    private ResultListener<List<Track>> controllerListener;

    public Tarea (ResultListener<List<Track>> controllerListener) {
        this.controllerListener = controllerListener;
    }

    @Override
    protected List<Track> doInBackground(String... params) {
        //params is an static list where I can send strings
        String url = params[0];
        List<Track> trackListList = new ArrayList<>();

        //the conexion to internet is created
        HTTPConnectionManager httpConnectionManager = new HTTPConnectionManager();
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = httpConnectionManager.getRequestStream(url);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            Gson gson = new Gson();
            TrackContainer trackContainer = gson.fromJson(bufferedReader, TrackContainer.class);
            trackListList = trackContainer.getTracksTracks();

        } catch (DAOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e1) {
                //means it is not closed
                e1.printStackTrace();
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
        httpConnectionManager.closeConnection();
        return trackListList;
    }

    @Override
    protected void onPostExecute(List<Track> tracks) {
        super.onPostExecute(tracks);
        controllerListener.finish(tracks);
    }
}
