package com.newtouch.test;

public class Second extends First{
    @Override
    public String toString() {
        return "Second{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", kind='" + kind + '\'' +
                '}';
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    String kind;
}
