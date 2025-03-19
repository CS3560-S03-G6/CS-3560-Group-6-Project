
public class SpacecraftCrew {
    private int crewID;
    private int missionID;
    private int crewSize;
    private String[] crewMembers;
    private String[] crewHealthStatus;

    //Constructor that takes in all necessary fields
    public SpacecraftCrew(int crewID, int missionID, int crewSize, String[] crewMembers, String[] crewHealthStatus){
        this.crewID = crewID;
        this.missionID = missionID;
        this.crewSize = crewSize;
        this.crewMembers = crewMembers;
        this.crewHealthStatus = crewHealthStatus;
    }

    //Getter for crewID
    public int getCrewID(){
        return crewID;
    }

    //Setter for crewID
    public void setCrewID(int crewID){
        this.crewID = crewID;
    }

    //Getter for missionID
    public int getMissionID(){
        return missionID;
    }

    //Setter for missionID
    public void setMissionID(int missionID){
        this.missionID = missionID;
    }

    //Getter for crewSize
    public int getCrewSize(){
        return crewSize;
    }

    //Setter for crewSize
    public void setCrewSize(int crewSize){
        this.crewSize = crewSize;
    }

    //Getter for crewMembers
    public String[] getCrewMembers(){
        return crewMembers;
    }

    //Setter for crewMembers
    public void setCrewMembers(String[] crewMembers){
        this.crewMembers = crewMembers;
    }

    //Getter for crewHealthStatus
    public String[] getCrewHealthStatus(){
        return crewHealthStatus;
    }

    //Setter for crewHealthStatus
    public void setCrewHealthStatus(String[] crewHealthStatus){
        this.crewHealthStatus = crewHealthStatus;
    }
}
