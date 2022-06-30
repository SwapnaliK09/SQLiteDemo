package com.example.databasedemo;

public class UserModel {

    private String API;
    private String Description;
    private String Auth;

    UserModel(){

    }

    public UserModel(String API, String description, String auth) {
        this.API = API;
        Description = description;
        Auth = auth;
    }

    public String getAPI() {
        return API;
    }

    public void setAPI(String API) {
        this.API = API;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getAuth() {
        return Auth;
    }

    public void setAuth(String auth) {
        Auth = auth;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "API='" + API + '\'' +
                ", Description='" + Description + '\'' +
                ", Auth='" + Auth + '\'' +
                '}';
    }
}
