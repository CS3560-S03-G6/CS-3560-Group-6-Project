public class Spacecraft {
    private int spacecraftID;
    private int missionID;
    private String spacecraftName;
    private String model;
    private int thrust;
    private int maxFuelCapacity;

    // constructor
    public Spacecraft(int spacecraftID, int missionID, String spacecraftName, String model, int thrust, int maxFuelCapacity) {
        this.spacecraftID = spacecraftID;
        this.missionID = missionID;
        this.spacecraftName = spacecraftName;
        this.model = model;
        this.thrust = thrust;
        this.maxFuelCapacity = maxFuelCapacity;
    }

    // getter and setter for spacecraftID
    public int getSpacecraftID() {
        return spacecraftID;
    }

    public void setSpacecraftID(int spacecraftID) {
        this.spacecraftID = spacecraftID;
    }

    // getter and setter for missionID
    public int getMissionID() {
        return missionID;
    }

    public void setMissionID(int missionID) {
        this.missionID = missionID;
    }

    // getter and setter for spacecraftName
    public String getSpacecraftName() {
        return spacecraftName;
    }

    public void setSpacecraftName(String spacecraftName) {
        this.spacecraftName = spacecraftName;
    }

    // getter and setter for model
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    // getter and setter for thrust
    public int getThrust() {
        return thrust;
    }

    public void setThrust(int thrust) {
        this.thrust = thrust;
    }

    // getter and setter for maxFuelCapacity
    public int getMaxFuelCapacity() {
        return maxFuelCapacity;
    }

    public void setMaxFuelCapacity(int maxFuelCapacity) {
        this.maxFuelCapacity = maxFuelCapacity;
    }

    // method to display spacecraft details
    public void displaySpacecraftDetails() {
        System.out.println("Spacecraft Details:");
        System.out.println("Name: " + spacecraftName);
        System.out.println("Model: " + model);
        System.out.println("Thrust: " + thrust);
        System.out.println("Max Fuel Capacity: " + maxFuelCapacity);
    }
} 
