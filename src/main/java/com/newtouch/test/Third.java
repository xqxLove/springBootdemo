package com.newtouch.test;

public class Third extends Second{
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    String color;

    @Override
    public String toString() {
        return "Third{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", kind='" + kind + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
