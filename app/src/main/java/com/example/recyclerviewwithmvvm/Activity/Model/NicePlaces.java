package com.example.recyclerviewwithmvvm.Activity.Model;

public class NicePlaces {

    int imgUrl;
    String placeName;


    public NicePlaces(int imgUrl, String placeName) {
        this.imgUrl = imgUrl;
        this.placeName = placeName;
    }


    public int getImgUrl() {
        return imgUrl;
    }

    public String getPlaceName() {
        return placeName;
    }
}
