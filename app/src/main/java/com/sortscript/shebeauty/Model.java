package com.sortscript.shebeauty;

public class Model {
    String Name, PhoneNumber, Address, Certificate, Experience, Description, Image;

    public Model() {
    }

    public Model(String name, String phoneNumber,
                 String address, String certificate, String experience, String description, String image) {
        Name = name;
        PhoneNumber = phoneNumber;
        Address = address;
        Certificate = certificate;
        Experience = experience;
        Description = description;
        Image = image;
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

    public String getCertificate() {
        return Certificate;
    }

    public void setCertificate(String certificate) {
        Certificate = certificate;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        Experience = experience;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
