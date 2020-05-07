package com.example.albumstolistentoatwork_java;

public class Album {

    private String albumName;
    private String albumYear;
    private String artistName;
    private String albumImage;

    public Album() {

    }
    public String getAlbumName() {
        return albumName;
    };
    public String getAlbumYear() {
        return albumYear;
    };
    public String getArtistName() {
        return artistName;
    };
    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumName(String name) {
        this.albumName = name;
    }
    public void setAlbumYear(String year) {
        this.albumYear = year;
    }
    public void setArtistName(String artist) {
        this.artistName = artist;
    }
    public void setAlbumImage(String albumCover) {
        this.albumImage = albumCover;
    }
}
