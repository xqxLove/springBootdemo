package com.newtouch.test;

public class Base {
    @Override
    public String toString() {
        return "Base{" +
                "id='" + id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

}
