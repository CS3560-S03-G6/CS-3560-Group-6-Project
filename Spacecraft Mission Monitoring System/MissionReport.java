
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
}