import java.time.LocalDateTime;
import java.util.List;

public class Issue {
    private int issueID;
    private int missionID;
    private String issueType;
    private LocalDateTime detectionTime;
    private int severityLevel;
    private boolean alertTriggered;
    private String resolutionStatus;

    // constructor
    public Issue(int issueID, int missionID, String issueType, LocalDateTime detectionTime, int severityLevel, boolean alertTriggered, String resolutionStatus) {
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

        // go through each maneuver and add up the fuelCost
        for (Maneuver m : allManeuvers) {
            totalFuelUsed += m.getFuelCost();
        }

        // critical if fuel used >= initial fuel
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
        LocalDateTime now = LocalDateTime.now();

        // severity level scale (1 = minor, 5 = most severe)
        // 5 = low fuel alert
        // 4 = failed maneuver alert

        if (isFuelCritical(initialFuelLevel, allManeuvers)) {
            return new Issue(0, missionID, "Low Fuel", now, 5, true, "Unresolved");
        }

        if (hasFailedManeuver(allManeuvers)) {
            return new Issue(0, missionID, "Failed Maneuver", now, 4, true, "Unresolved");
        }

        return null; // no critical issue detected
    }

    // getters and setters

    public int getIssueID() {
        return this.issueID;
    }

    public void setIssueID(int issueID) {
        this.issueID = issueID;
    }

    public int getMissionID() {
        return this.missionID;
    }

    public void setMissionID(int missionID) {
        this.missionID = missionID;
    }

    public String getIssueType() {
        return this.issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public LocalDateTime getDetectionTime() {
        return this.detectionTime;
    }

    public void setDetectionTime(LocalDateTime detectionTime) {
        this.detectionTime = detectionTime;
    }

    public int getSeverityLevel() {
        return this.severityLevel;
    }

    public void setSeverityLevel(int severityLevel) {
        this.severityLevel = severityLevel;
    }

    public boolean isAlertTriggered() {
        return this.alertTriggered;
    }

    public void setAlertTriggered(boolean alertTriggered) {
        this.alertTriggered = alertTriggered;
    }

    public String getResolutionStatus() {
        return this.resolutionStatus;
    }

    public void setResolutionStatus(String resolutionStatus) {
        this.resolutionStatus = resolutionStatus;
    }
}
