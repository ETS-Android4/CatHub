package com.example.taptest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

        adapter.addItem(new Gallery(R.drawable.bw01));
        adapter.addItem(new Gallery(R.drawable.bw02));
        adapter.addItem(new Gallery(R.drawable.bw03));
        adapter.addItem(new Gallery(R.drawable.bw04));
        adapter.addItem(new Gallery(R.drawable.bw05));
        adapter.addItem(new Gallery(R.drawable.bw06));
        adapter.addItem(new Gallery(R.drawable.bw07));
        adapter.addItem(new Gallery(R.drawable.bw08));
        adapter.addItem(new Gallery(R.drawable.bw09));
        adapter.addItem(new Gallery(R.drawable.bw10));
        adapter.addItem(new Gallery(R.drawable.bw11));
        adapter.addItem(new Gallery(R.drawable.bw12));
        adapter.addItem(new Gallery(R.drawable.bw13));
        adapter.addItem(new Gallery(R.drawable.bw14));
        adapter.addItem(new Gallery(R.drawable.bw15));
        adapter.addItem(new Gallery(R.drawable.bw16));
        adapter.addItem(new Gallery(R.drawable.bw17));
        adapter.addItem(new Gallery(R.drawable.bw18));
        adapter.addItem(new Gallery(R.drawable.bw19));
        adapter.addItem(new Gallery(R.drawable.bw20));
        adapter.addItem(new Gallery(R.drawable.bw21));
        gridView.setAdapter(adapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }
}
