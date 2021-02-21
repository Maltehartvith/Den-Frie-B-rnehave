package com.company;

import java.util.*;
import java.io.*;


public class Main {

    public static void main(String[] args)throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        LinkedList<Child> childs = new LinkedList<>();
        ArrayList<Child> waitlist = new ArrayList<>();
        ArrayList<Employee> employees = new ArrayList<>();
        LinkedList<Parent> parents = new LinkedList<>();



        Child kid = new Child("Kenny", 4, "100118-0043");
        childs.add(kid);


        mainMenu(scan, childs, waitlist, employees, parents);


    }

    // Metode der indeholder vores main menu
    public static void mainMenu(Scanner scan, LinkedList<Child> childs, ArrayList<Child> waitlist, ArrayList<Employee> employees, LinkedList<Parent> parents) throws FileNotFoundException {
        int answer = 1;

        while (answer != 0) {
            System.out.println("Welcome to Roskilde frie børnehave administrative system\n" +
                    "Type 1 for child menu." +
                    "\nType 2 for parent menu" +
                    "\nType 3 for coworker menu." +
                    "\nType 4 for phone list." +
                    "\nType 0 to terminate program.");
            answer = scan.nextInt();

            switch (answer) {
                case 1:
                    childrensMenu();
                    int canswer = scan.nextInt();

                    while(canswer != 0){
                        if(canswer==1){
                            System.out.println("You have chosen to register a child");
                            System.out.println("-----------------------------------");
                            RegisterChild(scan, childs);


                        }if(canswer==2){
                            Child c1 = childs.get(0);
                            System.out.println("You have chosen to edit a child");
                            System.out.println("-------------------------------");
                            findChildToEdit(scan, childs);


                        }if(canswer==3) {
                            System.out.println("You have chosen to print lists");
                            System.out.println("------------------------------------------");
                            printList(scan, childs, waitlist, employees, parents);


                        }if(canswer==4){
                            System.out.println("You have chose to remove a child from the list");
                            System.out.println("-------------------------------------");
                            deleteChild(scan, childs);
                        }else{
                            //skal der være noget her?
                        }
                        childrensMenu();
                        canswer = scan.nextInt();
                    }
                    break;
                case 2:
                    System.out.println("** Parent Menu **");
                    System.out.println("Type 1 to register parent.");
                    System.out.println("Type 2 to edit a parents information.");
                    System.out.println("Type 0 to return to ");
                    int panswer = scan.nextInt();

                    while(panswer != 0) {
                        if(panswer==1){
                            System.out.println("You have chosen to register parent.");
                            System.out.println("-----------------------------------");
                            parents.add(RegisterParent(scan, childs));
                            panswer = scan.nextInt();
                        }if(panswer==2) {
                            System.out.println("You have chosen to edit a parent.");
                            System.out.println("---------------------------------");
                            //Metode kald
                            panswer = scan.nextInt();
                        }else{
                            System.out.println("You typed something wrong - try again.");
                            panswer = scan.nextInt();
                        }
                    }
                    break;
                case 3:
                    System.out.println("** Employee Menu**");
                    System.out.println("Type 1 to add a new employee");
                    System.out.println("Type 2 to print list of all employees");
                    System.out.println("Type 0 to return to main menu.");
                    int eanswer = scan.nextInt();

                    switch (eanswer) {
                        case 1:
                            System.out.println("You have chosen to add a new employee");
                            System.out.println("-------------------------------------");
                            //Metode kald
                            break;
                        case 2:
                            System.out.println("You have chosen to list all employees");
                            System.out.println("-------------------------------------");
                            //Metode kald
                            break;
                        case 3:
                            System.out.println("You have chosen to remove an employee");
                            System.out.println("-------------------------------------");
                            deleteEmployee(scan, employees);
                        case 0:
                            break;
                        default:
                            System.out.println("Error, returning to main menu");
                            break;
                    }
                    break;
                case 0:
                    writeToFileChild(childs);
                    writeToFileEmployee(employees);
                    writeToFileParent(parents);
                    writeToFileWaitList(waitlist);
                    break;
                default:
                    System.out.println("Fejlmeddelelse 244 - SE MINDRE PORNO Error - returning to main menu");
                    break;
            }
        }


    }

    // Metoder der registrere et nyt Child object, og gemmer det på vores linkedList
    public static Child RegisterChild(Scanner scan, LinkedList<Child> childs) {
        System.out.println("Enter first name of the child");
        String fname = scan.next();
        System.out.println("Enter the last name of the child");
        String lname = scan.next();
        String name = fname + " " + lname;
        System.out.println("Enter age of child");
        int age = scan.nextInt();
        System.out.println("Enter personal number - xxxxxx-xxxx:");
        String personalnumber = scan.next();

        Child kid = new Child(name, age, personalnumber);
        childs.add(kid);

        System.out.println(kid + "\nIs now registred");
        return kid;
    }

    // Metode der registrere et nyt Parent object, og gemmer det på vores LinkedList sammen med childs
    public static Parent RegisterParent(Scanner scan, LinkedList<Child> childs) {
        Parent pa = new Parent();
        while (pa == null) {
            System.out.println("Enter the first name of the parent: ");
            String fname = scan.next();
            System.out.println("Enter the last name of the parent: ");
            String lname = scan.next();
            String name = fname + " " + lname;
            System.out.println("Enter phone number: ");
            int phoNumber = scan.nextInt();
            System.out.println("Enter personal number - xxxxxx-xxxx: ");
            String personalnumber = scan.next();
            System.out.println("Enter the name of the parents child: ");
            String child = scan.next();
            System.out.println("Enter the child personal number with xxxxxx-xxxx: ");
            String childPNumber = scan.next();
            //Hvis det her ikke virker er det muligvis pga getName er både fornavn og efternavn

            for (int i = 0; i < childs.size(); i++) {
                childs.get(i).getName();
                childs.get(i).getCPR();
                if (childs.get(i).getName().equalsIgnoreCase(child) &&
                        childs.get(i).getCPR().equals(childPNumber)) {
                    pa = new Parent(name, phoNumber, personalnumber);
                    pa.setKid(childs.get(i));
                    return pa;
                } else {
                    System.out.println("Something went wrong. Try again");
                }
            }
        }
        return pa;
    }

    // Metode der registrere et nyt Employee object, og gemmer det på vores Employee ArrayList
    public static void RegisterEmployee(Scanner scan, ArrayList<Employee> employees) {
        System.out.println("Enter the first name of employee");
        String fname = scan.next();
        System.out.println("Enter the last name of employee");
        String lname = scan.next();
        String name = fname + " " + lname;
        System.out.println("Enter age: ");
        int age = scan.nextInt();
        System.out.println("Enter personal number - xxxxxx-xxxx: ");
        String personalnumber = scan.next();
        System.out.println("Enter phone number: ");
        int number = scan.nextInt();

        Employee e = new Employee(name, age, personalnumber, number);

        employees.add(e);
    }

    // Metode der skriver vores Child objecter ud i en txt fil
    public static void writeToFileChild(LinkedList<Child> childs) throws FileNotFoundException {
        PrintStream write = new PrintStream(new File("ChildsList.txt"));

        for (int i = 0; i < childs.size(); i++) {
            write.println(childs.get(i).toStringToFile());
        }

        write.close();

    }

    //Metode der gemmer vores parentsliste som et txt dokument
    public static void writeToFileParent(LinkedList<Parent> parents) throws FileNotFoundException {
        PrintStream write = new PrintStream(new File("ParentList.txt"));

        for (int i = 0; i < parents.size(); i++) {
            write.println(parents.get(i).toStringToFile());
        }

        write.close();

    }

    // Metode der skriver vores venteliste ud i en txt fil
    public static void writeToFileWaitList(ArrayList<Child> waitlist) throws FileNotFoundException {
        PrintStream write = new PrintStream(new File("WaitList.txt"));

        for (int i = 0; i < waitlist.size(); i++) {
            write.println(waitlist.get(i).toStringToFile());
        }

        write.close();

    }

    // Metode der skriver vores Employee objecter ud i en txt fil
    public static void writeToFileEmployee(ArrayList<Employee> employees) throws FileNotFoundException {
        PrintStream write = new PrintStream(new File("EmployeeList.txt"));

        for (int i = 0; i < employees.size(); i++) {
            write.println(employees.get(i).toStringToFile());
        }

        write.close();

    }

    //Metode der læser vores Child objecter fra en fil, og tilføjer objecterne til vores LinkedList
    public static void readChildFromFile(Scanner scan, LinkedList<Child> childs) throws FileNotFoundException {
        Scanner load = new Scanner(new File("ChildsList.txt"));
        while (scan.hasNextLine()) {

            String fname = load.next();
            String lname = load.next();
            String name = fname + " " + lname;
            int age = load.nextInt();
            String CPR = load.next();

            Child c = new Child(name, age, CPR);

            childs.add(c);

        }
    }

    public static void readParentFromFile(Scanner scan, LinkedList<Parent> parents, LinkedList<Child> childs) throws FileNotFoundException {
        Scanner load = new Scanner(new File("ParentsList.txt"));
        while (scan.hasNextLine()) {

            String fname = load.next();
            String lname = load.next();
            String name = fname + " " + lname;
            String CPR = load.next();
            int number = load.nextInt();

            String CPRkid = load.next();
            for(int i = 0; i < childs.size(); i++){
                childs.get(i).getCPR();
                if(CPRkid.equals(childs.get(i).getCPR())){
                    Parent p = new Parent(name, number, CPR);
                    p.setKid(childs.get(i));
                    parents.add(p);
                }/*   måske??

            Parent p = new Parent(name, number, CPR);

            parents.add(p);*/
            }
        }
    }

    /* public static void readWaitListFromFile(Scanner scan, ArrayList<Child> waitlist) throws FileNotFoundException {
        Scanner load = new Scanner(new File("WaitList.txt.txt"));
        while (scan.hasNextLine()) {

            String name = load.next();
            int age = load.nextInt();
            String CPR = load.next();

            Child c = new Child(name, age, CPR);

            childs.add(c);

        }
    }*/

    public static void readEmployeeFromFile(Scanner scan, ArrayList<Employee> employees) throws FileNotFoundException {
        Scanner load = new Scanner(new File("EmployeeList.txt"));
        while (scan.hasNextLine()) {

            String fname = load.next();
            String lname = load.next();
            String name = fname + " " + lname;
            int age = load.nextInt();
            int number = load.nextInt();
            String CPR = load.next();


            Employee e = new Employee(name, age, CPR, number);

            employees.add(e);
        }
    }

    //Metode der udskriver vores Child list
    public static void printList(Scanner scan, LinkedList<Child> childs, ArrayList<Child> waitlist,
                                 ArrayList<Employee> employees, LinkedList<Parent> parent) {
        System.out.println("Which list do you want see?\n---------------------------\n" +
                "1. Childrens list.\n2.The waiting list.\n3.The parent phone list.\n4. The employee list.\n" +
                "0. Exit to main menu.");
        String answer = scan.next();
        while (!answer.equals("0")) {
            if (answer.equals("1")) {
                for (int i = 0; i < childs.size(); i++) {
                    Collections.sort(childs);
                    System.out.println(childs.get(i));
                }
                answer = scan.next();
            }if (answer.equals("2")) {
                for (int i = 0; i < waitlist.size(); i++) {
                    System.out.println(waitlist.get(i));
                }
                answer = scan.next();
            }if (answer.equals("3")) {
                TreeMap<String, Integer> phoneList = new TreeMap<>();
                for(Parent par: parent){
                    phoneList.put(par.getName(), par.getNumber());
                }
                System.out.println(phoneList);
                answer = scan.next();
            }if (answer.equals("4")) {
                for (int i = 0; i < employees.size(); i++) {
                    Collections.sort(employees);
                    System.out.println(employees.get(i));
                }
                answer = scan.next();
            }else{
                System.out.println("You typed something wrong, try again.");
                answer = scan.next();
            }
        }
    }



    // Metode der udskriver vores Venteliste
   /* public static void printWaitList(ArrayList<String> waitlist) {
        for (int i = 0; i < waitlist.size(); i++) {
            System.out.println(waitlist.get(i));
        }
    }*/

    // Metode der udskriver vores Employee liste
   /* public static void printEmployeeList(ArrayList<Employee> employees) {
        for (int i = 0; i < employees.size(); i++) {
            System.out.println(employees.get(i));
        }
    }*/

    // Metode der finder et Child object for at redigere
    //skal flettes sammen med editChild
    public static void findChildToEdit(Scanner scan, LinkedList<Child> childs) {
        System.out.print("Enter the first name of the child you wish to edit: ");
        String name = scan.next();

        for (int i = 0; i < childs.size(); i++) {
            if (childs.get(i).getName().equals(name)) {
                System.out.println("\n" + childs.get(i) + "\n");
                editChild(scan, childs.get(i));
            }
        }
    }

    // Metode der redigere et child object
    public static void editChild(Scanner scan, Child c1) {
        int svar = 1;
        while (svar != 0) {
            System.out.println("Type 1 to edit name.\nType 2 to edit age.\nType 3 to edit CPR\nType 0 to return\n");
            svar = scan.nextInt();

            switch (svar) {
                case 1:
                    System.out.println("You have chosen to edit name\n----------------------------\nEnter new name: ");
                    String newName = scan.next();
                    c1.setName(newName);
                    break;
                case 2:
                    System.out.println("You have chosen to edit age\n----------------------------\nEnter new age: ");
                    int newAge = scan.nextInt();
                    c1.setAge(newAge);
                    break;
                case 3:
                    System.out.println("You have chosen to edit CPR\n---------------------------\nEnter new CPR: ");
                    String newCPR = scan.next();
                    c1.setCPR(newCPR);
                    break;
                case 0:
                    System.out.println("Returning to main menu.");
                    break;
            }
        }
    }

    //metode til at fjerne et barn
    public static void deleteChild(Scanner scan, LinkedList<Child> childs){
        for(int i = 0; i < childs.size(); i++){
            System.out.println(childs.get(i));
        }
        System.out.println("Enter personal number of the child you want to remove from the list");
        boolean foundCPR = false;
        String personalNumber = scan.next();
        for (int i = 0; i < childs.size(); i++){
            if(childs.get(i).getCPR() == personalNumber){
                childs.remove(i);
                foundCPR = true;
                break;
            }
        }
        if(!foundCPR){
            System.out.println("This personal number does not exists");
        }
        System.out.println(childs);
    }

    //metode til at fjerne en ansat
    public static void deleteEmployee(Scanner scan,ArrayList<Employee> employees){
        for(int i = 0; i < employees.size(); i++){
            System.out.println(employees.get(i));
        }
        System.out.println("Enter personal number of the employee you want to remove from the list");
        boolean foundCPR = false;
        String personalNumber = scan.next();
        for (int i = 0; i < employees.size(); i++){
            if(employees.get(i).getCPR() == personalNumber){
                employees.remove(i);
                foundCPR = true;
                break;
            }
        }
    }
    public static void printPhoneList(LinkedList<Parent> parent){
        TreeMap<String, Integer> phoneList = new TreeMap<>();
        for(Parent par: parent){
            phoneList.put(par.getName(), par.getNumber());
        }
        System.out.println(phoneList);
    }
    public static void childrensMenu(){
        System.out.printf("%21s %n", "** Child Menu **");
        System.out.println("\nType 1 to register a new child.\n" +
                "Type 2 to edit a childs information.\nType 3 to print lists.\n" +
                "Type 4 to remove a child from the list.\nType 0 to return to main menu.");
    }

}

