package com.example.taptest;

public class PhoneNumberVO {
    private String name;
    private String phone;
    //private int photo;
    public PhoneNumberVO() {}
    public PhoneNumberVO(String names, String phones) {
        name = names;
        phone = phones;
    }

    public String getName(){
        return name;
    }

    public void setName(String names){
        this.name = names;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phones){
        this.phone = phones;
    }

   /* public int getPhoto(){
        return photo;
    }

    public void setPhoto(){
        this.photo = photo;
    }

    */

    @Override
    public String toString(){
        return "PhoneNumberVO{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
