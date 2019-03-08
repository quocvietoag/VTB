package com.giuseviet.danhb;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.giuseviet.danhb.adapter.CustomAdapter;
import com.giuseviet.danhb.data.DBManger;
import com.giuseviet.danhb.model.User;

import java.util.ArrayList;
import java.util.List;

public class Goi_dien extends AppCompatActivity {


    private ListView lvUser;
    private DBManger dbManger;
    private CustomAdapter customAdapter;
    private List<User> userList;
    private TextView idNguoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dg_cuoc_goi);

        //gán gói
        initWideget();
        dbManger=new DBManger(this);
        userList=dbManger.getAllUser();
        setAdapter();
        //xin quyền
        checkAndRequestPermission();
      //  Nhan_goi_dl();
        //Nhận lệnh


        //bắt sự kiện
        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDialogConform(position);
            }
        });

    }

    //mởi cửa sổ để gọi
    public void showDialogConform(final int position){
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.gd_custom_dialog);
        Button btnCall=dialog.findViewById(R.id.btn_call);
        Button btnMess=dialog.findViewById(R.id.btn_mess);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checkAndRequestPermission();
                Toast.makeText(Goi_dien.this,"Call",Toast.LENGTH_SHORT).show();
                intentCall(position);
            }
        });

        btnMess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Goi_dien.this,"Messager",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + userList.get(position).getmPhoneNumber()));
                startActivity(intent);

            }
        });
        dialog.show();
    }

    //Hàm gọi điện
    private void intentCall(int position){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+userList.get(position).getmPhoneNumber()));
        startActivity(intent);
    }

    //Xin quyền gọi nhắn tin
    private void checkAndRequestPermission(){
        String[] permissions=new String[]{
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS
        };
        List<String>listPermissionsNeeded=new ArrayList<>();
        for (String permission : permissions){
            if(ContextCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED){
                listPermissionsNeeded.add(permission);
            }
            if (!listPermissionsNeeded.isEmpty()){
                ActivityCompat.requestPermissions(this,listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),1);

            }
        }
    }

    //gán dữ liệu
    private void initWideget(){
        lvUser=findViewById(R.id.lv_gd_goi);
        idNguoiDung=findViewById(R.id.tv_idNguoidung);
    }

    //MS003: hàm đưa luồng dữ liệu
    private void setAdapter(){
        if(customAdapter==null){
            customAdapter=new CustomAdapter(this,R.layout.item_contact,userList);
            lvUser.setAdapter(customAdapter);
        }else {
            customAdapter.notifyDataSetChanged();//Nếu List có sự thay đổi
            lvUser.setSelection(customAdapter.getCount()-1);//đưa về cuối danh sách, đối tượng thêm mới
        }
    }

    //nhận lệnh
    private void Nhan_goi_dl(){
        Intent intent=getIntent();//khởi tạo biến và dùng phương thức getIntent để lấy dữ liệu
        String noidung=intent.getStringExtra("Ten_tk");//lấy dữ liệu bên activity MainActivity qua (đó là tên tk)
        idNguoiDung.setText("ID: "+noidung);
    }
}
