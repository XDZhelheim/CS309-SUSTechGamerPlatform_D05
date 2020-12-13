package com.example.springproject.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;

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
    private Time purchaseTime;

    private long rank;

    private String details;

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

    public void setPurchaseTime(Time purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Time getPurchaseTime() {
        return purchaseTime;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    public long getRank() {
        return rank;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }
}
