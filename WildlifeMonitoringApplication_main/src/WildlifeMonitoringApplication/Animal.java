package WildlifeMonitoringApplication;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Animal{
    private int id;
    private String species;
    private double latitude;
    private double longitude;
    private String healthStatus;
    private String behavior;
    private Map<String, Double> sensorData;

    public Animal(int id, String species, double latitude, double longitude, String healthStatus, String behavior) {
        this.id = id;
        this.species = species;
        this.latitude = latitude;
        this.longitude = longitude;
        this.healthStatus = healthStatus;
        this.behavior = behavior;
        this.sensorData = new HashMap<>();
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public Map<String, Double> getSensorData() {
        return sensorData;
    }

    public void setSensorData(Map<String, Double> sensorData) {
        this.sensorData = sensorData;
    }

    @Override
    public String toString() {
        return "Animal [ID=" + id + ", Species=" + species + ", Latitude=" + latitude + ", Longitude=" + longitude +
                ", Health Status=" + healthStatus + ", Behavior=" + behavior + "]";
    }

    // Method to update animal's location
    public void updateLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Method to update animal's health status
    public void updateHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    // Method to update animal's behavior
    public void updateBehavior(String behavior) {
        this.behavior = behavior;
    }

    // Method to add sensor data
    public void addSensorData(String sensorType, double value) {
        sensorData.put(sensorType, value);
    }

    // Method to simulate animal movement
    public void simulateMovement() {
        Random random = new Random();
        double latitudeChange = (random.nextDouble() * 2 - 1) * 0.01; // Random latitude change between -0.01 and 0.01
        double longitudeChange = (random.nextDouble() * 2 - 1) * 0.01; // Random longitude change between -0.01 and 0.01

        latitude += latitudeChange;
        longitude += longitudeChange;
    }

    // Method to simulate animal health change
    public void simulateHealthChange() {
        Random random = new Random();
        int healthChange = random.nextInt(3) - 1; // Random health change between -1 and 1

        switch (healthChange) {
            case -1:
                healthStatus = "Poor";
                break;
            case 0:
                healthStatus = "Good";
                break;
            case 1:
                healthStatus = "Excellent";
                break;
        }
    }

    // Method to simulate animal behavior change
    public void simulateBehaviorChange() {
        Random random = new Random();
        int behaviorChange = random.nextInt(3) - 1; // Random behavior change between -1 and 1

        switch (behaviorChange) {
            case -1:
                behavior = "Resting";
                break;
            case 0:
                behavior = "Foraging";
                break;
            case 1:
                behavior = "Moving";
                break;
        }
    }

    // Method to simulate sensor data collection
    public void simulateSensorDataCollection() {
        Random random = new Random();
        addSensorData("Heart Rate", random.nextDouble() * 100);
        addSensorData("Body Temperature", random.nextDouble() * 10 + 35);
        addSensorData("Activity Level", random.nextDouble() * 5);
    }
}