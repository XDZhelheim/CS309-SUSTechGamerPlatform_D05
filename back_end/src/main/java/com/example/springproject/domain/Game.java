package com.example.springproject.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(unique = true)
    private String name;
    @NotNull
    private Time create_time;
    @NotNull
    private double price;

    private String intro;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCreate_time(Time create_time) {
        this.create_time = create_time;
    }

    public Time getCreate_time() {
        return create_time;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIntro() {
        return intro;
    }

}
