package com.phaytran.demoretrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Todo {
    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("priority")
    private String priority;

    @SerializedName("description")
    private String description;

    @SerializedName("duedate")
    private Date duedate;

    public Todo(Integer id, String name, String priority, String description, Date duedate) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.description = description;
        this.duedate = duedate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }
}
