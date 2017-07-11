package com.example.usuario.displayoffline.Model.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;



public class TrackContainer {

    @SerializedName("tracks")
    List<Track> tracksTracks = new ArrayList<>();

    public List<Track> getTracksTracks() {
        return tracksTracks;
    }
}
