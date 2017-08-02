package com.example.a7_28ormlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.a7_28ormlite.bean.ContactBean;
import com.example.a7_28ormlite.dao.ContactDao;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"onCreate");
        contactDao = new ContactDao(this);

    }

    public void insert(View v) {

        ContactBean contactBean = new ContactBean();
        contactBean.setWatchId("1a");
        contactBean.setMobileId("1b");
        contactBean.setFriendWatchNumber("1c");
        contactBean.setLongNumber("1d");
        contactBean.setShortNumber("1e");

        ContactBean contactBean1 = new ContactBean();
        contactBean1.setWatchId("2a");
        contactBean1.setMobileId("2b");
        contactBean1.setFriendWatchNumber("2c");
        contactBean1.setLongNumber("2d");
        contactBean1.setShortNumber("2e");

        ContactBean contactBean2 = new ContactBean();
        contactBean2.setWatchId("3a");
        contactBean2.setMobileId("3b");
        contactBean2.setFriendWatchNumber("3c");
        contactBean2.setLongNumber("3d");
        contactBean2.setShortNumber("3e");

        ContactBean contactBean3 = new ContactBean();
        contactBean3.setWatchId("4c");
        contactBean3.setMobileId("4b");
        contactBean3.setFriendWatchNumber("1b");
        contactBean3.setLongNumber("4d");
        contactBean3.setShortNumber("4e");

        contactDao.addContact(contactBean);
        contactDao.addContact(contactBean1);
        contactDao.addContact(contactBean2);
        contactDao.addContact(contactBean3);

    }
    public void queryAll(View v) {
        List list1 = null;
        try {
            list1 = contactDao.queryContactAll();
            Log.i(TAG,"all list"+list1.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void query(View v) {
        try {
            List list = contactDao.queryContact("1b", "1a");
            Log.i(TAG,list.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
