package model;

public class User {
    private String phoneID;

    private String password;

    public User() {

    }
    public User(String phoneID, String password){
        this.phoneID=phoneID;
        this.password=password;
    }

    public String getPhoneID() {
        return phoneID;
    }

    public String getPassword() {
        return password;
    }

    public void setPhoneID(String phoneID) {
        this.phoneID = phoneID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}