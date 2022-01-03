package com.example.taptest;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    List<Gallery> items;
    Context context;
    Dialog mDialog, rDialog;

    public GridViewAdapter(Context mContext, List<Gallery> mData) {
        this.context = mContext;
        this.items = mData;
    }

    public void addItem(Gallery gallery) {
        items.add(gallery);
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        context = parent.getContext();
        Gallery gallery = items.get(position);
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_gallery, parent, false);
        }
        ImageView photo = convertView.findViewById(R.id.imageView);

        if(gallery.getPhoto() == -1) { photo.setImageBitmap(gallery.getBitmap()); }
        else {
            photo.setImageResource(gallery.getPhoto());
        }



        mDialog = new Dialog(context);
        mDialog.setContentView(R.layout.dialog_photo);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //mDialog.getWindow().getAttributes().windowAnimations = R.anim.scale;
        ScrollView scroll = new ScrollView(context);

        rDialog = new Dialog(context);
        rDialog.setContentView(R.layout.remove_photo);


        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Image Clicked!", Toast.LENGTH_SHORT).show();
                ImageView photos = mDialog.findViewById(R.id.photo);
                ImageView photosBack = mDialog.findViewById(R.id.photo_back);
                ImageView photosNext = mDialog.findViewById(R.id.photo_next);

                photos.setImageResource(gallery.getPhoto());
                photosBack.setImageResource(items.get(position-1).getPhoto());
                photosNext.setImageResource(items.get(position-1).getPhoto());
                if(gallery.getPhoto() == -1) { photos.setImageBitmap(gallery.getBitmap()); }
                else {
                    photos.setImageResource(gallery.getPhoto());
                }
                mDialog.show();
            }
        });


        photo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                rDialog.show();

                Button yes_btn = (Button) rDialog.findViewById(R.id.yes_button);
                Button no_btn = (Button) rDialog.findViewById(R.id.no_button);

                yes_btn.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        items.remove(position);
                        notifyDataSetChanged();
                        rDialog.dismiss();
                    }
                });

                no_btn.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(),"Cancel remove", Toast.LENGTH_SHORT).show();
                        rDialog.dismiss();
                    }
                });

                return true;
            }
        });

        return convertView;
    }
}
