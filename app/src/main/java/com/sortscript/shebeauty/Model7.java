package com.sortscript.shebeauty;

public class Model7 {
    String UserName, Email, Description, Reference;

    public Model7(String userName, String email, String description, String Reference) {
        UserName = userName;
        Email = email;
        Description = description;
        this.Reference = Reference;
    }

    public String getReference() {
        return Reference;
    }

    public void setReference(String reference) {
        Reference = reference;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Model7() {


    }
}
