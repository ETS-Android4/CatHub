package com.example.taptest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentCall extends Fragment {

    View v;

    public FragmentCall() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_call, container, false);
        GridView gridView = v.findViewById(R.id.gridView);
        GridViewAdapter adapter = new GridViewAdapter();

        adapter.addItem(new Gallery(R.drawable.bw1));
        adapter.addItem(new Gallery(R.drawable.bw2));
        adapter.addItem(new Gallery(R.drawable.bw3));
        adapter.addItem(new Gallery(R.drawable.bw4));
        adapter.addItem(new Gallery(R.drawable.bw5));
        adapter.addItem(new Gallery(R.drawable.bw6));
        adapter.addItem(new Gallery(R.drawable.bw7));
        adapter.addItem(new Gallery(R.drawable.bw8));
        adapter.addItem(new Gallery(R.drawable.bw9));
        adapter.addItem(new Gallery(R.drawable.bw10));
        adapter.addItem(new Gallery(R.drawable.bw11));
        adapter.addItem(new Gallery(R.drawable.bw12));
        adapter.addItem(new Gallery(R.drawable.bw8));
        adapter.addItem(new Gallery(R.drawable.bw3));
        adapter.addItem(new Gallery(R.drawable.bw5));
        adapter.addItem(new Gallery(R.drawable.bw2));
        adapter.addItem(new Gallery(R.drawable.bw1));
        adapter.addItem(new Gallery(R.drawable.bw9));
        adapter.addItem(new Gallery(R.drawable.bw11));
        adapter.addItem(new Gallery(R.drawable.bw10));
        adapter.addItem(new Gallery(R.drawable.bw6));
        gridView.setAdapter(adapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }
}
