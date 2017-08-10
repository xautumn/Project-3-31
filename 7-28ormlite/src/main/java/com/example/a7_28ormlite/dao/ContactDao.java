package com.example.a7_28ormlite.dao;

import android.content.Context;
import android.util.Log;

import com.example.a7_28ormlite.bean.ContactBean;
import com.example.a7_28ormlite.helper.ContactHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static android.R.id.list;

/**
 * Created by wuqi on 2017/7/28.
 */

public class ContactDao {

    private ContactHelper contactHelper;
    private Dao contactDao;
    public String test = "ContactDao";

    public ContactDao(Context context) {

        contactHelper = ContactHelper.getInstance(context);
        getContactDao();
    }

    public String getString() {
        return "ContactDao";
    }

    public void getContactDao() {
        if (contactHelper != null) {
            try {

                contactDao = contactHelper.getOrmLiteDao(ContactBean.class);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 插入新数据
     *
     * @param contactBean
     */
    public void addContact(ContactBean contactBean) {
        try {
            contactDao.create(contactBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addContactAll(Collection collection) {
        try {
            contactDao.create(collection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 改
     *
     * @param contactBean
     */
    public void updateContact(ContactBean contactBean) {
        try {
            contactDao.update(contactBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删
     *
     * @param contactBean
     */
    public void deleteContact(ContactBean contactBean) {
        try {
            contactDao.delete(contactBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteContactAll(Collection collection) {
        try {
            contactDao.delete(collection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List queryContactAll() throws SQLException {
        List list = contactDao.queryForAll();
        return list;
    }

    /**
     * 查
     */
    public List queryContact(String number, String watchId) throws SQLException {
        QueryBuilder<ContactBean, Integer> queryBuilder = contactDao.queryBuilder();
        //selectColumns("mobileId") 只返回主键+mobileId列信息，切记
        queryBuilder.where()
                .eq("longNumber", number).or().eq("shortNumber", number)
                .or().eq("friendWatchNumber", number).or().eq("mobileId", number)
                .prepare();
        List<ContactBean> query = queryBuilder.query();
        if (query != null && query.size() > 0) {
            for (ContactBean mDbContact : query) {
                if (mDbContact != null && mDbContact.getWatchId() != null && mDbContact.getWatchId().equals(watchId)) {
                    Log.i("ContactDao", "true=" + mDbContact);
                }
            }
        }
        return query;
    }

    public List querySelectColumn(String column) {
        QueryBuilder queryBuilder = contactDao.queryBuilder();
        List list = new ArrayList();
        list.add("watchId");
        list.add("mobileId");
        list.add("longNumber");
        list.add("shortNumber");
        list.add("friendWatchNumber");
        try {
            List query = queryBuilder.distinct()
                    .selectColumns(list)
                    .where().eq("watchId", "55")
                    .and().eq("mobileId", "4b")
                    .query();
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
