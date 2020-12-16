package com.example.springproject.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @OneToOne
    private Users user1;
    @NotNull
    @OneToOne
    private Users user2;
    @NotNull
    private Date addTime;

    private String details;

    public void setUser1(Users user1) {
        this.user1 = user1;
    }

    public Users getUser1() {
        return user1;
    }

    public void setUser2(Users user2) {
        this.user2 = user2;
    }

    public Users getUser2() {
        return user2;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public @NotNull Date getAddTime() {
        return addTime;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }
}
