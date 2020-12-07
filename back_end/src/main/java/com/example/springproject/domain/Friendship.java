package com.example.springproject.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;

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
    private Time add_time;

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

    public void setAdd_time(Time add_time) {
        this.add_time = add_time;
    }

    public Time getAdd_time() {
        return add_time;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }
}
