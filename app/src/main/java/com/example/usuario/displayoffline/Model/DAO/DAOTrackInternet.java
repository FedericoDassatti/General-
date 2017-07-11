package com.example.usuario.displayoffline.Model.DAO;
import com.example.usuario.displayoffline.Model.POJO.Track;
import com.example.usuario.displayoffline.util.ResultListener;

import java.util.List;

public class DAOTrackInternet {
    //asincrono
    public void getTracksListFromInternet(ResultListener<List<Track>> controllerListener) {
        Tarea tarea = new Tarea(controllerListener);
        tarea.execute("https://api.myjson.com/bins/zwe9v");
    }
}
