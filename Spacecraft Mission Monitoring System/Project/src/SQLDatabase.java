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

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM mission")) {

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


    public static int stringToInt(String string) {
        String returnString = "";

        for (int i = 0; i < string.length(); i++) {
            if (Character.isDigit(string.charAt(i))) {
                returnString = returnString + string.charAt(i);
            }
        }

        return Integer.parseInt(returnString);
    }

    public static List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM employee")) {

            while (rs.next()) {
                Employee e = new Employee(
                        rs.getInt("employeeID"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getString("workEmail"),
                        stringToInt(rs.getString("phoneNumber")),
                        rs.getString("Location")
                );
                e.setEmployeeID(rs.getInt("employeeID"));
                employees.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }


    public static List<Maneuver> getAllManeuvers() {
        List<Maneuver> maneuvers = new ArrayList<>();
        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM maneuver");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Maneuver m = new Maneuver(
                        rs.getInt("maneuverID"),
                        rs.getInt("missionID"),
                        (Integer) rs.getObject("employeeID"),
                        (Integer) rs.getObject("crewID"),
                        rs.getString("maneuverType"),
                        rs.getString("maneuverDetails"),
                        rs.getString("executionTime"),
                        rs.getInt("fuelCost"),
                        rs.getString("status"),
                        rs.getString("loggedTime"),
                        rs.getString("loggedBy")
                );
                maneuvers.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maneuvers;
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


    public static boolean insertEmployee(String name, String role, String email, int phone, String location) {
        String insertSQL = "INSERT INTO employee (employeeID, name, role, workEmail, phoneNumber, location) VALUES (?, ?, ?, ?, ?, ?)";
        String getLastEmployeeIDSQL = "SELECT employeeID FROM employee ORDER BY employeeID DESC";

        try (Connection conn = getConnection()) {
            // Insert the employee
            PreparedStatement statement = conn.prepareStatement(getLastEmployeeIDSQL);
            ResultSet results = statement.executeQuery();
            results.next();
            int newID = results.getInt("employeeID") + 1;

            PreparedStatement stmt = conn.prepareStatement(insertSQL);

            stmt.setInt(1, newID);
            stmt.setString(2, name);
            stmt.setString(3, role);
            stmt.setString(4, email);
            stmt.setInt(5, phone);
            stmt.setInt(6, stringToInt(location));

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


    public static boolean updateEmployeeByID(int employeeID, String name, String role, String email, int phone, String location) {
        String sql = "UPDATE employee SET name=?, role=?, workEmail=?, phoneNumber=?, location=? WHERE employeeID=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, role);
            stmt.setString(3, email);
            stmt.setInt(4, phone);
            stmt.setString(5, location);
            stmt.setInt(6, employeeID);
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

    public static MissionReport generateMissionReport(Mission mission) {
        int missionID = mission.getMissionID();
        int reportID = (int) (Math.random() * 100000);

        String dateGenerated = java.time.ZonedDateTime.now(java.time.ZoneId.of("America/Los_Angeles"))
                .toString().substring(0, 16).replace("T", " ") + " PST";

        List<Maneuver> maneuvers = new ArrayList<>();
        List<Issue> issues = new ArrayList<>();
        int fuelUsed = 0;
        int fuelLevel = 0;

        try (Connection conn = getConnection()) {
            // === Ensure unique missionReportID ===
            while (true) {
                PreparedStatement checkStmt = conn.prepareStatement("SELECT COUNT(*) FROM missionreport WHERE missionReportID = ?");
                checkStmt.setInt(1, reportID);
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                if (rs.getInt(1) == 0) break; // Unique found
                reportID = (int) (Math.random() * 100000); // Try again
            }

            // === Get Executed Maneuvers ===
            PreparedStatement mStmt = conn.prepareStatement("SELECT * FROM maneuver WHERE missionID = ?");
            mStmt.setInt(1, missionID);
            ResultSet mRs = mStmt.executeQuery();
            while (mRs.next()) {
                Maneuver m = new Maneuver(
                        mRs.getInt("maneuverID"),
                        mRs.getInt("missionID"),
                        (Integer) mRs.getObject("employeeID"),
                        (Integer) mRs.getObject("crewID"),
                        mRs.getString("maneuverType"),
                        mRs.getString("maneuverDetails"),
                        mRs.getString("executionTime"),
                        mRs.getInt("fuelCost"),
                        mRs.getString("status"),
                        mRs.getString("loggedTime"),
                        mRs.getString("loggedBy")
                );
                maneuvers.add(m);
                fuelUsed += m.getFuelCost();
            }

            // === Get Issues ===
            PreparedStatement iStmt = conn.prepareStatement("SELECT * FROM issue WHERE missionID = ?");
            iStmt.setInt(1, missionID);
            ResultSet iRs = iStmt.executeQuery();
            while (iRs.next()) {
                Issue issue = new Issue(
                        iRs.getInt("issueID"),
                        iRs.getInt("missionID"),
                        iRs.getString("issueType"),
                        iRs.getString("detectionTime"),
                        iRs.getInt("severityLevel"),
                        iRs.getBoolean("alertTriggered"),
                        iRs.getString("resolutionStatus")
                );
                issues.add(issue);
            }

            // === Get Current Fuel Level ===
            PreparedStatement fuelStmt = conn.prepareStatement("SELECT initialFuelLevel FROM mission WHERE missionID = ?");
            fuelStmt.setInt(1, missionID);
            ResultSet fRs = fuelStmt.executeQuery();
            if (fRs.next()) {
                int initialFuel = fRs.getInt("initialFuelLevel");
                fuelLevel = initialFuel - fuelUsed;
            }

            // === INSERT into missionreport ===
            PreparedStatement insertReport = conn.prepareStatement(
                    "INSERT INTO missionreport (missionReportID, missionID, dateGenerated) VALUES (?, ?, ?)"
            );
            insertReport.setInt(1, reportID);
            insertReport.setInt(2, missionID);
            insertReport.setString(3, dateGenerated);
            insertReport.executeUpdate();

            // === INSERT into reportmaneuver ===
            PreparedStatement insertManeuver = conn.prepareStatement(
                    "INSERT INTO reportmaneuver (missionReportID, maneuverID, dateGenerated) VALUES (?, ?, ?)"
            );
            for (Maneuver m : maneuvers) {
                insertManeuver.setInt(1, reportID);
                insertManeuver.setInt(2, m.getManeuverID());
                insertManeuver.setString(3, dateGenerated);
                insertManeuver.addBatch();
            }
            insertManeuver.executeBatch();

            // === INSERT into reportissue ===
            PreparedStatement insertIssue = conn.prepareStatement(
                    "INSERT INTO reportissue (missionReportID, issueID, dateGenerated) VALUES (?, ?, ?)"
            );
            for (Issue i : issues) {
                insertIssue.setInt(1, reportID);
                insertIssue.setInt(2, i.getIssueID());
                insertIssue.setString(3, dateGenerated);
                insertIssue.addBatch();
            }
            insertIssue.executeBatch();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new MissionReport(
                reportID,
                missionID,
                dateGenerated,
                maneuvers.toArray(new Maneuver[0]),
                fuelUsed,
                fuelLevel,
                issues.toArray(new Issue[0])
        );
    }


    public static boolean insertImmediateManeuver(
            int missionID, Integer employeeID, Integer crewID,
            String maneuverType, String maneuverDetails,
            int fuelCost, String status
    ) {
        try (Connection conn = getConnection()) {

            // Check that mission exists
            PreparedStatement missionCheck = conn.prepareStatement("SELECT COUNT(*) FROM mission WHERE missionID = ?");
            missionCheck.setInt(1, missionID);
            ResultSet missionRs = missionCheck.executeQuery();
            missionRs.next();
            if (missionRs.getInt(1) == 0) {
                return false;
            }

            String loggedBy = null;
            if (employeeID != null) {
                // Validate employee exists AND role is one of the allowed roles
                PreparedStatement empStmt = conn.prepareStatement("SELECT name, role FROM employee WHERE employeeID = ?");
                empStmt.setInt(1, employeeID);
                ResultSet rs = empStmt.executeQuery();
                if (!rs.next()) {
                    return false;
                }

                String role = rs.getString("role");
                if (!(role.equalsIgnoreCase("Flight Director") || role.equalsIgnoreCase("Mission Controller") || role.equalsIgnoreCase("Crew Commander"))) {
                    return false;
                }

                loggedBy = rs.getString("name") + " - " + role;

            } else if (crewID != null) {
                // Validate crew exists
                PreparedStatement crewStmt = conn.prepareStatement("SELECT commander FROM spacecraftcrew WHERE crewID = ?");
                crewStmt.setInt(1, crewID);
                ResultSet rs = crewStmt.executeQuery();
                if (!rs.next()) {
                    return false;
                }
                loggedBy = "Commander " + rs.getString("commander") + " -  Crew #" + crewID;
            } else {
                return false;
            }

            String timestamp = java.time.ZonedDateTime.now(java.time.ZoneId.of("America/Los_Angeles"))
                    .toString().substring(0, 16).replace("T", " ") + " PST";

            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO maneuver (missionID, employeeID, crewID, maneuverType, maneuverDetails, "
                            + "executionTime, fuelCost, status, loggedTime, loggedBy) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );

            stmt.setInt(1, missionID);
            if (employeeID != null) {
                stmt.setInt(2, employeeID);
            } else {
                stmt.setNull(2, java.sql.Types.INTEGER);
            }
            if (crewID != null) {
                stmt.setInt(3, crewID);
            } else {
                stmt.setNull(3, java.sql.Types.INTEGER);
            }
            stmt.setString(4, maneuverType);
            stmt.setString(5, maneuverDetails);
            stmt.setString(6, timestamp); // executionTime
            stmt.setInt(7, fuelCost);
            stmt.setString(8, status);
            stmt.setString(9, timestamp); // loggedTime
            stmt.setString(10, loggedBy);

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean insertFutureManeuver(
            int missionID, Integer employeeID, Integer crewID,
            String maneuverType, String maneuverDetails,
            String executionTime, int fuelCost, String status
    ) {
        try (Connection conn = getConnection()) {

            // Check that mission exists
            PreparedStatement missionCheck = conn.prepareStatement("SELECT COUNT(*) FROM mission WHERE missionID = ?");
            missionCheck.setInt(1, missionID);
            ResultSet missionRs = missionCheck.executeQuery();
            missionRs.next();
            if (missionRs.getInt(1) == 0) {
                return false;
            }

            String loggedBy = null;
            if (employeeID != null) {
                // Validate employee and role
                PreparedStatement empStmt = conn.prepareStatement("SELECT name, role FROM employee WHERE employeeID = ?");
                empStmt.setInt(1, employeeID);
                ResultSet rs = empStmt.executeQuery();
                if (!rs.next()) {
                    return false;
                }

                String role = rs.getString("role");
                if (!(role.equalsIgnoreCase("Flight Director") || role.equalsIgnoreCase("Mission Controller") || role.equalsIgnoreCase("Crew Commander"))) {
                    return false;
                }

                loggedBy = rs.getString("name") + " - " + role;

            } else if (crewID != null) {
                PreparedStatement crewStmt = conn.prepareStatement("SELECT commander FROM spacecraftcrew WHERE crewID = ?");
                crewStmt.setInt(1, crewID);
                ResultSet rs = crewStmt.executeQuery();
                if (!rs.next()) {
                    return false;
                }
                loggedBy = "Commander " + rs.getString("commander") + " -  Crew #" + crewID;
            } else {
                return false;
            }

            String loggedTime = java.time.ZonedDateTime.now(java.time.ZoneId.of("America/Los_Angeles"))
                    .toString().substring(0, 16).replace("T", " ") + " PST";

            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO maneuver (missionID, employeeID, crewID, maneuverType, maneuverDetails, "
                            + "executionTime, fuelCost, status, loggedTime, loggedBy) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );

            stmt.setInt(1, missionID);
            if (employeeID != null) {
                stmt.setInt(2, employeeID);
            } else {
                stmt.setNull(2, java.sql.Types.INTEGER);
            }
            if (crewID != null) {
                stmt.setInt(3, crewID);
            } else {
                stmt.setNull(3, java.sql.Types.INTEGER);
            }
            stmt.setString(4, maneuverType);
            stmt.setString(5, maneuverDetails);
            stmt.setString(6, executionTime); // <-- User input
            stmt.setInt(7, fuelCost);
            stmt.setString(8, status);
            stmt.setString(9, loggedTime);
            stmt.setString(10, loggedBy);

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateManeuverByID(
            int maneuverID, int missionID, Integer employeeID, Integer crewID,
            String type, String details, String execTime, int fuelCost, String status) {

        try (Connection conn = getConnection()) {
            // Validate maneuver exists
            PreparedStatement maneuverCheck = conn.prepareStatement("SELECT COUNT(*) FROM maneuver WHERE maneuverID = ?");
            maneuverCheck.setInt(1, maneuverID);
            ResultSet manRs = maneuverCheck.executeQuery();
            manRs.next();
            if (manRs.getInt(1) == 0) {
                return false;
            }

            // Validate mission exists
            PreparedStatement missionCheck = conn.prepareStatement("SELECT COUNT(*) FROM mission WHERE missionID = ?");
            missionCheck.setInt(1, missionID);
            ResultSet missionRs = missionCheck.executeQuery();
            missionRs.next();
            if (missionRs.getInt(1) == 0) {
                return false;
            }

            // Validate only one of employeeID or crewID is provided
            if ((employeeID == null && crewID == null) || (employeeID != null && crewID != null)) {
                return false;
            }

            // Determine loggedBy string
            String loggedBy = null;
            if (employeeID != null) {
                PreparedStatement empStmt = conn.prepareStatement("SELECT name, role FROM employee WHERE employeeID = ?");
                empStmt.setInt(1, employeeID);
                ResultSet rs = empStmt.executeQuery();
                if (!rs.next()) {
                    return false;
                }

                String role = rs.getString("role");
                if (!(role.equalsIgnoreCase("Flight Director")
                        || role.equalsIgnoreCase("Mission Controller")
                        || role.equalsIgnoreCase("Crew Commander"))) {
                    return false;
                }

                loggedBy = rs.getString("name") + " - " + role;
            } else {
                PreparedStatement crewStmt = conn.prepareStatement("SELECT commander FROM spacecraftcrew WHERE crewID = ?");
                crewStmt.setInt(1, crewID);
                ResultSet rs = crewStmt.executeQuery();
                if (!rs.next()) {
                    return false;
                }

                loggedBy = "Commander " + rs.getString("commander") + " -  Crew #" + crewID;
            }

            // Use system-generated loggedTime
            String loggedTime = java.time.ZonedDateTime.now(java.time.ZoneId.of("America/Los_Angeles"))
                    .toString().substring(0, 16).replace("T", " ") + " PST";

            // Perform update
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE maneuver SET missionID = ?, employeeID = ?, crewID = ?, maneuverType = ?, "
                            + "maneuverDetails = ?, executionTime = ?, fuelCost = ?, status = ?, loggedTime = ?, loggedBy = ? "
                            + "WHERE maneuverID = ?"
            );

            stmt.setInt(1, missionID);
            if (employeeID != null) {
                stmt.setInt(2, employeeID);
            } else {
                stmt.setNull(2, java.sql.Types.INTEGER);
            }
            if (crewID != null) {
                stmt.setInt(3, crewID);
            } else {
                stmt.setNull(3, java.sql.Types.INTEGER);
            }
            stmt.setString(4, type);
            stmt.setString(5, details);
            stmt.setString(6, execTime);
            stmt.setInt(7, fuelCost);
            stmt.setString(8, status);
            stmt.setString(9, loggedTime);
            stmt.setString(10, loggedBy);
            stmt.setInt(11, maneuverID);

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateLoggedTime(int maneuverID) {
        try (Connection conn = getConnection()) {
            PreparedStatement checkStmt = conn.prepareStatement("SELECT COUNT(*) FROM maneuver WHERE maneuverID = ?");
            checkStmt.setInt(1, maneuverID);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) {
                return false;
            }

            String timestamp = java.time.ZonedDateTime.now(java.time.ZoneId.of("America/Los_Angeles"))
                    .toString().substring(0, 16).replace("T", " ") + " PST";

            PreparedStatement stmt = conn.prepareStatement("UPDATE maneuver SET loggedTime = ? WHERE maneuverID = ?");
            stmt.setString(1, timestamp);
            stmt.setInt(2, maneuverID);

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean doesMissionExist(int missionID) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM mission WHERE missionID = ?")) {
            stmt.setInt(1, missionID);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean doesEmployeeExist(int employeeID) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM employee WHERE employeeID = ?")) {
            stmt.setInt(1, employeeID);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean doesCrewExist(int crewID) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM spacecraftcrew WHERE crewID = ?")) {
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
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, employeeID);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true if any row found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
