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

import com.google.gson.Gson;
import java.io.InputStream;

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

    // onCreate메서드 에서 데이터관련 코드 넣음
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            lstContact = new ArrayList<>();
            lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_group_wh));
            lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_call_wh));
            lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_star_wh));
            lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_group_wh));
            lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_call_wh));
            lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_star_wh));
            lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_group_wh));
            lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_call_wh));
            lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_star_wh));
            lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_group_wh));
            lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_call_wh));
            lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_star_wh));
            lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_group_wh));
            lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_call_wh));
            lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_star_wh));
            lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_group_wh));
            lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_call_wh));
            lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_group_wh));
            lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_call_wh));
            lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_star_wh));
            lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_star_wh));
            lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_group_wh));
            lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_call_wh));
            lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_star_wh));
    }

    //code for phoneNumber
    /*private ArrayList<PhoneNumberVO>getPhoneNumbers(){
        ArrayList<PhoneNumberVO>list_phoneNumers = new ArrayList<>();
        Gson gson = new Gson();

        try{
            InputStream is = getAssets().open("album.json");


        }
    }*/
}
