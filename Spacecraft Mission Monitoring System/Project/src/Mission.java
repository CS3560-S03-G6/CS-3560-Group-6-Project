
public class Mission {
    private int missionID;
    private String missionName;
    private String missionType;
    private int launchDate;
    private String missionStatus;
    private String missionObjectives;
    private int initialFuelLevel;
    private String initialLocation;
    private int terminationDate;

    //Constructor that takes in all necessary fields
    public Mission(int missionID, String missionName, String missionType, int launchDate, String missionStatus, String missionObjectives, int initialFuelLevel, String initialLocation, int terminationDate){
        this.missionID = missionID;
        this.missionName = missionName;
        this.missionType = missionType;
        this.launchDate = launchDate;
        this.missionStatus = missionStatus;
        this.missionObjectives = missionObjectives;
        this.initialFuelLevel = initialFuelLevel;
        this.initialLocation = initialLocation;
        this.terminationDate = terminationDate;
    }

    //Getter for missionID
    public int getMissionID(){
        return missionID;
    }

    //Setter for missionID
    public void setMissionID(int missionID){
        this.missionID = missionID;
    }

    //Getter for missionName
    public String getMissionName(){
        return missionName;
    }

    //Setter for missionName
    public void setMissionName(String missionName){
        this.missionName = missionName;
    }

    //Getter for missionType
    public String getMissionType(){
        return missionType;
    }

    //Setter for missionType
    public void setMissionType(String missionType){
        this.missionType = missionType;
    }

    //Getter for launchDate
    public int getLaunchDate(){
        return launchDate;
    }

    //Setter for launchDate
    public void setLaunchDate(int launchDate){
        this.launchDate = launchDate;
    }

    //Getter for missionStatus
    public String getMissionStatus(){
        return missionStatus;
    }

    //Setter for missionStatus
    public void setMissionStatus(String missionStatus){
        this.missionStatus = missionStatus;
    }

    //Getter for missionObjectives
    public String getMissionObjectives(){
        return missionObjectives;
    }

    //Setter for missionObjectives
    public void setMissionObjectives(String missionObjectives){
        this.missionObjectives = missionObjectives;
    }

    //Getter for initialFuelLevel
    public int getInitialFuelLevel(){
        return initialFuelLevel;
    }

    //Setter for initialFuelLevel
    public void setInitialFuelLevel(int initialFuelLevel){
        this.initialFuelLevel = initialFuelLevel;
    }

    //Getter for initialLocation
    public String getInitialLocation(){
        return initialLocation;
    }

    //Setter for initialLocation
    public void setInitialLocation(String initialLocation){
        this.initialLocation = initialLocation;
    }

    //Getter for terminationDate
    public int getTerminationDate(){
        return terminationDate;
    }

    //Setter for terminationDate
    public void setTerminationDate(int terminationDate){
        this.terminationDate = terminationDate;
    }
}
