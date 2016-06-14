package com.example.myapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.myapp.models.Users;

/**
 * Created by Li on 2016/3/27.
 */
public class UsersData extends SQLiteOpenHelper {
    //定义数据库名
    private static final String DATABASE_NAME = "myDataBase";
    //定义数据库版本号
    private static final int DATABASE_VERSION = 1;
    //定义表名，可以建立多个表
    private static final String TABLE_NAME = "users";
    //定义表的字段名（列名）
    private static final String USERS_UID = "uid";
    private static final String USERS_UNAME = "uname";
    private static final String USERS_UPWD = "pwd";

    private static UsersData myInstance;

    public UsersData(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //定义创建数据表的sql语句
        String CREATE_USERS_TALBE = "CREATE TABLE " + TABLE_NAME + "("
                + USERS_UID + " TEXT PRIMARY KEY,"
                + USERS_UNAME + " TEXT,"
                + USERS_UPWD + " TEXT" +
                ")";
        //执行sql语句
        sqLiteDatabase.execSQL(CREATE_USERS_TALBE);
        //注意，这些语句并不会立即执行，需要有触发机制（getReadableDatabase/getWriteableDatabase）---懒执行


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //版本号变化，删除表再重建
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_NAME + " IF EXISTS");
        onCreate(sqLiteDatabase);

    }

    //用于返回静态实例（对象）
    public static synchronized UsersData getMyInstance(Context context) {
        if (myInstance == null) {
            myInstance = new UsersData(context.getApplicationContext());
        }
        return myInstance;
    }

    //增加users数据到表
    public long add(Users users) {
        //数据是否成功标记
        long flag = 0;
        //定义SQliteDatebase，调用getWritableDatabase()打开数据库（写入方式）
        SQLiteDatabase db = getWritableDatabase();
        //开始事务
        db.beginTransaction();

        try {
            //创建ContentValues对象,ContentValues对象实际上是一个Map<key,values>
            ContentValues values = new ContentValues();
            values.put(USERS_UID, users.getUid());
            values.put(USERS_UNAME, users.getUname());
            values.put(USERS_UPWD, users.getUpwd());

            //执行inset方法,返回长整形数据，-1代表失败，大于0成功
            flag = db.insert(TABLE_NAME, null, values);
            //事务成功
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.v("insert error:", e.getMessage());
        } finally {
            //事务结束
            db.endTransaction();
            db.close();
        }
        return flag;
    }

    //查询--rawQuery
    public Users rawQuertUsersByUid(String uid) {
        Users users = new Users();
        //指定字符串--SQL
        String QUERY_USERS = "SELECT * FROM " + TABLE_NAME;
        //创建SQLiteDataBase对象
        SQLiteDatabase db = getWritableDatabase();
        //通过rawQuert方法查询数据到cursor
        Cursor cursor = db.rawQuery(QUERY_USERS, null);
        //移动指针到第一条记录，如果存在则返回true，否则返回false
        if (cursor.moveToFirst()) {
            //通过字段（列）索引获取查询结果
            users.setUid(cursor.getString(0));
            users.setUname(cursor.getString(1));
            users.setUpwd(cursor.getString(2));

        }
        return users;
    }
}
