package com.example.model;

public class Applicant {
    private String name;
    private int age;
    private boolean valid;

    public Applicant(String name, int age, boolean valid) {
        this.name = name;
        this.age = age;
        this.valid = valid;
    }

    // getter and setter
    public String getName() {
    return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
