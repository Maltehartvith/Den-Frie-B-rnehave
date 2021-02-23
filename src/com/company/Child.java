package com.company;

public class Child implements Comparable<Child>{
    private String name;
    private int age;
    private String CPR;


    public Child(String name, String CPR) {
        this.name = name;
        this.CPR = CPR;
    }

    public Child(String name, int age, String CPR) {
        this.name = name;
        this.age = age;
        this.CPR = CPR;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCPR() {
        return CPR;
    }

    public void setCPR(String CPR) {
        this.CPR = CPR;
    }

    public String toString(){
        return "Childs name: " + name + "\nAge: " + age + "\nPersonal number: " + CPR + "\n";
    }
    public String toStringToFile(){ return  name + " " + age + " " + CPR;}
    public String toStringCPRname(){return "Name: " + name + "\nPersonal number: " + CPR + "\n";}
    public int compareTo(Child other) {
        return this.name.compareTo(other.name);
    }
}
