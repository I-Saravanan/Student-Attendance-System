package main;

import java.time.LocalDate;
import java.util.Scanner;
import database.DatabaseStorage;



public class StudentsAttendance {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        DatabaseStorage db = new DatabaseStorage();
        

        while(true){
            System.out.println("\n---Student Management System---");
            System.out.println("1.Manage Students List");
            System.out.println("2.Manage Attendance");
            System.out.println("3.Manage Marks");
            System.out.println("4.Exit");

            System.out.println("Enter your choice:" );
            int choice = sc.nextInt();

            switch(choice){
                case 1: //Manage Students List
                    manageStudents(sc,db);
                    break;
                case 2: //Manage Attendance
                    manageAttendance(sc,db);
                    break;
                case 3: //Manage Marks
                    manageMarks(sc,db);
                    break;
                case 4: //Exit
                System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice...");
            }
        }
    }
    static void manageStudents(Scanner sc, DatabaseStorage db){
        while (true) {
            System.out.println("--Manage Students List--");
            System.out.println("1.Add Student");
            System.out.println("2.View Students List");
            System.out.println("3.Update Student's Name");
            System.out.println("4.Delete Student");
            System.out.println("5.Back");
            
            System.out.println("Enter your Choicec:");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: //Add Student
                    System.out.println("Enter Student's roll number:");
                    int Addroll = sc.nextInt();
                    System.out.println("Enter Student's Name:");
                    String name = sc.next();
                    db.insertStudent(Addroll, name);
                    break;
                case 2: //View Students List
                    db.fetchAllStudents();
                    break;
                case 3: //update student
                    System.out.println("Enter Student roll number:");
                    int uproll = sc.nextInt();
                    System.out.println("Enter Student's new name:");
                    String newname = sc.next();
                    db.updateStudent(uproll, newname);
                    break;
                case 4: //Delete student
                    System.out.println("Enter Student roll number: ");
                    int delroll = sc.nextInt();
                    db.deleteStudent(delroll);
                    break;
                case 5: //Back
                    return;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
    static void manageAttendance(Scanner sc, DatabaseStorage db){
        while (true) {
            System.out.println("--Manage Attendance--");
            System.out.println("1.Mark Today's Attendance");
            System.out.println("2.View Attendance by date");
            System.out.println("3.View Attendance History");
            System.out.println("4.Attendance Percentage"); 
            System.out.println("5.Back");    

            System.out.println("Enter your choice:");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: //Mark Today's Attendance
                    System.out.print("Enter roll numbers (separated by ','): ");
                    String input = sc.next();
                    String[] rolls = input.split(",");

                    for (String r : rolls) {
                        int roll = Integer.parseInt(r.trim());
                        if(db.studentExist(roll)) db.updateTodayAttendance(LocalDate.now(), roll);
                        else System.out.println("Invalid Roll: " + roll);
                    }
                    System.out.println("Attendance marked successfully.");                   
                    break;
                case 2: //View Attendance by date
                    System.out.println("Enter Date(dd-mm-yyyy): ");
                    LocalDate date = LocalDate.parse(sc.next());
                    db.viewAttendancebyDate(date);
                    break;
                case 3: //View Attendance History
                    db.viewAttendanceHistory();
                    break;
                case 4: //Attendance Percentage
                    System.out.println("Enter Student's Roll:");
                    int r = sc.nextInt();
                    db.attendancePercentage(r);
                    break;
                case 5: //Back
                    return;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }
    static void manageMarks(Scanner sc, DatabaseStorage db){
        while (true) {
            System.out.println("--Manage Marks--");
            System.out.println("1.Add or Update Marks");
            System.out.println("2.View Student Marks");
            System.out.println("3.View All Marks");
            System.out.println("4.Back");   
            
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: //Add or Update Marks
                    System.out.println("Enter Student Roll:");
                    int roll = sc.nextInt();
                    System.out.println("Enter Student Marks(0-100) :");
                    int marks = sc.nextInt();
                    db.upsertMark(roll, marks);
                    break;
                case 2: //View Student Marks
                    System.out.println("Enter Student Roll :");
                    int r = sc.nextInt();
                    db.fetchMark(r);
                    break;
                case 3: //View All Marks
                    db.fetchAllMarks();
                    break;
                case 4: //Back
                    return;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }
    

}




