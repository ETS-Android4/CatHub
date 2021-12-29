package com.example.taptest;

public class PhoneNumberVO {
    private String name;
    private String phone;

    public String getName(){
        return name;
    }

    public void setName(){
        this.name = name;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(){
        this.phone = phone;
    }

    @Override
    public String toString(){
        return "PhoneNumberVO{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
