package com.sortscript.shebeauty;

public class Model1 {
    String image, Name, PhoneNumber, Address, Description;

    public Model1() {
    }

    public Model1(String image, String name, String phoneNumber, String address, String description) {
        this.image = image;
        Name = name;
        PhoneNumber = phoneNumber;
        Address = address;
        Description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}



