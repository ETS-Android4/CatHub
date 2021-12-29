package com.example.taptest;

import android.content.res.AssetManager;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;



public class FragmentContact extends Fragment {

    View v;
    private RecyclerView myrecyclerview;
    public List<PhoneNumberVO> lstContact;
    public Object PhoneNumberVO;

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

    Gson gson = new Gson();

    //onCreate메서드 에서 데이터관련 코드 넣음
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.contact_fragment);


        lstContact = new ArrayList<>();

        try {
            InputStream is = getContext().getAssets().open("phoneNumbers.json");
            //AssetManager am = getResources().getAssets();
            //InputStream is = am.open("phoneNumbers.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("person");
            int index = 0;
            while (index < jsonArray.length()) {
                Object a = jsonArray.get(index);
                String b = a.toString();

                PhoneNumberVO phoneNumberVO = gson.fromJson(b, PhoneNumberVO.class);
                lstContact.add(phoneNumberVO);
                index++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        /*
        lstContact = new ArrayList<>();
        lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_launcher_background));
*/

    }



}
