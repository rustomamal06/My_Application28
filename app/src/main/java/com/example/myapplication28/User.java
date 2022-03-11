package com.example.myapplication28;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class User {
    protected String fullname;
  protected String gmail;
  protected String username;
  protected String image;
  protected String age;
  protected String key;


    public User(String fullname, String gmail, String username, String age) {
        this.fullname = fullname;
        this.gmail = gmail;
        this.username = username;
        this.age = age;
    }

    public User() {
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }



}

