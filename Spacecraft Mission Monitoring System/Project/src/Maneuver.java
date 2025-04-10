
public class Maneuver {
    private int maneuverID;
    private int missionID;
    private String maneuverType;
    private String maneuverDetails;
    private int executionTime;
    private int fuelCost;
    private boolean locationChange;
    private String status;
    private String loggedBy;
    private int loggedTime;

    //Constructor that takes in all necessary fields
    public Maneuver(int maneuverID, int missionID, String maneuverType, String maneuverDetails, int executionTime, int fuelCost, boolean locationChange, String status, String loggedBy, int loggedTime){
        this.maneuverID = maneuverID;
        this.missionID = missionID;
        this.maneuverType = maneuverType;
        this.maneuverDetails = maneuverDetails;
        this.executionTime = executionTime;
        this.fuelCost = fuelCost;
        this.locationChange = locationChange;
        this.status = status;
        this.loggedBy = loggedBy;
        this.loggedTime = loggedTime;
    }

    //Getter for maneuverID
    public int getManeuverID(){
        return maneuverID;
    }

    //Setter for maneuverID
    public void setManeuverID(int maneuverID){
        this.maneuverID = maneuverID;
    }

    //Getter for missionID
    public int getMissionID(){
        return missionID;
    }

    //Setter for missionID
    public void setMissionID(int missionID){
        this.missionID = missionID;
    }

    //Getter for maneuverType
    public String getManeuverType(){
        return maneuverType;
    }

    //Setter for maneuverType
    public void setManeuverType(String maneuverType){
        this.maneuverType = maneuverType;
    }

    //Getter for maneuverDetails
    public String getManeuverDetails(){
        return maneuverDetails;
    }

    //Setter for maneuverDetails
    public void setManeuverDetails(String maneuverDetails){
        this.maneuverDetails = maneuverDetails;
    }

    //Getter for executionTime
    public int getExecutionTime(){
        return executionTime;
    }

    //Setter for executionTime
    public void setExecutionTime(int executionTime){
        this.executionTime = executionTime;
    }

    //Getter for fuelCost
    public int getFuelCost(){
        return fuelCost;
    }

    //Setter for fuelCost
    public void setFuelCost(int fuelCost){
        this.fuelCost = fuelCost;
    }

    //Getter for locationChange
    public boolean getLocationChange(){
        return locationChange;
    }

    //Setter for locationChange
    public void setLocationChange(boolean locationChange){
        this.locationChange = locationChange;
    }

    //Getter for status
    public String getStatus(){
        return status;
    }

    //Setter for status
    public void setStatus(String status){
        this.status = status;
    }

    //Getter for loggedBy
    public String getLoggedBy(){
        return loggedBy;
    }

    //Setter for loggedBy
    public void setLoggedBy(String loggedBy){
        this.loggedBy = loggedBy;
    }

    //Getter for loggedTime
    public int getLoggedTime(){
        return loggedTime;
    }

    //Setter for loggedTime
    public void setLoggedTime(int loggedTime){
        this.loggedTime = loggedTime;
    }
}
