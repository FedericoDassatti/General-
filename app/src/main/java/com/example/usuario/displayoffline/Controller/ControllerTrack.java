package com.example.usuario.displayoffline.Controller;


import android.content.Context;
import android.widget.Toast;

import com.example.usuario.displayoffline.Model.DAO.DAOTrackDatabase;
import com.example.usuario.displayoffline.Model.DAO.DAOTrackInternet;
import com.example.usuario.displayoffline.Model.POJO.Track;
import com.example.usuario.displayoffline.util.HTTPConnectionManager;
import com.example.usuario.displayoffline.util.ResultListener;

import java.util.List;

public class ControllerTrack {
    //asynchronous to be compatible with internet
    public void getListTTrack(final Context context, final ResultListener<List<Track>>  viewListener) {

        if (HTTPConnectionManager.isNetworkingOnline(context)) {
            //is requested to internet:
            DAOTrackInternet daoTrackInternet = new DAOTrackInternet();
            daoTrackInternet.getTracksListFromInternet(new ResultListener<List<Track>>() {
                @Override
                public void finish(List<Track> resultado) {
                    //comes from internet and is saved in the database:
                    DAOTrackDatabase daoTrackDatabase = new DAOTrackDatabase(context);
                    daoTrackDatabase.addListCard(resultado);

                    viewListener.finish(resultado);
                }
            });
        } else {
            //is requested to the database
            DAOTrackDatabase daoTrackDatabase = new DAOTrackDatabase(context);
            List<Track> aListOfTracks = daoTrackDatabase.getListTrackInDatabase();
            Toast.makeText(context, aListOfTracks.toString(), Toast.LENGTH_SHORT).show();
            viewListener.finish(aListOfTracks);
        }
    }
}
