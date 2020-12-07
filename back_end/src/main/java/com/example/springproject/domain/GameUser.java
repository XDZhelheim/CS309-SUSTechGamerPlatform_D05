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
    private char credit_as;
    @NotNull
    private Time purchase_time;

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

    public void setCredit_as(char credit_as) {
        this.credit_as = credit_as;
    }

    public char getCredit_as() {
        return credit_as;
    }

    public void setPurchase_time(Time purchase_time) {
        this.purchase_time = purchase_time;
    }

    public Time getPurchase_time() {
        return purchase_time;
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
