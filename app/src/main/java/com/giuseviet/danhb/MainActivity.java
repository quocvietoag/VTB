package com.giuseviet.danhb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edtTaikhoan;
    EditText edtMatkhau;
    Button btnDangnhap;
    Button btnDangky;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gd_dangnhap);
        initWideget();

        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();//để truyền dữ liệu qua các Activity
                String tk_sys="admin";//tài khoản //sẽ thay đổi khi có dữ liệu
                String pass_sys="0000";//mật khẩu giả bộ
                if(edtTaikhoan.getText().toString().equals(tk_sys)==true&&edtMatkhau.getText().toString().equals(pass_sys)==true){
                    intent.putExtra("Ten_tk",edtTaikhoan.getText().toString());//Truyền dữ liệu qua activity mới
                    intent.setClass(v.getContext(),Dieu_huong.class);//chọn class để truyền dữ liệu sang
                    startActivity(intent);//bắt đầu truyền dữ liệu
                    finish();//đóng activity này lun
                }

            }
        });

    }

    //Hàm gán id cho tất cả wideget
    private void initWideget(){
        edtTaikhoan=findViewById(R.id.edt_tai_khoan);
        edtMatkhau=findViewById(R.id.edt_mat_khau);
        btnDangnhap=findViewById(R.id.btn_dangnhap);
    }
}
