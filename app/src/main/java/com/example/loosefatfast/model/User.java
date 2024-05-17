package com.example.loosefatfast.model;

public class User {
    private String userId;
    private int age;
    private String gender;
    private double height;
    private double weight;
    private double targetWeight;

    // Constructeur
    public User( int age, String gender, double height, double weight, double targetWeight) {
        this.userId = userId;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.targetWeight = targetWeight;
    }

    // Getters et setters
    public String getUserId() {
        return String.valueOf(1);
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;
    }
}
