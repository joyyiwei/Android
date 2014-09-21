package com.socialcard.entity;

/**
 * Created by Administrator on 14-5-9.
 */
import java.io.Serializable;
public class contacts implements Serializable{

    String name;
    int user_id;
    String tel;
    String company;
    String email;
    String position ;
    String URL;

    public contacts(){

        super();
    }
    public  contacts( String name, int user_id, String tel, String company,  String email, String position , String URL){

        this.name=name;
        this.user_id=user_id;
        this.tel=tel;
        this.company=company;
        this.email=email;
        this.position=position;
        this.URL=URL;


    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
