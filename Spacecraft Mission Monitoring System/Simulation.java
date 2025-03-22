
public class Simulation {
    private int simulationID;
    private int missionID;
    private int startDate;
    private int endDate;
    private int simulatedFuelUsage;
    private boolean simulatedLocationChange;
    private String[] potentialIssuesDetected;
    private String simulationStatus;
    private String simulationResults;

    //Constructor that takes in all necessary fields
    public Simulation(int simulationID, int missionID, int startDate, int endDate, int simulatedFuelUsage, boolean simulatedLocationChange, String[] potentialIssuesDetected, String simulationStatus, String simulationResults){
        this.simulationID = simulationID;
        this.missionID = missionID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.simulatedFuelUsage = simulatedFuelUsage;
        this.simulatedLocationChange = simulatedLocationChange;
        this.potentialIssuesDetected = potentialIssuesDetected;
        this.simulationStatus = simulationStatus;
        this.simulationResults = simulationResults;
    }

    //Getter for simulationID
    public int getSimulationID(){
        return simulationID;
    }

    //Setter for simulationID
    public void setSimulationID(int simulationID){
        this.simulationID = simulationID;
    }

    //Getter for missionID
    public int getMissionID(){
        return missionID;
    }

    //Setter for missionID
    public void setMissionID(int missionID){
        this.missionID = missionID;
    }

    //Getter for startDate
    public int getStartDate(){
        return startDate;
    }

    //Setter for startDate
    public void setStartDate(int startDate){
        this.startDate = startDate;
    }

    //Getter for endDate
    public int getEndDate(){
        return endDate;
    }

    //Setter for endDate
    public void setEndDate(int endDate){
        this.endDate = endDate;
    }

    //Getter for simulatedFuelUsage
    public int getSimulatedFuelUsage(){
        return simulatedFuelUsage;
    }

    //Setter for simulatedFuelUsage
    public void setSimulatedFuelUsage(int simulatedFuelUsage){
        this.simulatedFuelUsage = simulatedFuelUsage;
    }

    //Getter for simulatedLocationChange
    public boolean getSimulatedLocationChange(){
        return simulatedLocationChange;
    }

    //Setter for simulatedLocationChange
    public void setSimulatedLocationChange(boolean simulatedLocationChange){
        this.simulatedLocationChange = simulatedLocationChange;
    }

    //Getter for potentialIssuesDetected
    public String[] getPotentialIssuesDetected(){
        return potentialIssuesDetected;
    }

    //Setter for potentialIssuesDetected
    public void setPotentialIssuesDetected(String[] potentialIssuesDetected){
        this.potentialIssuesDetected = potentialIssuesDetected;
    }

    //Getter for simulationStatus
    public String getSimulationStatus(){
        return simulationStatus;
    }

    //Setter for simulationStatus
    public void setSimulationStatus(String simulationStatus){
        this.simulationStatus = simulationStatus;
    }

    //Getter for simulationResults
    public String getSimulationResults(){
        return simulationResults;
    }

    //Setter for simulationResults
    public void setSimulationResults(String simulationResults){
        this.simulationResults = simulationResults;
    }
}
