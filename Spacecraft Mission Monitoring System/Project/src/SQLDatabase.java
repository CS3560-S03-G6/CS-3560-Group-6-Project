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
        String sql = "SELECT m.*, s.spacecraftID AS s_id, s.missionID AS s_missionID, "
                + "s.spacecraftName, s.spacecraftType, s.manufacturer, s.capacity, "
                + "s.maxFuelCapacity, s.thrustPower, s.weight, s.status "
                + "FROM mission m "
                + "LEFT JOIN spacecraft s ON m.spacecraftID = s.spacecraftID";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Mission mission = new Mission(
                        rs.getString("missionName"),
                        rs.getString("missionType"),
                        rs.getString("launchDate"),
                        rs.getString("missionStatus"),
                        rs.getString("missionObjective"),
                        rs.getInt("initialFuelLevel"),
                        rs.getString("initialLocation"),
                        rs.getString("terminationDate")
                );
                mission.setMissionID(rs.getInt("missionID"));

                // Populate spacecraft if available
                if (rs.getObject("s_id") != null) {

                    Spacecraft sc = new Spacecraft(
                            rs.getInt("s_id"),
                            (Integer) rs.getObject("s_missionID"), // ✅ clean and safe
                            rs.getString("spacecraftName"),
                            rs.getString("spacecraftType"),
                            rs.getString("manufacturer"),
                            rs.getInt("capacity"),
                            rs.getInt("maxFuelCapacity"),
                            rs.getInt("thrustPower"),
                            rs.getInt("weight"),
                            rs.getString("status")
                    );
                    mission.setSpacecraft(sc);
                } else {
                    mission.setSpacecraft(null);
                }

                missions.add(mission);
            }
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
                        rs.getString("userName"),
                        rs.getString("password"),
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

    public static int authenticateUser(String username, String password) {
        String sql = "SELECT employeeID FROM employee WHERE username = ? AND password = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("employeeID"); // Success
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Login failed
    }

    public static boolean isMissionController(int employeeID) {
        return existsInRoleTable("missioncontroller", employeeID);
    }

    public static boolean isFlightDirector(int employeeID) {
        return existsInRoleTable("flightdirector", employeeID);
    }

    public static boolean isCrewCommander(int employeeID) {
        return existsInRoleTable("crewcommander", employeeID);
    }

    public static boolean isCrewMember(int employeeID) {
        return existsInRoleTable("crewmember", employeeID);
    }

    private static boolean existsInRoleTable(String tableName, int employeeID) {
        String query = "SELECT 1 FROM " + tableName + " WHERE employeeID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, employeeID);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get spacecrafts not assigned to any mission
    public static List<Spacecraft> getAvailableSpacecraft() {
        List<Spacecraft> available = new ArrayList<>();
        String sql = "SELECT * FROM spacecraft WHERE missionID IS NULL";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int missionID = rs.getObject("missionID") != null ? rs.getInt("missionID") : -1;
                Spacecraft s = new Spacecraft(
                        rs.getInt("spacecraftID"),
                        missionID,
                        rs.getString("spacecraftName"),
                        rs.getString("spacecraftType"),
                        rs.getString("manufacturer"),
                        rs.getInt("capacity"),
                        rs.getInt("maxFuelCapacity"),
                        rs.getInt("thrustPower"),
                        rs.getInt("weight"),
                        rs.getString("status")
                );
                available.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return available;
    }

// Insert mission and return generated missionID
    public static int insertMissionReturnID(int employeeID, String missionName, String missionType, String launchDate,
            String status, String objectives, int fuelLevel, String location,
            String terminationDate, Integer spacecraftID) {
        String sql = "INSERT INTO mission (employeeID, missionName, missionType, launchDate, missionStatus, missionObjective, initialFuelLevel, initialLocation, terminationDate, spacecraftID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, employeeID);
            stmt.setString(2, missionName);
            stmt.setString(3, missionType);
            stmt.setString(4, launchDate);
            stmt.setString(5, status);
            stmt.setString(6, objectives);
            stmt.setInt(7, fuelLevel);
            stmt.setString(8, location);
            stmt.setString(9, terminationDate.isEmpty() ? null : terminationDate);
            if (spacecraftID != null) {
                stmt.setInt(10, spacecraftID);
            } else {
                stmt.setNull(10, java.sql.Types.INTEGER);
            }
            int affected = stmt.executeUpdate();
            if (affected > 0) {
                ResultSet keys = stmt.getGeneratedKeys();
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

// Update spacecraft to point to mission
    public static void assignSpacecraftToMission(int spacecraftID, int missionID) {
        String sql = "UPDATE spacecraft SET missionID = ? WHERE spacecraftID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, missionID);
            stmt.setInt(2, spacecraftID);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unassignSpacecraftFromMission(int missionID) {
        try (Connection conn = getConnection()) {
            // Null the missionID in the spacecraft table
            String clearSpacecraft = "UPDATE spacecraft SET missionID = NULL WHERE missionID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(clearSpacecraft)) {
                stmt.setInt(1, missionID);
                stmt.executeUpdate();
            }

            // Null the spacecraftID in the mission table
            String clearMission = "UPDATE mission SET spacecraftID = NULL WHERE missionID = ?";
            try (PreparedStatement stmt2 = conn.prepareStatement(clearMission)) {
                stmt2.setInt(1, missionID);
                stmt2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean updateMissionByID(int missionID, String name, String type, String launch,
            String status, String objectives, int fuel,
            String location, Integer spacecraftID, int employeeID) {

        String sql;

        // If a spacecraft is assigned, clear terminationDate
        if (spacecraftID != null) {
            sql = "UPDATE mission SET missionName=?, missionType=?, launchDate=?, missionStatus=?, "
                    + "missionObjective=?, initialFuelLevel=?, initialLocation=?, terminationDate=NULL, "
                    + "spacecraftID=?, employeeID=? WHERE missionID=?";
        } else {
            // If no spacecraft is assigned, just leave terminationDate as-is
            sql = "UPDATE mission SET missionName=?, missionType=?, launchDate=?, missionStatus=?, "
                    + "missionObjective=?, initialFuelLevel=?, initialLocation=?, "
                    + "spacecraftID=?, employeeID=? WHERE missionID=?";
        }

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, type);
            stmt.setString(3, launch);
            stmt.setString(4, status);
            stmt.setString(5, objectives);
            stmt.setInt(6, fuel);
            stmt.setString(7, location);

            if (spacecraftID != null) {
                stmt.setInt(8, spacecraftID);
                stmt.setInt(9, employeeID);
                stmt.setInt(10, missionID);
            } else {
                stmt.setNull(8, java.sql.Types.INTEGER);
                stmt.setInt(9, employeeID);
                stmt.setInt(10, missionID);
            }

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
                if (rs.getInt(1) == 0) {
                    break; // Unique found
                }
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
                mission,
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

        // === Ensure mission exists ===
        PreparedStatement missionCheck = conn.prepareStatement("SELECT COUNT(*) FROM mission WHERE missionID = ?");
        missionCheck.setInt(1, missionID);
        ResultSet missionRs = missionCheck.executeQuery();
        missionRs.next();
        if (missionRs.getInt(1) == 0) {
            return false;
        }

        String loggedBy = null;


        // Validate employee exists
        PreparedStatement empStmt = conn.prepareStatement("SELECT name FROM employee WHERE employeeID = ?");
        empStmt.setInt(1, employeeID);
        ResultSet rs = empStmt.executeQuery();
        if (!rs.next()) return false;

        String name = rs.getString("name");
        String role = null;

        if (isMissionController(employeeID)) {
            role = "Mission Controller";
        } else if (isFlightDirector(employeeID)) {
            role = "Flight Director";
        } else if (isCrewCommander(employeeID)) {
            role = "Crew Commander";
        } else if (isCrewMember(employeeID)) {
            role = "Crew Member";
        }

        if (role == null) {
            return false; // not authorized
        }

        loggedBy = name + " - " + role;


        // === Timestamp ===
        String timestamp = java.time.ZonedDateTime.now(java.time.ZoneId.of("America/Los_Angeles"))
                .toString().substring(0, 16).replace("T", " ") + " PST";

        // === Insert maneuver ===
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO maneuver (missionID, employeeID, crewID, maneuverType, maneuverDetails, " +
                        "executionTime, fuelCost, status, loggedTime, loggedBy) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
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

        // === Ensure mission exists ===
        PreparedStatement missionCheck = conn.prepareStatement("SELECT COUNT(*) FROM mission WHERE missionID = ?");
        missionCheck.setInt(1, missionID);
        ResultSet missionRs = missionCheck.executeQuery();
        missionRs.next();
        if (missionRs.getInt(1) == 0) {
            return false;
        }

        // === Validate employee ===
        PreparedStatement empStmt = conn.prepareStatement("SELECT name FROM employee WHERE employeeID = ?");
        empStmt.setInt(1, employeeID);
        ResultSet rs = empStmt.executeQuery();
        if (!rs.next()) return false;

        String name = rs.getString("name");
        String role = null;

        if (isMissionController(employeeID)) {
            role = "Mission Controller";
        } else if (isFlightDirector(employeeID)) {
            role = "Flight Director";
        } else if (isCrewCommander(employeeID)) {
            role = "Crew Commander";
        } else if (isCrewMember(employeeID)) {
            role = "Crew Member";
        }

        if (role == null) {
            return false; // not authorized
        }

        String loggedBy = name + " - " + role;

        // === Log time when maneuver is created ===
        String loggedTime = java.time.ZonedDateTime.now(java.time.ZoneId.of("America/Los_Angeles"))
                .toString().substring(0, 16).replace("T", " ") + " PST";

        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO maneuver (missionID, employeeID, crewID, maneuverType, maneuverDetails, " +
                        "executionTime, fuelCost, status, loggedTime, loggedBy) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
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
        stmt.setString(6, executionTime); // future execution time entered by user
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
        PreparedStatement maneuverCheck = conn.prepareStatement("SELECT COUNT(*) FROM maneuver WHERE maneuverID = ?");
        maneuverCheck.setInt(1, maneuverID);
        ResultSet manRs = maneuverCheck.executeQuery();
        manRs.next();
        if (manRs.getInt(1) == 0) return false;

        PreparedStatement missionCheck = conn.prepareStatement("SELECT COUNT(*) FROM mission WHERE missionID = ?");
        missionCheck.setInt(1, missionID);
        ResultSet missionRs = missionCheck.executeQuery();
        missionRs.next();
        if (missionRs.getInt(1) == 0) return false;

        PreparedStatement empStmt = conn.prepareStatement("SELECT name FROM employee WHERE employeeID = ?");
        empStmt.setInt(1, employeeID);
        ResultSet rs = empStmt.executeQuery();
        if (!rs.next()) return false;

        String name = rs.getString("name");
        String role = null;

        if (isMissionController(employeeID)) {
            role = "Mission Controller";
        } else if (isFlightDirector(employeeID)) {
            role = "Flight Director";
        } else if (isCrewCommander(employeeID)) {
            role = "Crew Commander";
        } else if (isCrewMember(employeeID)) {
            role = "Crew Member";
        }

        if (role == null) return false;

        String loggedBy = name + " - " + role;
        String loggedTime = java.time.ZonedDateTime.now(java.time.ZoneId.of("America/Los_Angeles"))
                .toString().substring(0, 16).replace("T", " ") + " PST";

        PreparedStatement stmt = conn.prepareStatement(
                "UPDATE maneuver SET missionID = ?, employeeID = ?, crewID = ?, maneuverType = ?, " +
                        "maneuverDetails = ?, executionTime = ?, fuelCost = ?, status = ?, loggedTime = ?, loggedBy = ? " +
                        "WHERE maneuverID = ?"
        );

        stmt.setInt(1, missionID);
        if (employeeID != null) stmt.setInt(2, employeeID); else stmt.setNull(2, java.sql.Types.INTEGER);
        if (crewID != null) stmt.setInt(3, crewID); else stmt.setNull(3, java.sql.Types.INTEGER);
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
    
    public static boolean updateLoggedTimeAndBy(int maneuverID, int employeeID) {
    try (Connection conn = getConnection()) {
        // Validate employee exists
        PreparedStatement empStmt = conn.prepareStatement("SELECT name FROM employee WHERE employeeID = ?");
        empStmt.setInt(1, employeeID);
        ResultSet rs = empStmt.executeQuery();
        if (!rs.next()) return false;

        String name = rs.getString("name");
        String role = null;

        if (isMissionController(employeeID)) {
            role = "Mission Controller";
        } else if (isFlightDirector(employeeID)) {
            role = "Flight Director";
        } else if (isCrewCommander(employeeID)) {
            role = "Crew Commander";
        } else if (isCrewMember(employeeID)) {
            role = "Crew Member";
        }

        if (role == null) return false;

        String loggedBy = name + " - " + role;
        String timestamp = java.time.ZonedDateTime.now(java.time.ZoneId.of("America/Los_Angeles"))
                .toString().substring(0, 16).replace("T", " ") + " PST";

        PreparedStatement update = conn.prepareStatement(
                "UPDATE maneuver SET loggedTime = ?, loggedBy = ? WHERE maneuverID = ?"
        );
        update.setString(1, timestamp);
        update.setString(2, loggedBy);
        update.setInt(3, maneuverID);

        return update.executeUpdate() > 0;
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

    public static Integer getCrewIDForEmployee(int employeeID) {
        String sql;

        // Check in crewmember table
        sql = "SELECT crewID FROM crewmember WHERE employeeID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("crewID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // If not found in crewmember, check in crewcommander
        sql = "SELECT crewID FROM crewcommander WHERE employeeID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("crewID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
