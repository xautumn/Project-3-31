package com.example.a7_28ormlite;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.a7_28ormlite.bean.ContactBean;
import com.example.a7_28ormlite.dao.ContactDao;
import com.example.a7_28ormlite.dao.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private ContactDao contactDao;
    private List<ContactBean> contactBeenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"onCreate");
        getSupportActionBar().hide();
        contactDao = new ContactDao(this);

        ContactDao test= new test(this);
        Log.i(TAG,"--"+test.test);
        Log.i(TAG,"--00--"+test.getString());

        contactBeenList = new ArrayList<>();
        Debug.startMethodTracing("ormLite");
        queryAll(null);
        queryColumn(null);
        Debug.stopMethodTracing();
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

        ContactBean contactBean4 = new ContactBean();
        contactBean4.setWatchId("55");
        contactBean4.setMobileId("4b");
        contactBean4.setFriendWatchNumber("1b");
        contactBean4.setLongNumber("4d");
        contactBean4.setShortNumber("4e");

        ContactBean contactBean5 = new ContactBean();
        contactBean5.setWatchId("55");
        contactBean5.setMobileId("4b");
        contactBean5.setFriendWatchNumber("1b");
        contactBean5.setLongNumber("4d");
        contactBean5.setShortNumber("4e");

        ContactBean contactBean6 = new ContactBean();
        contactBean6.setWatchId("55");
        contactBean6.setMobileId("3323");
        contactBean6.setFriendWatchNumber("1b");
        contactBean6.setLongNumber("4d");
        contactBean6.setShortNumber("4e");

        contactBeenList.add(contactBean4);
        contactBeenList.add(contactBean5);
        contactBeenList.add(contactBean6);

        contactDao.addContactAll(contactBeenList);


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

    public void queryColumn(View v) {
        List list = contactDao.querySelectColumn("watchId");
        Log.i(TAG,"list = "+list);
    }

    public void deleteAll(View v) {
        contactDao.deleteContactAll(contactBeenList);
    }
}
