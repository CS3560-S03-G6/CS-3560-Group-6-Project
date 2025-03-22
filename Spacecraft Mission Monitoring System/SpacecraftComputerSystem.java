
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

    // Logs a maneuver that has already been executed
    // Parameters: missionID and Maneuver object containing execution data
    public void logExecutedManeuver(String missionID, Maneuver executedManeuver) {
        // TODO: Add logic to log an executed maneuver to the mission record
    }
}
