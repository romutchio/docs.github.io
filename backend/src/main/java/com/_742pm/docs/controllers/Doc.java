package com._742pm.docs.controllers;

public class Doc {

    private final long id;
    private final String name;
    private final byte[] picture;

    public Doc(long id, String name, byte[] picture) {
        this.id = id;
        this.name = name;
        this.picture = picture;
    }

    public long getId() {
        return id;
    }

    public byte[] getContent() {
        return picture;
    }

    public String getName(){
        return name;
    }
}