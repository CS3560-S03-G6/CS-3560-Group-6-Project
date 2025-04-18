public class SpaceCraftCrew {
    private int crewID;
    private int missionID;
    private int crewSize;
    private String crewCommander;
    private String crewHealthStatus;

    // constructor
    public SpaceCraftCrew(int crewID, int missionID, int crewSize, String crewCommander, String crewHealthStatus) {
        this.crewID = crewID;
        this.missionID = missionID;
        this.crewSize = crewSize;
        this.crewCommander = crewCommander;
        this.crewHealthStatus = crewHealthStatus;
    }

    // getter and setter for crewID
    public int getCrewID() {
        return crewID;
    }

    public void setCrewID(int crewID) {
        this.crewID = crewID;
    }

    // getter and setter for missionID
    public int getMissionID() {
        return missionID;
    }

    public void setMissionID(int missionID) {
        this.missionID = missionID;
    }

    // getter and setter for crewSize
    public int getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(int crewSize) {
        this.crewSize = crewSize;
    }

    // getter and setter for crewCommander
    public String getCrewCommander() {
        return crewCommander;
    }

    public void setCrewCommander(String crewCommander) {
        this.crewCommander = crewCommander;
    }

    // getter and setter for crewHealthStatus
    public String getCrewHealthStatus() {
        return crewHealthStatus;
    }

    public void setCrewHealthStatus(String crewHealthStatus) {
        this.crewHealthStatus = crewHealthStatus;
    }

    // this class just stores crew info, it doesn't handle logging maneuvers or any mission logic
} 
