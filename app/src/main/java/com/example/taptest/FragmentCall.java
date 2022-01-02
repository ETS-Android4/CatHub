package com.example.taptest;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FragmentCall extends Fragment {

    View v;

    GridViewAdapter adapter;

    File filePath;
    List<Gallery> lstGallery;

    public FragmentCall() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_call, container, false);
        GridView gridView = v.findViewById(R.id.gridView);
        adapter = new GridViewAdapter(v.getContext(), lstGallery);
        gridView.setAdapter(adapter);



        FloatingActionButton fab = v.findViewById(R.id.goToCam);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String dirPath = v.getContext().getExternalFilesDir(null).getPath();
                    File dir = new File(dirPath);
                    if(!dir.exists()) {
                        Log.d("MAKE DIR", dir.mkdirs() + "");

                    }

                    filePath = File.createTempFile("IMG", ".jpg", dir);
                    if(!filePath.exists()) {
                        filePath.createNewFile();
                    }

                    Uri photoUri = FileProvider.getUriForFile(v.getContext(), "com.example.taptest.provider", filePath);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(intent, 40);

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 40 && resultCode == Activity.RESULT_OK) {
            if(filePath != null) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                try {
                    InputStream in = new FileInputStream(filePath);
                    BitmapFactory.decodeStream(in, null, options);
                    in.close();
                    in = null;
                } catch ( Exception e ) {
                    e.printStackTrace();
                }

                final int width = options.outWidth;
                final int height = options.outHeight;


                // width, height 값에 따라 inSaampleSize 값 계산


                BitmapFactory.Options imgOptions = new BitmapFactory.Options();
                imgOptions.inSampleSize = 8;
                Bitmap bitmap = BitmapFactory.decodeFile(filePath.getAbsolutePath(), imgOptions);
                Gallery gallery = new Gallery(bitmap);
                lstGallery.add(gallery);
                GridView gridView = v.findViewById(R.id.gridView);
                adapter = new GridViewAdapter(v.getContext(), lstGallery);
                gridView.setAdapter(adapter);
            }
        } else {
            Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        lstGallery = new ArrayList<>();
        lstGallery.add(new Gallery(R.drawable.bw1));
        lstGallery.add(new Gallery(R.drawable.bw2));
        lstGallery.add(new Gallery(R.drawable.bw3));
        lstGallery.add(new Gallery(R.drawable.bw4));
        lstGallery.add(new Gallery(R.drawable.bw5));
        lstGallery.add(new Gallery(R.drawable.bw6));
        lstGallery.add(new Gallery(R.drawable.bw7));
        lstGallery.add(new Gallery(R.drawable.bw8));
        lstGallery.add(new Gallery(R.drawable.bw9));
        lstGallery.add(new Gallery(R.drawable.bw10));
        lstGallery.add(new Gallery(R.drawable.bw11));
        lstGallery.add(new Gallery(R.drawable.bw12));
        lstGallery.add(new Gallery(R.drawable.bw8));
        lstGallery.add(new Gallery(R.drawable.bw3));
        lstGallery.add(new Gallery(R.drawable.bw5));
        lstGallery.add(new Gallery(R.drawable.bw2));
        lstGallery.add(new Gallery(R.drawable.bw1));
        lstGallery.add(new Gallery(R.drawable.bw9));
        lstGallery.add(new Gallery(R.drawable.bw11));
        lstGallery.add(new Gallery(R.drawable.bw10));
        lstGallery.add(new Gallery(R.drawable.bw6));

    }

}


