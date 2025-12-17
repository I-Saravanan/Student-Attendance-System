package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class DatabaseStorage  {
    
        //Connection of DBMS
        public Connection getConnection () throws Exception{
        String url = "jdbc:mysql://localhost:3306/student_management";
        String user = "root";
        String pass = "Saratheking8@";
        return DriverManager.getConnection(url, user, pass);
        }

        public void insertStudent(int roll, String name){
            try{
                if(studentExist(roll)){
                    System.out.println("The student with this Roll number already exists");
                    return;
                }
                Connection con = getConnection();

                String sql = "INSERT INTO studentlist (roll,name) VALUES(?,?)";
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setInt(1, roll);
                ps.setString(2, name);

                ps.executeUpdate();
                ps.close();
                con.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        public void fetchAllStudents(){
            try {
                Connection con = getConnection();

                String sql = "SELECT roll,name FROM studentlist";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    int roll = rs.getInt("roll");
                    String name = rs.getString("name");
                    System.out.println(roll + "-" + name);
                }

                ps.close();
                con.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        public boolean studentExist(int roll) {
            try {
                Connection con = getConnection();

                String sql = "SELECT roll FROM studentlist WHERE roll = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, roll);

                ResultSet rs = ps.executeQuery();
                Boolean exist = rs.next();

                ps.close();
                con.close();
                rs.close();

                return exist;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        public void updateStudent(int roll, String newName){
            try {
                Connection con = getConnection();

                String sql = "UPDATE studentlist SET name = ? WHERE roll = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, newName);
                ps.setInt(2, roll);

                ps.executeUpdate();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void deleteStudent(int roll){
            try {
                Connection con = getConnection();

                String sql = "DELETE FROM studentlist WHERE roll = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, roll);

                ps.executeUpdate();
                System.out.println("Student deleted.");
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void upsertMark(int roll, int mark){
            try {
                Connection con = getConnection();

                String sql = "INSERT INTO marks(roll,mark) VALUES (?,?) ON DUPLICATE KEY UPDATE mark = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, roll);
                ps.setInt(2, mark);
                ps.setInt(3, mark);

                ps.executeUpdate();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void fetchMark(int roll){
            try {
                Connection con = getConnection();

                String sql = "SELECT mark FROM marks WHERE roll = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, roll);

                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                System.out.println("Marks: " + rs.getInt("mark"));
                }
                else{
                    System.out.println("Marks not found!");
                }
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void fetchAllMarks(){
            try {
                Connection con = getConnection();

                String sql = "SELECT s.roll,s.name,s.mark FROM studentlist s LEFT JOIN marks m ON s.roll = m.roll";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    int roll = rs.getInt("roll");
                    String name = rs.getString("name");
                    int mark = rs.getInt("mark");
                    System.out.println(roll + ":" + name + "-" + mark);
                }

                ps.close();
                con.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        public void updateTodayAttendance(LocalDate date, int roll){
            try {
                Connection con = getConnection();

                String sql = "INSERT INTO attendance VALUES (?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setDate(1, java.sql.Date.valueOf(date));
                ps.setInt(2, roll);

                ps.executeUpdate();
                ps.close();
                con.close();
            }catch(java.sql.SQLIntegrityConstraintViolationException e){
                System.out.println("Attendance is already marked for Roll: "+roll);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void viewAttendancebyDate(LocalDate date){
            try {
                Connection con = getConnection();
                //Present
                String sql = "SELECT roll FROM attendance WHERE date = ?";
                PreparedStatement ps1 = con.prepareStatement(sql);
                ps1.setDate(1, java.sql.Date.valueOf(date));

                ResultSet rs1 = ps1.executeQuery();
                System.out.println("Present Students:");
                while(rs1.next()){
                System.out.println("Roll no:" + rs1.getInt("roll"));
                }
                //Absent
                PreparedStatement ps2 =con.prepareStatement("SELECT roll FROM studentlist " +
                        "WHERE roll NOT IN (SELECT roll FROM attendance WHERE date = ?)");

                ps2.setDate(1, java.sql.Date.valueOf(date));
                ResultSet rs2 = ps2.executeQuery();

                System.out.println("Absent Students:");
                while (rs2.next()) {
                    System.out.println("Roll: " + rs2.getInt("roll"));
                }
                rs2.close();
                rs1.close();
                ps2.close();
                ps1.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void viewAttendanceHistory(){
            try {
                Connection con = getConnection();

                String sql = "SELECT date,roll FROM attendance ORDER BY date";
                PreparedStatement ps = con.prepareStatement(sql);

                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                System.out.println(rs.getDate("date") + " - " + rs.getInt("roll"));
                }
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void attendancePercentage(int roll){
            try {
                Connection con = getConnection();
                //total days
                String sqls1 = "SELECT COUNT(DISTINCT date) AS totaldays FROM attendance";
                PreparedStatement ps1 = con.prepareStatement(sqls1);
                ResultSet rs1 = ps1.executeQuery();
                rs1.next();
                int totaldays = rs1.getInt("totaldays");
                //present days
                String sqls2 = "SELECT COUNT(roll) AS present FROM attendance WHERE roll = ?";
                PreparedStatement ps2 = con.prepareStatement(sqls2);
                ps2.setInt(1, roll);
                ResultSet rs2 = ps2.executeQuery();
                rs2.next();
                int present = rs2.getInt("present");
                //calculation %
                int percent = (totaldays == 0) ? 0 : (present*100)/100;

                System.out.println( "Attendance Percentage: " + percent + "%");

                ps1.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
}
