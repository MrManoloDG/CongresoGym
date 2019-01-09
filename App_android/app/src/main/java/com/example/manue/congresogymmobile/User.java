package com.example.manue.congresogymmobile;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private String id;
    private String name;
    private int    plan;
    private Date   date_start;
    private Date   date_end;
    private int    tel;
    private String email;
    private String comment;

    public User(String id, String name, int plan, Date date_start, Date date_end, int tel, String email, String comment) {
        this.id = id;
        this.name = name;
        this.plan = plan;
        this.date_start = date_start;
        this.date_end = date_end;
        this.tel = tel;
        this.email = email;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlan() {
        return plan;
    }

    public void setPlan(int plan) {
        this.plan = plan;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
