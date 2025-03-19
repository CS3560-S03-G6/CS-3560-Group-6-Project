
public class Spacecraft {
    private int spacecraftID;
    private int missionID;
    private String spacecraftName;
    private String spacecraftType;
    private String manufacturer;
    private int capacity;
    private int maxFuelCapacity;
    private int thrustPower;
    private int weight;
    private String status;

    //Constructor that takes in all necessary fields
    public Spacecraft(int spacecraftID, int missionID, String spacecraftName, String spacecraftType, String manufacturer, int capacity, int maxFuelCapacity, int thrustPower, int weight, String status){
        this.spacecraftID = spacecraftID;
        this.missionID = missionID;
        this.spacecraftName = spacecraftName;
        this.spacecraftType = spacecraftType;
        this.manufacturer = manufacturer;
        this.capacity = capacity;
        this.maxFuelCapacity = maxFuelCapacity;
        this.thrustPower = thrustPower;
        this.weight = weight;
        this.status = status;
    }

    //Getter for spacecraftID
    public int getSpacecraftID(){
        return spacecraftID;
    }

    //Setter for spacecraftID
    public void setSpacecraftID(int spacecraftID){
        this.spacecraftID = spacecraftID;
    }

    //Getter for missionID
    public int getMissionID(){
        return missionID;
    }

    //Setter for missionID
    public void setMissionID(int missionID){
        this.missionID = missionID;
    }

    //Getter for spacecraftName
    public String getSpacecraftName(){
        return spacecraftName;
    }

    //Setter for spacecraftName
    public void setSpacecraftName(String spacecraftName){
        this.spacecraftName = spacecraftName;
    }

    //Getter for spacecraftType
    public String getSpacecraftType(){
        return spacecraftType;
    }

    //Setter for spacecraftType
    public void setSpacecraftType(String spacecraftType){
        this.spacecraftType = spacecraftType;
    }

    //Getter for manufacturer
    public String getManufacturer(){
        return manufacturer;
    }

    //Setter for manufacturer
    public void setManufacturer(String manufacturer){
        this.manufacturer = manufacturer;
    }

    //Getter for capacity
    public int getCapacity(){
        return capacity;
    }

    //Setter for capacity
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    //Getter for maxFuelCapacity
    public int getMaxFuelCapacity(){
        return maxFuelCapacity;
    }

    //Setter for maxFuelCapacity
    public void setMaxFuelCapacity(int maxFuelCapacity){
        this.maxFuelCapacity = maxFuelCapacity;
    }

    //Getter for thrustPower
    public int getThrustPower(){
        return thrustPower;
    }

    //Setter for thrustPower
    public void setThrustPower(int thrustPower){
        this.thrustPower = thrustPower;
    }

    //Getter for weight
    public int getWeight(){
        return weight;
    }

    //Setter for weight
    public void setWeight(int weight){
        this.weight = weight;
    }

    //Getter for status
    public String getStatus(){
        return status;
    }

    //Setter for status
    public void setStatus(String status){
        this.status = status;
    }
}
