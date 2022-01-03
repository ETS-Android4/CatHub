package com.example.taptest;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.renderscript.ScriptGroup;
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
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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

    private final int CAMERA_REQUEST_CODE = 40;
    private final int GALLERY_REQUEST_CODE = 50;

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
                    startActivityForResult(intent, CAMERA_REQUEST_CODE);

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        FloatingActionButton gal = v.findViewById(R.id.goToGallery);
        gal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent();
                newIntent.setType("image/*");
                newIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(newIntent, GALLERY_REQUEST_CODE);
            }
        });


        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
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

                ExifInterface exif = null;
                try {
                    exif = new ExifInterface(filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_UNDEFINED);

                Bitmap bmRotated = rotateBitmap(bitmap, orientation);
                Gallery gallery = new Gallery(bmRotated);
                lstGallery.add(gallery);
                adapter.notifyDataSetChanged();
            }
        } else if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            try {
                InputStream in = getActivity().getContentResolver().openInputStream(data.getData());
                Bitmap img = BitmapFactory.decodeStream(in);
                in.close();

                Uri selectedImage = data.getData();


                ExifInterface exif = null;
                try {
                    exif = new ExifInterface(getRealPathFromURI(selectedImage));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_UNDEFINED);

                Bitmap bmRotated = rotateBitmap(img, orientation);

                lstGallery.add(new Gallery(bmRotated));
                adapter.notifyDataSetChanged();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        lstGallery = new ArrayList<>();
        lstGallery.add(new Gallery(R.drawable.bw01));
        lstGallery.add(new Gallery(R.drawable.bw02));
        lstGallery.add(new Gallery(R.drawable.bw03));
        lstGallery.add(new Gallery(R.drawable.bw04));
        lstGallery.add(new Gallery(R.drawable.bw05));
        lstGallery.add(new Gallery(R.drawable.bw06));
        lstGallery.add(new Gallery(R.drawable.bw07));
        lstGallery.add(new Gallery(R.drawable.bw08));
        lstGallery.add(new Gallery(R.drawable.bw09));
        lstGallery.add(new Gallery(R.drawable.bw10));
        lstGallery.add(new Gallery(R.drawable.bw11));
        lstGallery.add(new Gallery(R.drawable.bw12));
        lstGallery.add(new Gallery(R.drawable.bw13));
        lstGallery.add(new Gallery(R.drawable.bw14));
        lstGallery.add(new Gallery(R.drawable.bw15));
        lstGallery.add(new Gallery(R.drawable.bw16));
        lstGallery.add(new Gallery(R.drawable.bw17));
        lstGallery.add(new Gallery(R.drawable.bw18));
        lstGallery.add(new Gallery(R.drawable.bw19));
        lstGallery.add(new Gallery(R.drawable.bw20));
        lstGallery.add(new Gallery(R.drawable.bw21));

    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {

        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.setScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.setRotate(-90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.setRotate(-90);
                break;
            default:
                return bitmap;
        }
        try {
            Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return bmRotated;
        }
        catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getRealPathFromURI(Uri contentUri) {
        if (contentUri.getPath().startsWith("/storage")) {
            return contentUri.getPath();
        }
        String id = DocumentsContract.getDocumentId(contentUri).split(":")[1];
        String[] columns = { MediaStore.Files.FileColumns.DATA };
        String selection = MediaStore.Files.FileColumns._ID + " = " + id;
        Cursor cursor = getActivity().getContentResolver().query(MediaStore.Files.getContentUri("external"), columns, selection, null, null);
        try {
            int columnIndex = cursor.getColumnIndex(columns[0]);
            if (cursor.moveToFirst()) {
                return cursor.getString(columnIndex);
            }
        } finally {
            cursor.close();
        }
        return null;
    }


}

