package com.company;

import java.io.PrintStream;

import static java.lang.System.*;

public class Employee implements Comparable<Employee>{
    private String name;
    private int number;
    private String CPR;

    public Employee(String name, int number, String CPR) {
        this.name = name;
        this.number = number;
        this.CPR = CPR;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String toString() {
        return "Employee name: " + name + "\nPhone number: " + number +
                "\nPersonal number: " + CPR+"\n" ;
    }
    public String toStringToFile() {
        return name + " " + number + " " + CPR;
    }
    public String toStringCPRname(){return "Name: " + name + "\nPersonal number: " + CPR + "\n";}
    public int compareTo(Employee other) {
        return this.name.compareTo(other.name);
    }
    public String toStringName(){
        String name = getName();
        String newName = "";
        for(int i = 0; i < name.length(); i++){
             newName= name.substring(0, name.indexOf(" ")+2);
            return newName;
        }
        return newName;
    }
}
