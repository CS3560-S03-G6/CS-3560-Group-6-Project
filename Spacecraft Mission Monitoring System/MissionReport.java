public class MissionReport {
    private int missionReportID;
    private int missionID;
    private LocalDateTime dateGenerated;
    private List<Maneuver> maneuvers;
    private int currentFuelUsage;
    private int currentFuelLevel;
    private List<Issue> detectedIssues;

    public MissionReport(int missionReportID, int missionID, LocalDateTime dateGenerated,
                         List<Maneuver> maneuvers, int currentFuelUsage, int currentFuelLevel,
                         List<Issue> detectedIssues) {
        this.missionReportID = missionReportID;
        this.missionID = missionID;
        this.dateGenerated = dateGenerated;
        this.maneuvers = maneuvers;
        this.currentFuelUsage = currentFuelUsage;
        this.currentFuelLevel = currentFuelLevel;
        this.detectedIssues = detectedIssues;
    }

    // getter and setter for missionReportID
    public int getMissionReportID() {
        return missionReportID;
    }

    public void setMissionReportID(int missionReportID) {
        this.missionReportID = missionReportID;
    }

    // getter and setter for missionID
    public int getMissionID() {
        return missionID;
    }

    public void setMissionID(int missionID) {
        this.missionID = missionID;
    }

    // getter and setter for dateGenerated
    public LocalDateTime getDateGenerated() {
        return dateGenerated;
    }

    public void setDateGenerated(LocalDateTime dateGenerated) {
        this.dateGenerated = dateGenerated;
    }

    // getter and setter for maneuvers
    public List<Maneuver> getManeuvers() {
        return maneuvers;
    }

    public void setManeuvers(List<Maneuver> maneuvers) {
        this.maneuvers = maneuvers;
    }

    // getter and setter for currentFuelUsage
    public int getCurrentFuelUsage() {
        return currentFuelUsage;
    }

    public void setCurrentFuelUsage(int currentFuelUsage) {
        this.currentFuelUsage = currentFuelUsage;
    }

    // getter and setter for currentFuelLevel
    public int getCurrentFuelLevel() {
        return currentFuelLevel;
    }

    public void setCurrentFuelLevel(int currentFuelLevel) {
        this.currentFuelLevel = currentFuelLevel;
    }

    // getter and setter for detectedIssues
    public List<Issue> getDetectedIssues() {
        return detectedIssues;
    }

    public void setDetectedIssues(List<Issue> detectedIssues) {
        this.detectedIssues = detectedIssues;
    }

    // method to display a summary of this report
    public void displayReportSummary() {
        System.out.println("Mission Report Summary:");
        System.out.println("Mission ID: " + missionID);
        System.out.println("Report Generated On: " + dateGenerated);
        System.out.println("Total Maneuvers Logged: " + maneuvers.size());
        System.out.println("Current Fuel Usage: " + currentFuelUsage);
        System.out.println("Current Fuel Level: " + currentFuelLevel);
        System.out.println("Detected Issues: " + detectedIssues.size());
        for (Issue issue : detectedIssues) {
            System.out.println(" - Issue: " + issue.getIssueType() + ", Severity: " + issue.getSeverityLevel());
        }
    }
} 
