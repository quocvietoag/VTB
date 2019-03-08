package com.giuseviet.danhb.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.giuseviet.danhb.model.User;

import java.util.ArrayList;
import java.util.List;

public class DBManger extends SQLiteOpenHelper {

    //Khởi tạo các biến cần thiết
    private static final String DATABASE_NAME="MANAGER_USER";//TÊN CSDL
    private static final String TABLE_NAME="USER";//TÊN BẢNG
    private static final String ID="id";//Id người dùng
    private static final String NAME="name";//tên người dùng
    private static final String GENDER="gender";//giới tính
    private static final String PHONE_NUMBER="phone";//số điện thoại
    private static final String EMAIL="email";//địa chỉ email
    private static final String ADDRESS="adress";//địa chỉ

    //Biến phiên bảng dữ liệu
    private static final int VERSION=1;

    //Biến khởi tạo thông báo
    private final String TAG="Manager: ";
    //biến Context;
    Context context;

    //Khởi tạo câu truy vấn tạo bảng
    private String SQLquery="CREATE TABLE "+ TABLE_NAME +" ("+
             ID + " integer primary key, "+
             NAME +" TEXT, "+
             GENDER + " TEXT, "+
             PHONE_NUMBER + " TEXT, "+
             EMAIL + " TEXT, "+
             ADDRESS + " TEXT)";


    public DBManger(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLquery);//thực hiện câu try vấn bằng execSQL
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    //Khởi tạo phương thức thêm người dùng
    public void addUser(User user){
        SQLiteDatabase db=this.getWritableDatabase();//phương thức mở dữ liệu để viết
        ContentValues values=new ContentValues();//khởi tạo biến lấy dữ liệu
        values.put(NAME,user.getmName());
        values.put(GENDER,user.getmGender());
        values.put(PHONE_NUMBER,user.getmPhoneNumber());
        values.put(EMAIL, user.getmEmail());
        values.put(ADDRESS,user.getmAddress());
        db.insert(TABLE_NAME,null,values);//THêm dữ liệu vào bảng
        db.close();
    }
    ContentValues values=new ContentValues();//khởi tạo biến lấy dữ liệu
    ContentValues values1=new ContentValues();
    //add user auto
    public void addUser_auto(){
        SQLiteDatabase db=this.getWritableDatabase();//phương thức mở dữ liệu để viết
        dt1();
        dt2();
        db.insert(TABLE_NAME,null,values);//THêm dữ liệu vào bảng
        db.insert(TABLE_NAME,null,values1);//THêm dữ liệu vào bảng
        db.close();
    }

    //đối tượng
    private  void dt1(){
        values.put(NAME,"Mai Quốc Việt");
        values.put(GENDER,"Nam");
        values.put(PHONE_NUMBER,"0932 937 025");
        values.put(EMAIL, "giuseqviet@gmail.com");
        values.put(ADDRESS,"Thanh bình, Đồng tháp");
    }
    private  void dt2(){
        values1.put(NAME,"Lưu Sĩ Quí");
        values1.put(GENDER,"Nữ");
        values1.put(PHONE_NUMBER,"0932 937 xxx");
        values1.put(EMAIL, "petersiqui@gmail.com");
        values1.put(ADDRESS,"Thanh bình, Đồng tháp");
    }

    //Khởi tạo hàm update user
    public  int updateUser(User user){
        SQLiteDatabase db=this.getWritableDatabase();//mở dữ liệu
        ContentValues values =new ContentValues();
        values.put(NAME,user.getmName());
        values.put(GENDER,user.getmGender());
        values.put(PHONE_NUMBER,user.getmPhoneNumber());
        values.put(EMAIL, user.getmEmail());
        values.put(ADDRESS,user.getmAddress());
        int update=db.update(TABLE_NAME,values,ID + "=?",new String[]{String.valueOf(user.getmID())});
        return update;
    }

    //Hàm xóa người dùng
    public int deleteUser(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,ID + "=?",new String[]{String.valueOf(id)});
    }

    //Khởi tạo hàm lấy ds người dùng
    public List<User>getAllUser(){
        List<User>listUser=new ArrayList<>();//Khởi tạo một list danh sách người dùng
        String selectQuery=" SELECT * FROM "+ TABLE_NAME;//TRUY VÂN DỮ LIỆU
        SQLiteDatabase db=this.getWritableDatabase();//mở cổng ghi dữ liệu
        Cursor cursor=db.rawQuery(selectQuery,null);//gán con trỏ cho Cursor
        if(cursor.moveToFirst()){
            do {
                User user=new User();//Khởi tạo một người dùng mới
                user.setmID(cursor.getInt(0));//lấy dữ liệu
                user.setmName(cursor.getString(1));
                user.setmGender(cursor.getString(2));
                user.setmPhoneNumber(cursor.getString(3));
                user.setmEmail(cursor.getString(4));
                user.setmAddress(cursor.getString(5));
                listUser.add(user);//đưa các dữ liệu vừa lấy vào List
            }while (cursor.moveToNext());
        }
        db.close();
        return listUser;
    }


}
