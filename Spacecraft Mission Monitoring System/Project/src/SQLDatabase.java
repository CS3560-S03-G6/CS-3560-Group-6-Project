/*import com.mysql.cj.jdbc.exceptions.SQLError;*/
import java.sql.*;
import java.util.*;

public class SQLDatabase {
    
    // 5 steps t connect database in Java
    // Register the Driver class
    // Create connection
    // Create statment
    // Execute queries
    // Close connection
    
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/spacecraft_mission_monitoring_system";
        String username = "root";
        String password = "root";
        return DriverManager.getConnection(url, username, password);
    }
    
    public static List<Mission> getAllMissions() {
        List<Mission> missions = new ArrayList<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM mission")) {

            while (rs.next()) {
                Mission m = new Mission(
                    rs.getString("missionName"),
                    rs.getString("missionType"),
                    rs.getString("launchDate"),
                    rs.getString("missionStatus"),
                    rs.getString("missionObjective"),
                    rs.getInt("initialFuelLevel"),
                    rs.getString("initialLocation"),
                    rs.getString("terminationDate")
                );
                m.setMissionID(rs.getInt("missionID"));
                missions.add(m);
            }
         System.out.println("Database and tables created successfully (or already exist).");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return missions;
    }
    
   
    /**
     * Inserts a new mission into the database if employeeID exists.
     * Returns true if insertion is successful, false otherwise.
     */
    public static boolean insertMission(int employeeID, String name, String type, String launchDate,
                                        String status, String objective, int fuelLevel,
                                        String location, String terminationDate) {

        String checkSQL = "SELECT COUNT(*) FROM employee WHERE employeeID = ?";
        String insertSQL = "INSERT INTO mission (employeeID, missionName, missionType, launchDate, missionStatus, missionObjective, initialFuelLevel, initialLocation, terminationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection()) {

            // Check if employeeID exists
            PreparedStatement checkStmt = conn.prepareStatement(checkSQL);
            checkStmt.setInt(1, employeeID);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) {
                return false; // Employee ID not found
            }

            // Insert the mission
            PreparedStatement stmt = conn.prepareStatement(insertSQL);
            stmt.setInt(1, employeeID);
            stmt.setString(2, name);
            stmt.setString(3, type);
            stmt.setString(4, launchDate);
            stmt.setString(5, status);
            stmt.setString(6, objective);
            stmt.setInt(7, fuelLevel);
            stmt.setString(8, location);
            stmt.setString(9, terminationDate);

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


    // You can add getAllSpacecraft(), getAllEmployees(), etc here too

    
    
    

//    public static void main(String[] args){
//        String url = "jdbc:mysql://localhost:3306/spacecraft_mission_monitoring_system";
//        String username = "root";
//        String password = "root";
//        
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            
//            Connection connection = DriverManager.getConnection(url, username, password);
//            
//            Statement statement = connection.createStatement();
//            
//            ResultSet resultSet = statement.executeQuery("select * from employee");
//            
//            while (resultSet.next()){
//                System.out.println(resultSet.getInt(1)+" "+ resultSet.getString(2)+" "+resultSet.getString(3));
//            }
//            
//            connection.close();
//        }
//        catch(Exception e){
//            System.out.println(e);
//        }
    
    
    
    
    
 
