package com.example.usuario.displayoffline.Model.POJO;


import com.google.gson.annotations.SerializedName;

public class Track {

    private Integer id;
    //translates the attribute name:
    @SerializedName("thumbnailUrl")
    private String imageUrl;
    private String title;
    private Integer albumId;


    public Track(Integer id, String imageUrl, String title, Integer albumId) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.albumId = albumId;
    }

    public Integer getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", title='" + title + '\'' +
                ", albumId=" + albumId +
                '}';
    }
}
