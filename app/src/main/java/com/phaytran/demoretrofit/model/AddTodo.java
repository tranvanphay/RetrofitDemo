package com.phaytran.demoretrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class AddTodo {
    @SerializedName("name")
    private String name;

    @SerializedName("priority")
    private String priority;

    @SerializedName("description")
    private String description;

    @SerializedName("duedate")
    private String dueDate;

    public AddTodo(String name, String priority, String description, String dueDate) {
        this.name = name;
        this.priority = priority;
        this.description = description;
        this.dueDate = dueDate;
    }

    public AddTodo() {
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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
