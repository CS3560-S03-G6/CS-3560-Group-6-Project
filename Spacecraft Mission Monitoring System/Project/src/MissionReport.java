
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MissionReport {

    private int missionReportID;
    Mission mission;
    private String dateGenerated;
    private Maneuver[] executedManeuvers;
    private int currentFuelUsage;
    private int currentFuelLevel;
    private Issue[] detectedIssues;

    // Constructor that takes in all necessary fields
    public MissionReport(int missionReportID, Mission mission, String dateGenerated, Maneuver[] executedManeuvers,
            int currentFuelUsage, int currentFuelLevel, Issue[] detectedIssues) {
        this.missionReportID = missionReportID;
        this.mission = mission;
        this.dateGenerated = dateGenerated;
        this.executedManeuvers = executedManeuvers;
        this.currentFuelUsage = currentFuelUsage;
        this.currentFuelLevel = currentFuelLevel;
        this.detectedIssues = detectedIssues;
    }

    // Getter for missionReportID
    public int getMissionReportID() {
        return missionReportID;
    }

    // Setter for missionReportID
    public void setMissionReportID(int missionReportID) {
        this.missionReportID = missionReportID;
    }
    // Getter for mission
    public Mission getMission() {
        return mission;
    }
    
    // Setter for mission
    public void setMission(Mission mission) {
        this.mission = mission;
    }

    // Getter for dateGenerated
    public String getDateGenerated() {
        return dateGenerated;
    }

    // Setter for dateGenerated
    public void setDateGenerated(String dateGenerated) {
        this.dateGenerated = dateGenerated;
    }

    // Getter for executedManeuvers
    public Maneuver[] getExecutedManeuvers() {
        return executedManeuvers;
    }

    // Setter for executedManeuvers
    public void setExecutedManeuvers(Maneuver[] executedManeuvers) {
        this.executedManeuvers = executedManeuvers;
    }

    // Getter for currentFuelUsage
    public int getCurrentFuelUsage() {
        return currentFuelUsage;
    }

    // Setter for currentFuelUsage
    public void setCurrentFuelUsage(int currentFuelUsage) {
        this.currentFuelUsage = currentFuelUsage;
    }

    // Getter for currentFuelLevel
    public int getCurrentFuelLevel() {
        return currentFuelLevel;
    }

    // Setter for currentFuelLevel
    public void setCurrentFuelLevel(int currentFuelLevel) {
        this.currentFuelLevel = currentFuelLevel;
    }

    // Getter for detectedIssues
    public Issue[] getDetectedIssues() {
        return detectedIssues;
    }

    // Setter for detectedIssues
    public void setDetectedIssues(Issue[] detectedIssues) {
        this.detectedIssues = detectedIssues;
    }

    public String displayReportSummary() {
        return "Report summary";
    }

    //Outputs this mission report to a .txt file
    public void exportReport() {

        String fileName = "report_mission_" + mission.getMissionID() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("MISSION REPORT FOR MISSION ID " + mission.getMissionID() + "\n");
            writer.write("Report ID: " + missionReportID + "\n");
            writer.write("Date Generated: " + dateGenerated + "\n");
            if (mission.getSpacecraft() != null) {
                Spacecraft sc = mission.getSpacecraft();
                writer.write("Spacecraft: #" + sc.getSpacecraftID() + " - " + sc.getSpacecraftName()
                        + " (" + sc.getSpacecraftType() + ")\n");
            } else {
                writer.write("Spacecraft: None Assigned\n");
            }
            writer.write("=====================================\n");
            if (executedManeuvers != null) {
                writer.write("Executed Maneuvers:");
                writer.newLine();
                for (Maneuver m : executedManeuvers) {
                    writer.write("-----------------------------\n");
                    writer.write("Maneuver ID: " + m.getManeuverID() + "\n");
                    writer.write("Maneuver Type: " + m.getManeuverType() + "\n");
                    writer.write("Maneuver Description: " + m.getManeuverDescription() + "\n");
                    writer.write("Execution Time: " + m.getExecutionTime() + "\n");
                    writer.write("Fuel Cost: " + m.getFuelCost() + "\n");
                    writer.write("Status: " + m.getStatus() + "\n");
                    writer.write("Logged Time: " + m.getLoggedTime() + "\n");
                    writer.write("Logged By: " + m.getLoggedBy() + "\n");
                }
            } else {
                writer.write("No manuevers have been executed so far.\n");
                writer.newLine();
            }
            writer.write("=====================================\n");
            writer.write("Initial fuel level: " + mission.getInitialFuelLevel() + "\n") ;
            writer.write("Current fuel usage: " + currentFuelUsage);
            writer.newLine();
            writer.write("Current fuel level: " + currentFuelLevel + "\n");
            writer.write("=====================================\n");
            if (detectedIssues != null) {
                writer.write("Issues:\n");
                for (Issue issue : detectedIssues) {
                    writer.write("Issue ID: " + issue.getIssueID() + "\n");
                    writer.write("Issue Type: " + issue.getIssueType() + "\n");
                    writer.write("Detection Time: " + issue.getDetectionTime() + "\n");
                    writer.write("Severity Level: " + issue.getSeverityLevel() + "\n");
                    writer.write("Alert Triggered: " + issue.getAlertTriggered() + "\n");
                    writer.write("Resolution Status: " + issue.getResolutionStatus() + "\n");
                }
            } else {
                writer.write("This mission has had no issues so far.\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
