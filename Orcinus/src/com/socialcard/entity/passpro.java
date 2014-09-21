package com.socialcard.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 14-5-9.
 */
public class passpro implements Serializable {

    String username;
    String problem;
    String pass;

    public passpro(){

        super();
    }
    public passpro( String username, String problem, String pass){
        this.username=username;
        this.problem=problem;
        this.pass=pass;

    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
