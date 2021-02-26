package com.company;

import java.util.*;
import java.io.*;


public class Main {

    public static void main(String[] args) throws IOException, NullPointerException {

        Scanner scan = new Scanner(System.in);
        LinkedList<Child> childs = readChildFromFile();
        ArrayList<Child> waitlist = readWaitListFromFile();;
        ArrayList<Employee> employees = readEmployeeFromFile();
        LinkedList<Parent> parents = readParentFromFile(childs);
        String[][] scheduleArray = new String[6][6];

        mainMenu(scan, childs, waitlist, employees, parents, scheduleArray);


    }

    // Metode der indeholder vores main menu
    public static void mainMenu(Scanner scan, LinkedList<Child> childs, ArrayList<Child> waitlist,
                                ArrayList<Employee> employees, LinkedList<Parent> parents, String[][] scheduleArray)
            throws IOException {
        int answer = 1;

        while (answer != 0) {
            printMainMenu();
            answer = scan.nextInt();

            switch (answer) {
                case 1:
                    childrensMenu();
                    int cAnswer = scan.nextInt();

                    while (cAnswer != 0) {
                        if (cAnswer == 1) {
                            System.out.println("You have chosen to register a child");
                            System.out.println("-----------------------------------");
                            RegisterChild(scan, childs);

                        }if (cAnswer == 2) {
                            Child c1 = childs.get(0);
                            System.out.println("You have chosen to edit a child");
                            System.out.println("-------------------------------");
                            editChild(scan, childs);

                        }if (cAnswer == 3) {
                            System.out.println("You have chose to remove a child from the list");
                            System.out.println("-------------------------------------");
                            deleteChild(scan, childs);

                        }if (cAnswer == 4) {
                            System.out.println("You have chosen to add a child to the waiting list." +
                                    "\n----------------------------------------------------\n");
                            RegisterChildToWaitList(scan, waitlist);
                        } else {
                            //skal der være noget her?
                        }
                        childrensMenu();
                        cAnswer = scan.nextInt();
                    }
                    break;
                case 2:
                    printParentsMenu();
                    int pAnswer = scan.nextInt();
                    while (pAnswer != 0) {
                        if (pAnswer == 1) {
                            System.out.println("You have chosen to register parent.");
                            System.out.println("-----------------------------------");
                            parents.add(RegisterParent(scan, childs));

                        }if (pAnswer == 2) {
                            System.out.println("You have chosen to edit a parent.");
                            System.out.println("---------------------------------");
                            editParent(scan, parents, childs);

                        }if(pAnswer == 3) {
                            System.out.println("You have chosen to delete a parent");
                            System.out.println("----------------------------------");
                            deleteParent(scan, parents);
                        }
                        printParentsMenu();
                        pAnswer = scan.nextInt();
                    }
                    writeToFileParent(parents);
                    break;

                case 3:
                    printEmployeesMenu();
                    int eAnswer = scan.nextInt();
                    while (eAnswer != 0) {
                        if (eAnswer == 1) {
                            System.out.println("You have chosen to add a new employee");
                            System.out.println("-------------------------------------");
                            RegisterEmployee(scan, employees);

                        }if (eAnswer == 2) {
                            System.out.println("You have chosen to edit an employee");
                            System.out.println("-----------------------------------");
                            Employee e1 = employees.get(0);
                            editEmployee(scan, employees);

                        }if (eAnswer == 3) {
                            System.out.println("You have chosen to remove an employee");
                            System.out.println("-------------------------------------");
                            deleteEmployee(scan, employees);
                        }else if (eAnswer == 0) {
                            break;
                        }
                        printEmployeesMenu();
                        eAnswer = scan.nextInt();
                    }


                    break;
                case 4:
                    printList(scan, childs, waitlist, employees, parents);
                    break;
                case 5:
                    System.out.println("What do you want to do?\n1. Create new schedule.\n2. Print the current schedule");
                    int sAnswer = scan.nextInt();
                    if(sAnswer == 1) {
                        schedule(scheduleArray, employees.toArray(new Employee[10]), scan);
                    }if(sAnswer == 2){
                        readScheduleFromFile(scheduleArray);
                }
                    break;
                case 0:
                    writeToFileChild(childs);
                    writeToFileEmployee(employees);
                    writeToFileParent(parents);
                    writeToFileWaitList(waitlist);
                    writeToFileSchedule(scheduleArray);
                    break;
                default:
                    System.out.println("Fejlmeddelelse 244 - Error - returning to main menu");
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

    // Metode der registrere et nyt child object til vente listen
    public static Child RegisterChildToWaitList(Scanner scan, ArrayList<Child> waitList) {
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
        waitList.add(kid);

        System.out.println(kid + "\nIs now registred on the waitlist.");
        return kid;
    }

    // Metode der registrere et nyt Parent object, og gemmer det på vores LinkedList sammen med childs
    public static Parent RegisterParent(Scanner scan, LinkedList<Child> childs) {

        System.out.println("Enter the first name of the parent: ");
        String fname = scan.next();
        System.out.println("Enter the last name of the parent: ");
        String lname = scan.next();
        String name = fname + " " + lname;
        System.out.println("Enter phone number: ");
        int phoNumber = scan.nextInt();
        System.out.println("Enter personal number - xxxxxx-xxxx: ");
        String personalnumber = scan.next();
        System.out.println("Enter the child personal number with xxxxxx-xxxx: ");
        String childPNumber = scan.next();
        //Hvis det her ikke virker er det muligvis pga getName er både fornavn og efternavn
        Parent pa = new Parent(name, phoNumber, personalnumber);
        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).getCPR();
            if (childs.get(i).getCPR().equals(childPNumber)) {
                pa.setKid(childs.get(i));
                System.out.println(pa + "was created");
                return pa;
            }
        }
        if (pa.getKid() == null) {
            System.out.println("There wasn't found a child with that personal number.\nTry again.");
        }
        System.out.println(pa + "was created");
        return pa;
    }

    // Metode der registrere et nyt Employee object, og gemmer det på vores Employee ArrayList
    public static void RegisterEmployee(Scanner scan, ArrayList<Employee> employees) {
        System.out.println("Enter the first name of employee");
        String fname = scan.next();
        System.out.println("Enter the last name of employee");
        String lname = scan.next();
        String name = fname + " " + lname;
        System.out.println("Enter personal number - xxxxxx-xxxx: ");
        String personalnumber = scan.next();
        System.out.println("Enter phone number: ");
        int number = scan.nextInt();

        Employee e = new Employee(name, number, personalnumber);

        employees.add(e);
    }

    //public static createWorkShifts (Scanner scan, ArrayList<Employee> employees) {

    // }

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

    public static void writeToFileSchedule(String[][] arr) throws FileNotFoundException {
        try{
            PrintStream output = new PrintStream(new File("Schedule.txt"));
            for (int i = 0; i < arr.length; i++) {
                String s = "";
                for (int j = 0; j < arr[i].length; j++) {
                    s += arr[i][j] + ";";
                }
                output.println(s);
            }
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Metode der læser vores Child objecter fra en fil, og tilføjer objecterne til vores LinkedList
    public static LinkedList<Child> readChildFromFile() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("ChildsList.txt"));
        LinkedList<Child> childs = new LinkedList<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            Scanner load = new Scanner(line);
            String fname = load.next();
            String lname = load.next();
            String name = fname + " " + lname;
            int age = load.nextInt();
            String CPR = load.next();

            Child c = new Child(name, age, CPR);

            childs.add(c);

        }
        return childs;
    }

