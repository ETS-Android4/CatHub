package com.example.taptest;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    //액티비티의 Context, Data추가
    Context mContext;
    List<Contact> mData;
    Dialog mDialog;

    public RecyclerViewAdapter(Context mContext, List<Contact> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    //1) onCreateViewHolder
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
                dialog_contact_img.setImageResource(mData.get(vHolder.getAdapterPosition()).getPhoto());


                mDialog.show();
            }
        });

        vHolder.item_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Call Image Click"+ String.valueOf(vHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }
        });
        //------------------------------------------------------------------------------------------

        return vHolder;
    }

    //2 onBindViewHolder
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_phone.setText(mData.get(position).getPhone());
        holder.img.setImageResource(mData.get(position).getPhoto());
    }

    //3) getItemCount
    @Override
    public int getItemCount() {
        return mData.size();
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
