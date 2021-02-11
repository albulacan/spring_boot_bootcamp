package com.springboot.bootcamp.models;

import java.sql.Timestamp;

public class ToDo {

    private long id;
    private String name;
    private String description;
    private Timestamp createdOn;

    public ToDo() {
    }

    public ToDo(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdOn = new Timestamp(System.currentTimeMillis());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public void setCreatedOn() {
        this.createdOn = new Timestamp(System.currentTimeMillis());
    }
}
