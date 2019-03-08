package com.giuseviet.danhb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.giuseviet.danhb.Goi_dien;
import com.giuseviet.danhb.R;
import com.giuseviet.danhb.data.DBManger;
import com.giuseviet.danhb.model.User;

public class Dieu_huong extends AppCompatActivity {

    ImageView imvcall;
    ImageView imvsetting;
    TextView idNguoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gd_chuyen_huong);
        initWideget();

        //Khởi tạo tự động
        DBManger dbManger = new DBManger(this);
        dbManger.addUser_auto();
       // Nhan_goi_dl();




        imvcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent=new Intent();

                intent.setClass(v.getContext(), Goi_dien.class);//chọn class để truyền dữ liệu sang
                //Truyền dữ liệu qua activity mới
                startActivity(intent);//bắt đầu truyền dữ liệu
                //finish();//đóng activity này lun
            }
        });

        imvsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent=new Intent();
               // intent.putExtra("Ten_tk",idNguoiDung.getText().toString());
                intent.setClass(v.getContext(), NhanLen.class);//chọn class để truyền dữ liệu sang
                startActivity(intent);//bắt đầu truyền dữ liệu
               // finish();//đóng activity này lun
            }
        });

    }


    private void initWideget(){
        imvcall= findViewById(R.id.imv_goi);
        imvsetting=findViewById(R.id.imv_setting);
        idNguoiDung=findViewById(R.id.tv_idNguoidung);
    }

    private void Nhan_goi_dl(){
        Intent intent=getIntent();//khởi tạo biến và dùng phương thức getIntent để lấy dữ liệu
        String noidung=intent.getStringExtra("Ten_tk");//lấy dữ liệu bên activity MainActivity qua (đó là tên tk)
        idNguoiDung.setText("ID: "+noidung);
    }
}
