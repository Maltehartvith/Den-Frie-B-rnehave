package com.company;

public class Employee implements Comparable<Employee>{
    private String name;
    private int age;
    private String CPR;
    private int number;

    public Employee(String name, int age, String CPR, int number) {
        this.name = name;
        this.CPR = CPR;
        this.age = age;
        this.number = number;
    }

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

    public String getCPR() {
        return CPR;
    }

    public void setCPR(String CPR) {
        this.CPR = CPR;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String toString() {
        return "Employee name: " + name + "\nAge: " + age + "\nPersonal number: " +
                CPR + "\nPhone number: " + number;
    }
    public int compareTo(Employee o) {
        return this.name.compareTo(o.name);
    }
}
