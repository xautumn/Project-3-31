package com.example.a7_28ormlite.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.example.a7_28ormlite.bean.ContactBean;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by wuqi on 2017/7/28.
 */

public class ContactHelper extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_PATH = "contact.db";

    public ContactHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

        //数据库创建
        try {
            TableUtils.createTable(connectionSource, ContactBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }

    static ContactHelper contactHelper;
    public static synchronized ContactHelper getInstance(Context context) {

        if (contactHelper == null) {
            Log.i("ContactHelper",DATABASE_PATH);
            contactHelper = new ContactHelper(context, DATABASE_PATH, null, 1);
        }
        return contactHelper;
    }

    public synchronized Dao getOrmLiteDao(Class cla) throws SQLException {
        Dao dao = null;
        String className = cla.getSimpleName();

        dao = super.getDao(cla);
        return dao;
    }
}
