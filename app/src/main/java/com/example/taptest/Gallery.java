package com.example.taptest;

import android.graphics.Bitmap;

public class Gallery {
    private int Photo = -1;
    private Bitmap bitMap;

    public Gallery() {
    }

    public Gallery(int photo) {
        Photo = photo;
    }

    public Gallery(Bitmap bitmap) { bitMap = bitmap; }

    public int getPhoto() {
        return Photo;
    }

    public void setPhoto(int photo) {
        Photo = photo;
    }

    public Bitmap getBitmap() { return bitMap; }

    public void setBitmap(Bitmap bitmap) { bitMap = bitmap;}

}
