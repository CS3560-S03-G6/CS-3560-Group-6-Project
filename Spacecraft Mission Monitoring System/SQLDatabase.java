
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
                        rs.getString("terminationDate"));
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
     * Inserts a new mission into the database if employeeID exists. Returns
     * true if insertion is successful, false otherwise.
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

    public static boolean updateMissionByID(int missionID, String name, String type, String launch,
            String status, String objectives, int fuel,
            String location, String termination) {
        String sql = "UPDATE mission SET missionName=?, missionType=?, launchDate=?, missionStatus=?, "
                + "missionObjective=?, initialFuelLevel=?, initialLocation=?, terminationDate=? "
                + "WHERE missionID=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, type);
            stmt.setString(3, launch);
            stmt.setString(4, status);
            stmt.setString(5, objectives);
            stmt.setInt(6, fuel);
            stmt.setString(7, location);
            stmt.setString(8, termination);
            stmt.setInt(9, missionID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateTerminationDate(int missionID, String terminationDate) {
        String sql = "UPDATE mission SET terminationDate = ?, missionStatus = 'Terminated' WHERE missionID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, terminationDate);
            stmt.setInt(2, missionID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean insertImmediateManeuver(
            int missionID, Integer employeeID, Integer crewID,
            String type, String details, int executionTime, int fuelCost,
            String locationChange, String status, String loggedTime, String loggedBy) {

        String sql = "INSERT INTO maneuver (missionID, employeeID, crewID, maneuverType, maneuverDetails, executionTime, fuelCost, locationChange, status, loggedTime, loggedBy) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, missionID);
            if (employeeID != null)
                stmt.setInt(2, employeeID);
            else
                stmt.setNull(2, Types.INTEGER);
            if (crewID != null)
                stmt.setInt(3, crewID);
            else
                stmt.setNull(3, Types.INTEGER);
            stmt.setString(4, type);
            stmt.setString(5, details);
            stmt.setInt(6, executionTime);
            stmt.setInt(7, fuelCost);
            stmt.setString(8, locationChange);
            stmt.setString(9, status);
            stmt.setString(10, loggedTime);
            stmt.setString(11, loggedBy);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean doesMissionExist(int missionID) {
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM mission WHERE missionID = ?")) {
            stmt.setInt(1, missionID);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean doesEmployeeExist(int employeeID) {
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM employee WHERE employeeID = ?")) {
            stmt.setInt(1, employeeID);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean doesCrewExist(int crewID) {
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn
                        .prepareStatement("SELECT COUNT(*) FROM spacecraftcrew WHERE crewID = ?")) {
            stmt.setInt(1, crewID);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean doesFlightDirectorExist(int employeeID) {
        String query = "SELECT 1 FROM flightdirector WHERE employeeID = ?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, employeeID);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true if any row found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
