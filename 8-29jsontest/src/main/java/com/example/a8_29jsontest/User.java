package com.example.a8_29jsontest;

/**
 * Created by wuqi on 2017/8/29.
 */

public class User {
    private int age;
    private String username;
    private boolean enabled;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", username='" + username + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
