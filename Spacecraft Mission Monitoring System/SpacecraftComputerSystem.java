
public class SpacecraftComputerSystem {
    private int systemID;
    private int spacecraftID;
    private String communicationStatus;

    //Constructor that takes in all necessary fields
    public SpacecraftComputerSystem(int systemID, int spacecraftID, String communicationStatus){
        this.systemID = systemID;
        this.spacecraftID = spacecraftID;
        this.communicationStatus = communicationStatus;
    }

    //Getter for systemID
    public int getSystemID(){
        return systemID;
    }

    //Setter for systemID
    public void setSystemID(int systemID){
        this.systemID = systemID;
    }

    //Getter for spacecraftID
    public int getSpacecraftID(){
        return spacecraftID;
    }

    //Setter for spacecraftID
    public void setSpacecraftID(int spacecraftID){
        this.spacecraftID = spacecraftID;
    }

    //Getter for communicationStatus
    public String getCommunicationStatus(){
        return communicationStatus;
    }

    //Setter for communicationStatus
    public void setCommunicationStatus(String communicationStatus){
        this.communicationStatus = communicationStatus;
    }

    // additonal methods:
    // Additional methods

    /**
     * Logs an executed maneuver with system-level tracking.
     */
    public void logExecutedManeuver(Maneuver maneuver) {
        // Store maneuver and update status
    }

    /**
     * Triggers system shutdown or switch to safe mode for the mission.
     */
    public void terminateMission(int missionID, String reason) {
        // Execute shutdown or safe mode
    }

    /**
     * Scans all missions and triggers alerts for fuel or maneuver failures.
     */
    public void alertSpacecraftComputerSystem(List<Mission> missions, List<Spacecraft> spacecrafts, List<Maneuver> maneuvers) {
        // Loop through missions and identify issues
    }

    /**
     * Generates a report summarizing a mission's current state.
     */
    public MissionReport generateMissionReport(Mission mission, List<Maneuver> maneuvers, List<Issue> issues) {
        // Build and return report
        return null; // Placeholder
    }

}