    public static LinkedList<Parent> readParentFromFile(LinkedList<Child> childs) throws FileNotFoundException {
        LinkedList<Parent> parents = new LinkedList<>();
        Scanner scan = new Scanner(new File("ParentList.txt"));
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            Scanner load = new Scanner(line);

            String fname = load.next();
            String lname = load.next();
            String name = fname + " " + lname;
            String CPR = load.next();
            int number = load.nextInt();
            String CPRkid = load.next();

            for (int i = 0; i < childs.size(); i++) {
                childs.get(i).getCPR();
                if (CPRkid.equals(childs.get(i).getCPR())) {
                    Parent p = new Parent(name, number, CPR);
                    p.setKid(childs.get(i));
                    parents.add(p);
                    break;
                }
                if (!CPRkid.equals(childs.get(i).getCPR())) {
                    Parent p = new Parent(name, number, CPR);
                    p.setKid(null);
                    parents.add(p);
                    break;
                }
            }

        }
        return parents;
    }

    public static ArrayList<Child> readWaitListFromFile() throws FileNotFoundException {
        ArrayList<Child> waitlist = new ArrayList<>();
        Scanner scan = new Scanner(new File("waitList.txt"));
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            Scanner load = new Scanner(line);
            String fname = load.next();
            String lname = load.next();
            String name = fname + " " + lname;
            int age = load.nextInt();
            String CPR = load.next();

            Child cc = new Child(name, age, CPR);

            waitlist.add(cc);

        }
        return waitlist;
    }

    public static ArrayList<Employee> readEmployeeFromFile() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("EmployeeList.txt"));
        ArrayList<Employee> employees = new ArrayList<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            Scanner load = new Scanner(line);

            String fname = load.next();
            String lname = load.next();
            String name = fname + " " + lname;
            int number = load.nextInt();
            String CPR = load.next();


            Employee e = new Employee(name, number, CPR);

            employees.add(e);
        }
        return employees;
    }

    public static void readScheduleFromFile(String[][] arr) throws IOException {
        Scanner load = new Scanner(new File("Schedule.txt"));
        load.useDelimiter(";");
        for(int i = 0; i < arr.length; i++){

            for(int j = 0; j < arr[i].length; j++){
                arr[i][j] = load.next();
            }
            load.nextLine();
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.printf("%11s", arr[i][j]);
            }
            System.out.println();
        }
    }


    //Metode der udskriver vores Child list
    public static void printList(Scanner scan, LinkedList<Child> childs, ArrayList<Child> waitlist,
                                 ArrayList<Employee> employees, LinkedList<Parent> parent) {
        printlistmenu();
        String answer = scan.next();
        while (!answer.equals("0")) {
            if (answer.equals("1")) {
                for (int i = 0; i < childs.size(); i++) {
                    Collections.sort(childs);
                    System.out.println(childs.get(i));
                }
            }if(answer.equals("2")) {
                for (int i = 0; i < waitlist.size(); i++) {
                    System.out.println(waitlist.get(i));
                }
            }if(answer.equals("3")) {
                TreeMap<String, Integer> phoneList = new TreeMap<>();
                for (Parent par : parent) {
                    phoneList.put(par.getName(), par.getNumber());
                }
                for (String par : phoneList.keySet()) {
                    System.out.println("Parent: " + par + "\nPhone number: " + phoneList.get(par) + "\n");
                }
            }if(answer.equals("4")) {
                for (int i = 0; i < parent.size(); i++) {
                    System.out.println(parent.get(i));
                }
            }if(answer.equals("5")) {
                for (int i = 0; i < employees.size(); i++) {
                    Collections.sort(employees);
                    System.out.println(employees.get(i));
                }
            }
            printlistmenu();
            answer = scan.next();
        }
    }


    //metode til at fjerne et barn
    public static void deleteChild(Scanner scan, LinkedList<Child> childs) {
        for (int i = 0; i < childs.size(); i++) {
            System.out.println(childs.get(i));
        }
        System.out.println("Enter personal number of the child you want to remove from the list");
        boolean foundCPR = false;
        String personalNumber = scan.next();
        for (int i = 0; i < childs.size(); i++) {
            if (childs.get(i).getCPR().equals(personalNumber)) {
                childs.remove(i);
                foundCPR = true;
                break;
            }
        }
        if (!foundCPR) {
            System.out.println("This personal number does not exists");
        }
        System.out.println(childs);
    }

    //metode til at fjerne en ansat
    public static void deleteEmployee(Scanner scan, ArrayList<Employee> employees) {
        for (int i = 0; i < employees.size(); i++) {
            System.out.println(employees.get(i));
        }
        System.out.println("Enter personal number of the employee you want to remove from the list");
        boolean foundCPR = false;
        String personalNumber = scan.next();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getCPR().equals(personalNumber)) {
                employees.remove(i);
                foundCPR = true;
                break;
            }
        }
    }
    public static void deleteParent(Scanner scan, LinkedList<Parent> parents) {
        for (int i = 0; i < parents.size(); i++) {
            System.out.println(parents.get(i));
        }
        System.out.println("Enter personal number of the parent you want to remove from the list");
        boolean foundCPR = false;
        String personalNumber = scan.next();
        for (int i = 0; i < parents.size(); i++) {
            if (parents.get(i).getCPR().equals(personalNumber)) {
                parents.remove(i);
                foundCPR = true;
                break;
            }
        }
        if (!foundCPR) {
            System.out.println("This personal number does not exists");
        }
        System.out.println(parents);
    }

    public static void printPhoneList(LinkedList<Parent> parent) {
        TreeMap<String, Integer> phoneList = new TreeMap<>();
        for (Parent par : parent) {
            phoneList.put(par.getName(), par.getNumber());
        }
        System.out.println(phoneList);
    }

    public static void editParent(Scanner scan, LinkedList<Parent> parent, LinkedList<Child> childs) {
        for(int i = 0; i < parent.size(); i++){
            System.out.println(parent.get(i).toStringCPRname());
        }
        System.out.println("Enter the personal number of the parent you wish to edit: ");
        String CPR = scan.next();

        for (int i = 0; i < parent.size(); i++) {
            if (parent.get(i).getCPR().equals(CPR)) {
                System.out.println("\n" + parent.get(i) + "\n");

                int svar = 1;
                while (svar != 0) {
                    System.out.println("Type 1 to edit name.\nType 2 to edit age.\nType 3 to edit CPR\nType 4 to edit child" +
                            "\nType 0 to return");
                    svar = scan.nextInt();

                    switch (svar) {
                        case 1:
                            System.out.println("You have chosen to edit name\n----------------------------\nEnter new first name: ");
                            String newFName = scan.next();
                            System.out.println("Enter new last name: ");
                            String newLName = scan.next();
                            String newName = newFName + " " + newLName;
                            parent.get(i).setName(newName);
                            break;
                        case 2:
                            System.out.println("You have chosen to edit phone number\n----------------------------\nEnter new phone number: ");
                            int newNumber = scan.nextInt();
                            parent.get(i).setNumber(newNumber);
                            break;
                        case 3:
                            System.out.println("You have chosen to edit CPR\n---------------------------\nEnter new CPR: ");
                            String newCPR = scan.next();
                            parent.get(i).setCPR(newCPR);
                            break;
                        case 4:
                            System.out.println("You have chosen edit child CPR\n---------------------------\nEnter new CPR: ");
                            String kidCPR = scan.next();
                            for (int j = 0; j < childs.size(); j++) {
                                    if (kidCPR.equals(childs.get(j).getCPR())) {
                                        parent.get(j).setKid(childs.get(j));
                                    }
                            }
                            break;
                        case 0:
                            System.out.println("Returning to main menu.");
                            break;
                    }
                }
            }
        }
    }


    public static void editEmployee(Scanner scan, ArrayList<Employee> employees) {
        for(int i = 0; i < employees.size(); i++){
            System.out.println(employees.get(i).toStringCPRname());
        }
        System.out.println("Enter the personal number of the employee you wish to edit: ");
        String CPR = scan.next();

        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getCPR().equals(CPR)) {
                System.out.println("\n" + employees.get(i) + "\n");

                int svar = 1;
                while (svar != 0) {
                    System.out.println("Type 1 to edit name.\nType 2 to edit CPR\nType 3 to edit phone number\nType 0 to return\n");
                    svar = scan.nextInt();

                    switch (svar) {
                        case 1:
                            System.out.println("You have chosen to edit name\n----------------------------\nEnter new name: ");
                            String newName = scan.next();
                            employees.get(i).setName(newName);
                            break;
                        case 2:
                            System.out.println("You have chosen to edit CPR\n---------------------------\nEnter new CPR: ");
                            String newCPR = scan.next();
                            employees.get(i).setCPR(newCPR);
                            break;
                        case 3:
                            System.out.println("You have chosen to edit phone number\n---------------------------\nEnter new phone number: ");
                            int newNumber = scan.nextInt();
                            employees.get(i).setNumber(newNumber);
                            break;
                        case 0:
                            System.out.println("Returning to main menu.");
                            break;
                    }
                }
            }
        }
    }

    public static void editChild(Scanner scan, LinkedList<Child> childs) {
        for(int i = 0; i < childs.size(); i++){
            System.out.println(childs.get(i).toStringCPRname());
        }
        System.out.println("Enter the personal number of the child you wish to edit: ");
        String CPR = scan.next();

        for (int i = 0; i < childs.size(); i++) {
            if (childs.get(i).getCPR().equals(CPR)) {
                System.out.println("\n" + childs.get(i) + "\n");

                int svar = 1;
                while (svar != 0) {
                    System.out.println("Type 1 to edit name.\nType 2 to edit age.\nType 3 to edit CPR\nType 0 to return\n");
                    svar = scan.nextInt();

                    switch (svar) {
                        case 1:
                            System.out.println("You have chosen to edit name\n----------------------------\nEnter new name: ");
                            String temp = scan.nextLine();
                            String newName = scan.nextLine();
                            childs.get(i).setName(newName);
                            break;
                        case 2:
                            System.out.println("You have chosen to edit age\n----------------------------\nEnter new age: ");
                            int newAge = scan.nextInt();
                            childs.get(i).setAge(newAge);
                            break;
                        case 3:
                            System.out.println("You have chosen to edit CPR\n---------------------------\nEnter new CPR: ");
                            String newCPR = scan.next();
                            childs.get(i).setCPR(newCPR);
                            break;
                        case 0:
                            System.out.println("Returning to main menu.");
                            break;
                    }
                }
            }
        }
    }
    public static String[][] schedule(String[][] arr, Employee[] employee, Scanner scan) throws NullPointerException {
        System.out.println("What week are you in?");
        int week = scan.nextInt();

        arr[0][0] = "Week: " + week;
        arr[0][1] = "Monday";
        arr[0][2] = "Tuesday";
        arr[0][3] = "Wednesday";
        arr[0][4] = "Thursday";
        arr[0][5] = "Friday";
        arr[1][0] = "7:00-14:00";
        arr[2][0] = "7:00-14:00";
        arr[3][0] = "8:30-15:30";
        arr[4][0] = "10:00-17:00";
        arr[5][0] = "10:00-17:00";


        //liste med employees med index
        boolean stop = false;

        while (stop == false) {

            for (int index = 0; index < employee.length; index++) {
                if (employee[index] != null) {
                    System.out.println(index + " " + employee[index].getName());
                }
            }
            System.out.println("Which employee do you want to add to the schedule? Type the index of the employee" +
                    " or -1 to exit to the menu: ");
            int employeeIndex = scan.nextInt();
            if (employeeIndex == -1) {
                stop = true;
                break;
            }

            while (employee[employeeIndex] == null){
                System.out.println("There were no employee on that index. try again");
                employeeIndex = scan.nextInt();
            }
            boolean contWhile = true;
            while(contWhile) {
                System.out.println("What day do you want to add " + employee[employeeIndex].toStringName()
                        + " to?\nType -1 to exit to main menu\nType 0 to go back to change the employee\n\n" +
                        "1. Monday\n2. Tuesday\n3. Wednesday\n4. Thursday\n5. Friday");
                int day = scan.nextInt();
                if (day == -1) {
                    stop = true;
                    break;
                } else if (day == 0) {
                    contWhile = false;
                    break;
                }

                System.out.println("When is " + employee[employeeIndex].toStringName() + " due to meet?\n" +
                        "1. Opening(7:00-13:00)" +
                        "\n2. Fill(9:30-15:30)\n3. Closing(10:00-17:00)");
                int due;
                switch (day) {
                    case 1:
                        due = scan.nextInt();

                        if (due == 1 && arr[1][1] == null) {
                            arr[1][1] = employee[employeeIndex].toStringName();
                        } else if (due == 1 && !arr[1][1].isEmpty()) {
                            arr[2][1] = employee[employeeIndex].toStringName();

                        }
                        if (due == 2) {
                            arr[3][1] = employee[employeeIndex].toStringName();

                        }
                        if (due == 3 && arr[4][1] == null) {
                            arr[4][1] = employee[employeeIndex].toStringName();
                        } else if (due == 3 && !arr[4][1].isEmpty()) {
                            arr[5][1] = employee[employeeIndex].toStringName();
                        }
                        break;

                    case 2:
                        due = scan.nextInt();

                        if (due == 1 && arr[1][2] == null) {
                            arr[1][2] = employee[employeeIndex].toStringName();
                        } else if (due == 1 && !arr[1][2].isEmpty()) {
                            arr[2][2] = employee[employeeIndex].toStringName();

                        }
                        if (due == 2) {
                            arr[3][2] = employee[employeeIndex].toStringName();

                        }
                        if (due == 3 && arr[4][2] == null) {
                            arr[4][2] = employee[employeeIndex].toStringName();
                        } else if (due == 3 && !arr[4][2].isEmpty()) {
                            arr[5][2] = employee[employeeIndex].toStringName();
                        }
                        break;

                    case 3:
                        due = scan.nextInt();

                        if (due == 1 && arr[1][3] == null) {
                            arr[1][3] = employee[employeeIndex].toStringName();
                        } else if (due == 1 && !arr[1][3].isEmpty()) {
                            arr[2][3] = employee[employeeIndex].toStringName();

                        }
                        if (due == 2) {
                            arr[3][3] = employee[employeeIndex].toStringName();

                        }
                        if (due == 3 && arr[4][3] == null) {
                            arr[4][3] = employee[employeeIndex].toStringName();
                        } else if (due == 3 && !arr[4][3].isEmpty()) {
                            arr[5][3] = employee[employeeIndex].toStringName();
                        }
                        break;

                    case 4:
                        due = scan.nextInt();

                        if (due == 1 && arr[1][4] == null) {
                            arr[1][4] = employee[employeeIndex].toStringName();
                        } else if (due == 1 && !arr[1][4].isEmpty()) {
                            arr[2][4] = employee[employeeIndex].toStringName();

                        }
                        if (due == 2) {
                            arr[3][4] = employee[employeeIndex].toStringName();

                        }
                        if (due == 3 && arr[4][4] == null) {
                            arr[4][4] = employee[employeeIndex].toStringName();
                        } else if (due == 3 && !arr[4][4].isEmpty()) {
                            arr[5][4] = employee[employeeIndex].toStringName();
                        }
                        break;

                    case 5:
                        due = scan.nextInt();

                        if (due == 1 && arr[1][5] == null) {
                            arr[1][5] = employee[employeeIndex].toStringName();
                        } else if (due == 1 && !arr[1][5].isEmpty()) {
                            arr[2][5] = employee[employeeIndex].toStringName();

                        }
                        if (due == 2) {
                            arr[3][5] = employee[employeeIndex].toStringName();

                        }
                        if (due == 3 && arr[4][5] == null) {
                            arr[4][5] = employee[employeeIndex].toStringName();
                        } else if (due == 3 && !arr[4][5].isEmpty()) {
                            arr[5][5] = employee[employeeIndex].toStringName();
                        }
                        break;

                    default:
                        System.out.println("Wrong input.");
                        break;
                }

                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr[0].length; j++) {
                        if (arr[i][j] != null) {
                            System.out.printf("%11s", arr[i][j]);

                        }
                        if (arr[i][j] == null) {
                            String empty = "";
                            System.out.printf("%11s", empty);

                        }
                    }
                    System.out.println();

                }
                System.out.println();
            }
            System.out.println();
        }
        return arr;
    }


    public static void printMainMenu(){
        System.out.println("\n***** Roskilde frie børnehave hovedmenu *****\n" +
                "\nType 1 for child menu." +
                "\nType 2 for parent menu" +
                "\nType 3 for coworker menu." +
                "\nType 4 to print lists." +
                "\nType 5 for schedule menu." +
                "\nType 0 to terminate program.");
    }
    public static void childrensMenu() {
        System.out.printf("%21s %n", "\n********* Child Menu *********");
        System.out.println("\nType 1 to register a new child.\n" +
                "Type 2 to edit a childs information.\nType 3 to remove child" +
                "\nType 4 to registre a child to the waiting list to\nType 0 to return to main menu.");
    }

    public static void printParentsMenu(){
        System.out.println("\n***** Parent Menu *****\n\nType 1 to register parent." +
                            "\nType 2 to edit a parents information.\nType 0 to return to ");
    }

    public static void printEmployeesMenu(){
        System.out.println("\n******* Employee Menu *******\n" +
                            "\nType 1 to add a new employee\nType 2 to edit an employee" +
                            "\nType 3 to delete an employee\nType 0 to return to main menu.");
    }

    public static void printlistmenu() {
        System.out.println("Which list do you want see?\n---------------------------\n" +
                "1. Childrens list.\n2. The waiting list.\n3. The parent phone list.\n4. The parent list." +
                "\n5. The employee list\n0. Exit to main menu.");
    }
}
