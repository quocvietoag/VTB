package com.giuseviet.danhb.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.giuseviet.danhb.R;
import com.giuseviet.danhb.model.User;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<User> {

    private Context context;
    private int resource;
    private List<User> listUser;

    public CustomAdapter(Context context, int resource, List<User> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.listUser=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHoder;

        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_contact,parent,false);
            viewHoder=new ViewHolder();
            viewHoder.imgAvatar=  convertView.findViewById(R.id.img_avatar);
            viewHoder.tvName=  convertView.findViewById(R.id.tv_contact_name);
            viewHoder.tvGender=  convertView.findViewById(R.id.tv_ct_gender);
            viewHoder.tvPhoneNumber=  convertView.findViewById(R.id.tv_ct_number);
            viewHoder.tvEmail=  convertView.findViewById(R.id.tv_ct_email);
            viewHoder.tvAddress= convertView.findViewById(R.id.tv_ct_address);
            convertView.setTag(viewHoder);
        }else {
            viewHoder=(ViewHolder)convertView.getTag();
        }

        User user=listUser.get(position);
        viewHoder.tvName.setText(user.getmName());
        viewHoder.tvGender.setText(user.getmGender());
        viewHoder.tvPhoneNumber.setText(user.getmPhoneNumber());
        viewHoder.tvEmail.setText(user.getmEmail());
        viewHoder.tvAddress.setText(user.getmAddress());
       // edtTaikhoan.getText().toString().equals(tk_sys)==true
        if(user.getmGender().toLowerCase().equals("nam")){
            viewHoder.imgAvatar.setBackgroundResource(R.drawable.nam);
        }else {
            viewHoder.imgAvatar.setBackgroundResource(R.drawable.nu);
        }
        return convertView;
    }



    public class ViewHolder{
        private ImageView imgAvatar;
        private TextView tvName;
        private TextView tvGender;
        private TextView tvPhoneNumber;
        private TextView tvEmail;
        private TextView tvAddress;

    }


}
