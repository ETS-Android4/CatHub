package com.example.taptest;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {
    ArrayList<Gallery> items = new ArrayList<Gallery>();
    Context context;
    Dialog mDialog;

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
        photo.setImageResource(gallery.getPhoto());

        mDialog = new Dialog(context);
        mDialog.setContentView(R.layout.dialog_photo);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //mDialog.getWindow().getAttributes().windowAnimations = R.anim.scale;


        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Click Event!", Toast.LENGTH_SHORT).show();
                ImageView photos = mDialog.findViewById(R.id.photo);
                photos.setImageResource(gallery.getPhoto());

                mDialog.show();
            }
        });

        return convertView;
    }
}
