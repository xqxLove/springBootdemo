package com.newtouch.test;

public class First extends Base{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    @Override
    public String toString() {
        return "First{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
