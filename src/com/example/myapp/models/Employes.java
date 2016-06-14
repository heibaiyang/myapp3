package com.example.myapp.models;

/**
 * Created by Li on 2016/3/27.
 */
public class Employes {
    private String id;
    private String name;

    public Employes() {
    }

    public Employes(String id, String name) {
        this.id = id;
        this.name = name;
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
}
