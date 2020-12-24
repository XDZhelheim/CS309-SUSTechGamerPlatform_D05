package com.example.springproject.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class GameUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @OneToOne
    private Game game;
    @NotNull
    @OneToOne
    private Users users;
    @NotNull
    private char creditAs;
    @NotNull
    private Date purchaseTime;

    private double score;

    private String details;
//    @NotNull
//    @OneToMany
//    private Set<Users> good;
//    @NotNull
//    @OneToMany
//    private Set<Users> bad;

//    public void newGood(Users u) {
//        good.add(u);
//    }
//
//    public void newBad(Users u) {
//        bad.add(u);
//    }
//
//    public int goods() {
//        return good.size();
//    }
//
//    public int bads() {
//        return bad.size();
//    }

    public GameUser(){
        details = "";
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Users getUsers() {
        return users;
    }

    public void setCreditAs(char creditAs) {
        this.creditAs = creditAs;
    }

    public char getCreditAs() {
        return creditAs;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    private static final String split = "////";

    public void addDetails(String details) {
        if (this.details.length() == 0) {
            this.details = details;
        } else {
            this.details += split + details;
        }
    }

    public String[] getDetails() {
        return details.split(split);
    }
}
