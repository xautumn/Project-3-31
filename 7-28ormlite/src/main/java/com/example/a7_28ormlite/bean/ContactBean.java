package com.example.a7_28ormlite.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by wuqi on 2017/7/28.
 * db关系映射类
 */

@DatabaseTable(tableName = "contact_info")
public class ContactBean {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField (columnName = "watchId")
    private String watchId;

    @DatabaseField (columnName = "mobileId")
    private String mobileId;

    @DatabaseField (columnName = "longNumber")
    private String longNumber;

    @DatabaseField (columnName = "shortNumber")
    private String shortNumber;

    @DatabaseField (columnName = "friendWatchNumber")
    private String friendWatchNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWatchId() {
        return watchId;
    }

    public String getMobileId() {
        return mobileId;
    }

    public void setMobileId(String mobileId) {
        this.mobileId = mobileId;
    }

    public void setWatchId(String watchId) {
        this.watchId = watchId;
    }

    public String getLongNumber() {
        return longNumber;
    }

    public void setLongNumber(String longNumber) {
        this.longNumber = longNumber;
    }

    public String getShortNumber() {
        return shortNumber;
    }

    public void setShortNumber(String shortNumber) {
        this.shortNumber = shortNumber;
    }

    public String getFriendWatchNumber() {
        return friendWatchNumber;
    }

    public void setFriendWatchNumber(String friendWatchNumber) {
        this.friendWatchNumber = friendWatchNumber;
    }

    @Override
    public String toString() {
        return "ContactBean{" +
                "id=" + id +
                ", watchId='" + watchId + '\'' +
                ", mobileId='" + mobileId + '\'' +
                ", longNumber='" + longNumber + '\'' +
                ", shortNumber='" + shortNumber + '\'' +
                ", friendWatchNumber='" + friendWatchNumber + '\'' +
                '}';
    }
}
