import java.util.List;

public class Issue {
    private int issueID;
    private int missionID;
    private String issueType;
    private String detectionTime; // <-- changed from LocalDateTime to String
    private int severityLevel;
    private boolean alertTriggered;
    private String resolutionStatus;

    // constructor
    public Issue(int issueID, int missionID, String issueType, String detectionTime, int severityLevel,
                 boolean alertTriggered, String resolutionStatus) {
        this.issueID = issueID;
        this.missionID = missionID;
        this.issueType = issueType;
        this.detectionTime = detectionTime;
        this.severityLevel = severityLevel;
        this.alertTriggered = alertTriggered;
        this.resolutionStatus = resolutionStatus;
    }

    // checks if fuel is critically low based on initial fuel level and maneuver fuel usage
    public static boolean isFuelCritical(int initialFuelLevel, List<Maneuver> allManeuvers) {
        int totalFuelUsed = 0;
        for (Maneuver m : allManeuvers) {
            totalFuelUsed += m.getFuelCost();
        }
        return totalFuelUsed >= initialFuelLevel;
    }

    // checks if a failed maneuver exists
    public static boolean hasFailedManeuver(List<Maneuver> allManeuvers) {
        for (Maneuver m : allManeuvers) {
            if (m.getStatus().equalsIgnoreCase("failed")) {
                return true;
            }
        }
        return false;
    }

    // generates a critical issue if detected
    public static Issue detectCriticalIssue(int missionID, int initialFuelLevel, List<Maneuver> allManeuvers) {
        String now = java.time.ZonedDateTime.now(java.time.ZoneId.of("America/Los_Angeles"))
                         .toString().substring(0, 16).replace("T", " ") + " PST";

        if (isFuelCritical(initialFuelLevel, allManeuvers)) {
            return new Issue(0, missionID, "Low Fuel", now, 5, true, "Unresolved");
        }

        if (hasFailedManeuver(allManeuvers)) {
            return new Issue(0, missionID, "Failed Maneuver", now, 4, true, "Unresolved");
        }

        return null;
    }

    // === Getters and setters ===

    public int getIssueID() {
        return issueID;
    }

    public void setIssueID(int issueID) {
        this.issueID = issueID;
    }

    public int getMissionID() {
        return missionID;
    }

    public void setMissionID(int missionID) {
        this.missionID = missionID;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getDetectionTime() {
        return detectionTime;
    }

    public void setDetectionTime(String detectionTime) {
        this.detectionTime = detectionTime;
    }

    public int getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(int severityLevel) {
        this.severityLevel = severityLevel;
    }

    public boolean isAlertTriggered() {
        return alertTriggered;
    }

    public void setAlertTriggered(boolean alertTriggered) {
        this.alertTriggered = alertTriggered;
    }

    public boolean getAlertTriggered() {
        return alertTriggered;
    }

    public String getResolutionStatus() {
        return resolutionStatus;
    }

    public void setResolutionStatus(String resolutionStatus) {
        this.resolutionStatus = resolutionStatus;
    }
}
