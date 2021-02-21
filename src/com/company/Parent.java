package com.company;

public class Parent {
    private String name;
    private int number;
    private String CPR;
    Child kid;


    public Parent(){

    }
    public Parent(String name, int number, String CPR) {
        this.name = name;
        this.number = number;
        this.CPR = CPR;
        this.kid = kid;
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

    public Child getKid() {
        return kid;
    }

    public void setKid(Child kid) {
        this.kid = kid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Parents name: " + name + "\nPersonal number: " + CPR +
                "\nPhone number: " + number +"\nChild: " + kid +"\n";
    }
    public String toStringToFile(){
        return name + " " + CPR + " " + number + " " + kid.getCPR() + "\n";
    }
}
