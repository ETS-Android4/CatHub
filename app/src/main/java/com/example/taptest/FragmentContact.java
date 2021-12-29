package com.example.taptest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentContact extends Fragment {

    View v;
    private RecyclerView myrecyclerview;
    private List<Contact> lstContact;

    public FragmentContact() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.contact_fragment, container, false);


        myrecyclerview = v.findViewById(R.id.contact_recyclerview);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), lstContact);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerViewAdapter);


        return v;
    }


    // nCteate메서드 에서 데이터관련 코드 넣음
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstContact = new ArrayList<>();
        lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_baseline_contacts_24));
        lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_baseline_call_24));
        lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_baseline_star_24));
        lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_baseline_contacts_24));
        lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_baseline_call_24));
        lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_baseline_star_24));
        lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_baseline_contacts_24));
        lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_baseline_call_24));
        lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_baseline_star_24));
        lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_baseline_contacts_24));
        lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_baseline_call_24));
        lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_baseline_star_24));
        lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_baseline_contacts_24));
        lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_baseline_call_24));
        lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_baseline_star_24));
        lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_baseline_contacts_24));
        lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_baseline_call_24));
        lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_baseline_contacts_24));
        lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_baseline_call_24));
        lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_baseline_star_24));
        lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_baseline_star_24));
        lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_baseline_contacts_24));
        lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_baseline_call_24));
        lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_baseline_star_24));

    }
}
