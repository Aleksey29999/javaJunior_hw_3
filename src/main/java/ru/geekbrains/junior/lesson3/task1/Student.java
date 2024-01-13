package ru.geekbrains.junior.lesson3.task1;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Student implements Serializable {

    public Student(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }

    private String name;
    private int age;

    @JsonIgnore
    transient double GPA;


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double GPA() {
        return GPA;
    }
}
