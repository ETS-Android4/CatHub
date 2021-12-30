package com.example.taptest;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;



public class FragmentContact extends Fragment {

    View v;
    private RecyclerView myrecyclerview;
    public List<PhoneNumberVO> lstContact;
    public Object PhoneNumberVO;

    Dialog mDialog;

    public FragmentContact() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.contact_fragment, container, false);


        myrecyclerview = v.findViewById(R.id.contact_recyclerview);
        myrecyclerview.addItemDecoration(new DividerItemDecoration(v.getContext(), 1));
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), lstContact);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerViewAdapter);

        mDialog = new Dialog(v.getContext());
        mDialog.setContentView(R.layout.add_contact);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        FloatingActionButton fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "You can add a new address", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                mDialog.show();

                Button can_btn = (Button) mDialog.findViewById(R.id.button);
                Button ok_btn = (Button) mDialog.findViewById(R.id.button2);

                final EditText nameT = (EditText) mDialog.findViewById(R.id.editTextTextPersonName);
                final EditText phoneT = (EditText) mDialog.findViewById(R.id.editTextTextPersonName2);

                can_btn.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });

                ok_btn.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = nameT.getText().toString();
                        String phone = phoneT.getText().toString();

                        PhoneNumberVO phoneNumberVO = new PhoneNumberVO(name, phone);
                        lstContact.add(phoneNumberVO);
                        mDialog.dismiss();
                    }
                });

            }
        });


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


    }
}
