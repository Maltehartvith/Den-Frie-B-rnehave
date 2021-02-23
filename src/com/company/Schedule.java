package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Schedule {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        ArrayList<Employee> employees = readEmployeeFromFile();
        Employee[] array = employees.toArray(new Employee[10]);
        String[][] arr = new String[6][6];
        writeToFileSchedule(schedule(arr, array, scan));
    }
    //sætter 1 plads tilbage i listen
    public static String[][] schedule(String[][] arr, Employee[] employee, Scanner scan) throws NullPointerException {
        System.out.println("What week are you in?");
        int week = scan.nextInt();
        arr[0][0] = "Week: " + week;
        arr[0][1] = "Monday";
        arr[0][2] = "Tuesday";
        arr[0][3] = "Wednesday";
        arr[0][4] = "Thursday";
        arr[0][5] = "Friday";
        arr[1][0] = "8:00-14:00";
        arr[2][0] = "8:00-14:00";
        arr[3][0] = "9:30-15:30";
        arr[4][0] = "11:00-18:00";
        arr[5][0] = "11:00-18:00";


        //liste med employees med index
        boolean stop = false;
        while (stop == false) {
            for (int index = 0; index < employee.length; index++) {
                //System.out.println(index + " " + employee[index].getName());
                if (employee[index] != null) {
                    System.out.println(employee[index].getName());
                }
            }
            System.out.println("Which employee do you want to add to the schedule? Type the index of the employee" +
                                " or -1 to exit to the menu: ");
            int employeeIndex = scan.nextInt();
            if(employeeIndex == -1){
                stop = true;
                break;
            }
            System.out.println("What day do you want to add the employee to? Type -1 to exit to menu" +
                    "\n1. Monday\n2. Thuesday\n3. Wednesday\n4. Tuesday\n5. Friday");
            int day = scan.nextInt();
            if(day == -1){
                stop = true;
                break;
            }
            System.out.println("When is the person due to meet?\n1. Opening(8:00-14:00)\n2. Fill(9:30-15:30)" +
                    "\n3. Closing(11:00-18:00)");
            int due;
            boolean empty = true;
            if (day == 1) {
                due = scan.nextInt();
                if (due == 1 && arr[1][1] == null) {
                    arr[1][1] = employee[employeeIndex].toStringName();
                    }else  if (due == 1 && !arr[1][1].isEmpty()) {
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
            }
            if (day == 2) {
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

            }
            if (day == 3) {
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

            }
            if (day == 4) {
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

            }
            if (day == 5) {
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
            }
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    if (arr[i][j] != null){
                        System.out.printf("%13s", arr[i][j]);
                    }
                    //System.out.printf("%11s ", arr[i][j]);

                }
                System.out.println();
            }
            System.out.println();
        }
        return arr;
    }
    public static void writeToFileSchedule(String[][] arr) throws FileNotFoundException {
        try{
        PrintStream output = new PrintStream(new File("output.txt"));
                for (int i = 0; i < arr.length; i++) {
                    String s = "";
                    for (int j = 0; j < arr[i].length; j++) {
                        s += "|" + arr[i][j] + "|";
                    }
                    output.println(s);
                }
                output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

        /*PrintStream write = new PrintStream(new File("Schedule.txt"));

        for (int i = 0; i < arr.length; i++) {
            for(int j = 0; j < i; j++) {
                write.println(arr[i][j]);
            }
        }

        write.close();*/



















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
}