package com.example.taptest;

public class Gallery {
    private int Photo;

    public Gallery() {
    }
    public Gallery(int photo) {
        Photo = photo;
    }

    public int getPhoto() {
        return Photo;
    }

    public void setPhoto(int photo) {
        Photo = photo;
    }

    public int getPhotoBack(){

        return Photo - 1;
    }

    public void setPhotoBack(int photoBack){
        Photo = photoBack;
    }

    public int getPhotoNext(){
        return Photo + 1;
    }

    public void setPhotoNext(int photoNext){
        Photo = photoNext;
    }

}
