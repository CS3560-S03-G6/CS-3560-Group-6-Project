import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MissionReport {
    private int missionReportID;
    private int missionID;
    private int dateGenerated;
    private Maneuver[] executedManeuvers;
    private int currentFuelUsage;
    private int currentFuelLevel;
    private Issue[] detectedIssues;

    // Constructor that takes in all necessary fields
    public MissionReport(int missionReportID, int missionID, int dateGenerated, Maneuver[] executedManeuvers,
            int currentFuelUsage, int currentFuelLevel, Issue[] detectedIssues) {
        this.missionReportID = missionReportID;
        this.missionID = missionID;
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

    // Getter for missionID
    public int getMissionID() {
        return missionID;
    }

    // Setter for missionID
    public void setMissionID(int missionID) {
        this.missionID = missionID;
    }

    // Getter for dateGenerated
    public int getDateGenerated() {
        return dateGenerated;
    }

    // Setter for dateGenerated
    public void setDateGenerated(int dateGenerated) {
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
    public void exportReport(){
        String fileName = "report_" + missionID + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("MISSION REPORT FOR MISSION ID " + missionID);
            writer.newLine();
            writer.write("Report ID: " + missionReportID);
            writer.newLine();
            writer.write("Date Generated: " + dateGenerated);
            writer.newLine();
            if (executedManeuvers != null) {
                writer.write("Executed Maneuvers:");
                writer.newLine();
                for (Maneuver m : executedManeuvers) {
                    writer.write("Maneuver ID: " + m.getManeuverID());
                    writer.newLine();
                    writer.write("Maneuver Type: " + m.getManeuverType());
                    writer.newLine();
                    writer.write("Maneuver Description: " + m.getManeuverDetails());
                    writer.newLine();
                    writer.write("Execution Time: " + m.getExecutionTime());
                    writer.newLine();
                    writer.write("Fuel Cost: " + m.getFuelCost());
                    writer.newLine();
                    writer.write("Status: " + m.getStatus());
                    writer.newLine();
                    writer.write("Logged Time: " + m.getLoggedTime());
                    writer.newLine();
                    writer.write("Logged By: " + m.getLoggedBy());
                    writer.newLine();
                }
            }
            else{
                writer.write("No manuevers have been executed so far.");
                writer.newLine();
            }
            writer.write("Current fuel usage: " + currentFuelUsage);
            writer.newLine();
            writer.write("Current fuel level: " + currentFuelLevel);
            writer.newLine();
            if (detectedIssues != null) {
                writer.write("Issues:");
                writer.newLine();
                for (Issue issue : detectedIssues) {
                    writer.write("Issue ID: " + issue.getIssueID());
                    writer.newLine();
                    writer.write("Issue Type: " + issue.getIssueType());
                    writer.newLine();
                    writer.write("Detection Time: " + issue.getDetectionTime());
                    writer.newLine();
                    writer.write("Severity Level: " + issue.getSeverityLevel());
                    writer.newLine();
                    writer.write("Alert Triggered: " + issue.getAlertTriggered());
                    writer.newLine();
                    writer.write("Resolution Status: " + issue.getResolutionStatus());
                    writer.newLine();
                }
            }
            else{
                writer.write("This mission has had no issues so far.");
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}