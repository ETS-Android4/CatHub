package com.example.taptest;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;



public class FragmentContact extends Fragment implements TextWatcher {

    View v;
    private RecyclerView myrecyclerview;
    private RecyclerViewAdapter recyclerViewAdapter;
    private final int REQUEST_CONTACT = 10;
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
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), lstContact);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerViewAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(myrecyclerview);

        mDialog = new Dialog(v.getContext());
        mDialog.setContentView(R.layout.add_contact);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText editText = v.findViewById(R.id.searchContact);
        editText.addTextChangedListener(this);

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
                        recyclerViewAdapter.notifyDataSetChanged();
                        mDialog.dismiss();
                    }
                });

            }
        });

        FloatingActionButton fab2 = v.findViewById(R.id.fab_connect);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, REQUEST_CONTACT);
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

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();
            lstContact.remove(position);
            recyclerViewAdapter.notifyItemRemoved(position);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CONTACT && resultCode == Activity.RESULT_OK) {
            Cursor cursor = getActivity().getContentResolver().query(data.getData(),
                    new String[] {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER},
                    null, null, null);
            cursor.moveToFirst();
            String name = cursor.getString(0);
            String phone = cursor.getString(1);
            cursor.close();

            PhoneNumberVO phoneNumberVO = new PhoneNumberVO(name, phone);
            lstContact.add(phoneNumberVO);
            recyclerViewAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        recyclerViewAdapter.getFilter().filter(s.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
