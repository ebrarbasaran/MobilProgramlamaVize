package com.example.phonebook;

import android.graphics.Bitmap;

public class Contact {
    private String name;
    private String phone;
    private Bitmap photo = null;

    public Contact(){

    }
    public Contact(String name,String phone,Bitmap photo){
        this.name=name;
        this.phone=phone;
        this.photo=photo;
    }

    public String getName() {
        return  name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
