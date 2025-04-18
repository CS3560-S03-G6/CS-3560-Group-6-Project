public class MissionReport {
    private int missionReportID;
    private int missionID;
    private LocalDateTime dateGenerated;
    private List<Maneuver> maneuversIncluded;  // all maneuvers, not just executed
    private int currentFuelUsage;
    private int currentFuelLevel;
    private List<Issue> detectedIssues;

    // constructor that takes in all fields
    public MissionReport(int missionReportID, int missionID, LocalDateTime dateGenerated,
                         List<Maneuver> maneuversIncluded, int currentFuelUsage,
                         int currentFuelLevel, List<Issue> detectedIssues) {
        this.missionReportID = missionReportID;
        this.missionID = missionID;
        this.dateGenerated = dateGenerated;
        this.maneuversIncluded = maneuversIncluded;
        this.currentFuelUsage = currentFuelUsage;
        this.currentFuelLevel = currentFuelLevel;
        this.detectedIssues = detectedIssues;
    }

    // getters and setters for all attributes

    public int getMissionReportID() {
        return missionReportID;
    }

    public void setMissionReportID(int missionReportID) {
        this.missionReportID = missionReportID;
    }

    public int getMissionID() {
        return missionID;
    }

    public void setMissionID(int missionID) {
        this.missionID = missionID;
    }

    public LocalDateTime getDateGenerated() {
        return dateGenerated;
    }

    public void setDateGenerated(LocalDateTime dateGenerated) {
        this.dateGenerated = dateGenerated;
    }

    public List<Maneuver> getManeuversIncluded() {
        return maneuversIncluded;
    }

    public void setManeuversIncluded(List<Maneuver> maneuversIncluded) {
        this.maneuversIncluded = maneuversIncluded;
    }

    public int getCurrentFuelUsage() {
        return currentFuelUsage;
    }

    public void setCurrentFuelUsage(int currentFuelUsage) {
        this.currentFuelUsage = currentFuelUsage;
    }

    public int getCurrentFuelLevel() {
        return currentFuelLevel;
    }

    public void setCurrentFuelLevel(int currentFuelLevel) {
        this.currentFuelLevel = currentFuelLevel;
    }

    public List<Issue> getDetectedIssues() {
        return detectedIssues;
    }

    public void setDetectedIssues(List<Issue> detectedIssues) {
        this.detectedIssues = detectedIssues;
    }
} 
