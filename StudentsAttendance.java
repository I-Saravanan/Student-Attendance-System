import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

class StudentsAttendance {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        HashMap<Integer,String> studentlist = new HashMap<Integer,String>();
        HashSet<Integer> presentlist = new HashSet<>();
        
        studentlist.put(101, "Akash");
        studentlist.put(102, "Ajay");
        studentlist.put(103, "Bhavana");
        studentlist.put(104, "Priya");
        studentlist.put(105, "Sahira");
        studentlist.put(106,"Sara");
        studentlist.put(107, "Zaara");
        
        presentlist.add(101);
        presentlist.add(103);
        presentlist.add(104);
        presentlist.add(105);
        presentlist.add(102);
        
        while(true){
            System.out.println("\n---Attendance Menu---");
            System.out.println("1.Mark Attendance");
            System.out.println("2.Check Attendance");
            System.out.println("3.View Present List");
            System.out.println("4.View Absent List");
            System.out.println("5.View Total Count");
            System.out.println("6.Exit");

            System.out.println("Enter your choice:" );
            int choice = sc.nextInt();
            
            switch(choice){
                case 1:
                    System.out.println("Enter the Roll no");
                    int roll = sc.nextInt();
                    if(studentlist.containsKey(roll)) {
                        if(presentlist.contains(roll)) {
                            System.out.println("Attendance Already Marked as Present");
                        }
                        else{
                            presentlist.add(roll);
                            System.out.println("Attendance Marked as Present");
                        }   
                    }
                    else {
                        System.out.println("Invalid Roll Number!");
                    }
                    break;
                case 2:
                    System.out.println("Enter the Roll no You want to check:");
                    int rol = sc.nextInt();
                    if(!studentlist.containsKey(rol)) {
                        System.out.println("Invalid Roll Number!");
                        break;
                    }
                    else{
                        if (presentlist.contains(rol)){
                            System.out.println(rol +" is Present");
                        }
                        else{
                            System.out.println(rol +" is Absent");
                        }
                        }
                    break;
                case 3:
                    System.out.println("Student's Present List:");
                    TreeSet<Integer> sorted = new TreeSet<>(presentlist);
                    for(Integer r : sorted) {
                        System.out.println(r + " - " + studentlist.get(r));
                    }
                    break;
                case 4:
                    System.out.println("Student's Absent List");
                    TreeSet<Integer> sortedAbs = new TreeSet<>(studentlist.keySet());
                    for(Integer r : sortedAbs) {
                        if(!presentlist.contains(r)) {
                            System.out.println(r + " - " + studentlist.get(r));
                        }
                    }
                    break;
                case 5:
                    System.out.println("Attendance Count");
                    int total = studentlist.size();
                    int present = presentlist.size();
                    int absent = total - present;
                    System.out.println("Total Number of Students:"+ total);
                    System.out.println("Total Number of Present:"+ present);
                    System.out.println("Total Number of Absent:"+ absent);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice...");
            }
        }
    }
}

