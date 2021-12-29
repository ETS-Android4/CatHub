package com.example.taptest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

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
        adapter.addItem(new Gallery(R.drawable.ic_launcher_background));
        adapter.addItem(new Gallery(R.drawable.ic_launcher_background));
        adapter.addItem(new Gallery(R.drawable.ic_launcher_background));
        adapter.addItem(new Gallery(R.drawable.ic_launcher_background));
        adapter.addItem(new Gallery(R.drawable.ic_launcher_background));
        adapter.addItem(new Gallery(R.drawable.ic_launcher_background));
        adapter.addItem(new Gallery(R.drawable.ic_launcher_background));
        adapter.addItem(new Gallery(R.drawable.ic_launcher_background));
        adapter.addItem(new Gallery(R.drawable.ic_launcher_background));
        adapter.addItem(new Gallery(R.drawable.ic_launcher_background));
        adapter.addItem(new Gallery(R.drawable.ic_launcher_background));
        adapter.addItem(new Gallery(R.drawable.ic_launcher_background));
        adapter.addItem(new Gallery(R.drawable.ic_launcher_background));
        adapter.addItem(new Gallery(R.drawable.ic_launcher_background));
        adapter.addItem(new Gallery(R.drawable.ic_launcher_background));
        adapter.addItem(new Gallery(R.drawable.ic_launcher_background));
        adapter.addItem(new Gallery(R.drawable.ic_launcher_background));
        adapter.addItem(new Gallery(R.drawable.ic_launcher_background));
        adapter.addItem(new Gallery(R.drawable.ic_launcher_background));
        adapter.addItem(new Gallery(R.drawable.ic_launcher_background));
        adapter.addItem(new Gallery(R.drawable.ic_launcher_background));
        gridView.setAdapter(adapter);
        return v;
    }
}
