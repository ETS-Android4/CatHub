package com.example.taptest;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements Filterable {

    //액티비티의 Context, Data추가
    Context mContext;
    List<PhoneNumberVO> mData; // filteredList
    List<PhoneNumberVO> unFilData; // unfilteredlist
    Dialog mDialog;



    public RecyclerViewAdapter(Context mContext, List<PhoneNumberVO> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.unFilData = mData;
    }

    //1) onCreateViewHolder: ViewHolder 생성 시 호출. 처음 화면에 보이는 View에 대해 생성
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v ;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_contact, parent, false);
        final MyViewHolder vHolder = new MyViewHolder(v);

        //Dialog init
        mDialog = new Dialog(mContext);
        mDialog.setContentView(R.layout.dialog_contact);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //------------------------------------------------------------------------------------------
        vHolder.item_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Text Click"+ String.valueOf(vHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();

                TextView dialog_name_tv = mDialog.findViewById(R.id.dialog_name);
                TextView dialog_phone_tv = mDialog.findViewById(R.id.dialog_phone);
                ImageView dialog_contact_img = mDialog.findViewById(R.id.dialog_img);
                dialog_name_tv.setText(mData.get(vHolder.getAdapterPosition()).getName());
                dialog_phone_tv.setText(mData.get(vHolder.getAdapterPosition()).getPhone());

                mDialog.show();

                Button call_btn = (Button) mDialog.findViewById(R.id.dialog_btn_call);
                Button msg_btn = (Button) mDialog.findViewById(R.id.dialog_btn_message);

                call_btn.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "Call button click", Toast.LENGTH_SHORT).show();

                        try {
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse("tel:" + vHolder.tv_phone.getText().toString()));
                            v.getContext().startActivity(callIntent);
                        }
                        catch (ActivityNotFoundException e) {
                            Log.e("Call", "Fail to Calling", e);
                        }
                    }
                });

                msg_btn.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "Msg button click", Toast.LENGTH_SHORT).show();

                        try {
                            Intent msgIntent = new Intent(Intent.ACTION_VIEW);
                            msgIntent.setData(Uri.parse("sms:" + vHolder.tv_phone.getText().toString()));
                            msgIntent.putExtra("sms_body", "Hello world!");
                            v.getContext().startActivity(msgIntent);
                        }
                        catch (ActivityNotFoundException e) {
                            Log.e("Msg", "Fail to Messaging", e);
                        }
                    }
                });

            }
        });

        vHolder.item_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Move to Dial"+ String.valueOf(vHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();

                try {
                    Intent newIntent = new Intent(Intent.ACTION_DIAL);
                    newIntent.setData(Uri.parse("tel:" + vHolder.tv_phone.getText().toString()));
                    v.getContext().startActivity(newIntent);
                }
                catch (ActivityNotFoundException e) {
                    Log.e("Dial", "Fail to dial", e);
                }

            }
        });
        //------------------------------------------------------------------------------------------

        return vHolder;
    }

    //2 onBindViewHolder: 화면에 ViewHolder가 붙을 때마다 호출 (실질적인 데이터 처리 이루어짐)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //PhoneNumberVO phoneNumberVO = mData.get(position);
        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_phone.setText(mData.get(position).getPhone());
        //holder.img.setImageResource(mData.get(position).getPhoto());
    }

    //3) getItemCount
    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String str = constraint.toString();
                if(str.isEmpty()) {
                    mData = unFilData;
                } else {
                    List<PhoneNumberVO> filteringList = new ArrayList<>();
                    for(PhoneNumberVO item : unFilData) {
                        if(item.getName().toLowerCase().contains(str) || item.getName().contains(str) || item.getPhone().contains(str))
                            filteringList.add(item);
                    }
                    mData = filteringList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mData;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mData = (List<PhoneNumberVO>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    //0) 뷰홀더 이너클래스 생성
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout item_contact;      //item_contact의 부모레이아웃
        private ImageView item_call;
        private TextView tv_name;
        private TextView tv_phone;
        private ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_contact = itemView.findViewById(R.id.contact_item_id);
            item_call = itemView.findViewById(R.id.call_img);
            tv_name = itemView.findViewById(R.id.name_contact);
            tv_phone = itemView.findViewById(R.id.phone_contact);
            img = itemView.findViewById(R.id.img_contact);
        }
    }
}
