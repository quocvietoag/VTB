package com.giuseviet.danhb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.giuseviet.danhb.adapter.CustomAdapter;
import com.giuseviet.danhb.data.DBManger;
import com.giuseviet.danhb.model.User;

import java.util.List;

public class NhanLen extends AppCompatActivity {


    private TextView tvIdNguoidung;//Gán id đăng nhập thành công1
    private EditText edtId;
    private EditText edtName;
    private EditText edtGender;
    private EditText edtPhoneNumber;
    private EditText edtEmail;
    private EditText edtAddress;
    private Button btnSave;
    private Button btnUpdate;
    private ListView lvUser;
    private DBManger dbManger;
    private CustomAdapter customAdapter;
    private List<User> userList;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gd_danhba_them_sua_xoa);

        //MS:001 gán các id
        initWideget();
        //MS002: Hàm nhận dữ liệu từ activiti cha
        Nhan_goi_dl();
        //Khởi tạo lớ DBManager
        dbManger=new DBManger(this);
        //khởi tạo dư liệu
        userList=dbManger.getAllUser();
        //MS003: Hàm cài đặt đưa luồng dữ liệu
        setAdapter();
        //Bắt sự kiện cho btnSave

        //adduser

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user1=createUser();//MS004: Khởi tạo user
                if(user1!=null){//Nếu đối tượng user có dữ liệu
                    dbManger.addUser(user1);
                }
                userList.clear();//dọn hết tất cả list user để sự kiện thay đổi hoạt động
                userList.addAll(dbManger.getAllUser());//rồi thêm lại để có sự thay đổi cho sự kiện pahts hiện
                setAdapter();
            }
        });
        //Bắt sụ kiện cho listview bấm vào thì hiện lên ô text
        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                User user1=userList.get(position);
                edtId.setText(String.valueOf(user1.getmID()));
                edtName.setText(user1.getmName());
                edtGender.setText(user1.getmGender());
                edtPhoneNumber.setText(user1.getmPhoneNumber());
                edtEmail.setText(user1.getmEmail());
                edtAddress.setText(user1.getmAddress());
                btnSave.setEnabled(false);
                btnUpdate.setEnabled(true);
            }
        });
        //bắt sự kiện cho button update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=new User();//tạo đối tượng user
                //Lấy dữ liệu từ ngoài edit text vào trong để sửa
                user.setmID(Integer.parseInt(edtId.getText().toString()));
                user.setmName(edtName.getText().toString());
                user.setmGender(edtGender.getText().toString());
                user.setmPhoneNumber(edtPhoneNumber.getText().toString());
                user.setmEmail(edtEmail.getText().toString());
                user.setmAddress(edtAddress.getText().toString());
                String a = edtName.getText().toString();
                int result=dbManger.updateUser(user);//khởi tạo biến kết quả trả về
                if(result>0){
                    btnSave.setEnabled(true);
                    btnUpdate.setEnabled(false);
                    updateListUser();//MS005: Hàm update student
                    Toast.makeText(NhanLen.this,"Update "+ a +" sucessfully",Toast.LENGTH_SHORT).show();
                }else{
                    btnSave.setEnabled(false);
                    btnUpdate.setEnabled(true);
                }

            }
        });
        //Xóa đối tượng người dùng
        lvUser.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                User user=userList.get(position);//lấy vị trí đối tượng cần xóa
                int result=dbManger.deleteUser(user.getmID());//lấy id của đối tượng đó
                if(result>0){
                    Toast.makeText(NhanLen.this,"Delete successfuly",Toast.LENGTH_SHORT).show();
                    updateListUser();
                }else {
                    Toast.makeText(NhanLen.this,"Delete fail",Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

    }



    //MS001: Hàm gán id wideget
    private void initWideget(){
        tvIdNguoidung=findViewById(R.id.tv_idNguoidung);
        edtId=findViewById(R.id.edt_id);
        edtName=findViewById(R.id.edt_name);
        edtGender=findViewById(R.id.edt_gender);
        edtPhoneNumber=findViewById(R.id.edt_numberphone);
        edtEmail=findViewById(R.id.edt_email);
        edtAddress=findViewById(R.id.edt_adress);
        btnSave=findViewById(R.id.btn_save);
        btnUpdate=findViewById(R.id.btn_update);
        lvUser=findViewById(R.id.lv_danhsach);
    }
    //MS002: Hàm nhận dữ liệu từ cha
    private void Nhan_goi_dl(){
        Intent intent=getIntent();//khởi tạo biến và dùng phương thức getIntent để lấy dữ liệu
        String noidung=intent.getStringExtra("Ten_tk");//lấy dữ liệu bên activity MainActivity qua (đó là tên tk)
        tvIdNguoidung.setText("ID: "+noidung);
    }
    //MS003: hàm đưa luồng dữ liệu
    private void setAdapter(){
        if(customAdapter==null){
            customAdapter=new CustomAdapter(this,R.layout.item_list_user,userList);
            lvUser.setAdapter(customAdapter);
        }else {
            customAdapter.notifyDataSetChanged();//Nếu List có sự thay đổi
            lvUser.setSelection(customAdapter.getCount()-1);//đưa về cuối danh sách, đối tượng thêm mới
        }
    }
    //MS004: khởi tạo User
    private User createUser(){
        String name=edtName.getText().toString();//lấy tên từ edt ngoài màn hình nhập liệu
        String gender=edtGender.getText().toString();
        String phone=edtPhoneNumber.getText().toString();
        String email=edtEmail.getText().toString();
        String address=edtAddress.getText().toString();
        User user=new User(name,gender,phone,email,address);//gán dữ liệu vào đối tượng user
        return user;
    }
    //MS005: Hàm update student
    private void updateListUser(){
        userList.clear();
        userList.addAll(dbManger.getAllUser());
        if(customAdapter!=null){
            customAdapter.notifyDataSetChanged();
        }
    }
}
