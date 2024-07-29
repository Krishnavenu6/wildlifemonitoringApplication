package WildlifeMonitoringApplication;
import java.sql.Timestamp;

public class SensorData {
    private int id;
    private int animalId;
    private String sensorType;
    private double value;
    private Timestamp timestamp;

    public SensorData(int id, int animalId, String sensorType, double value, Timestamp timestamp) {
        this.id = id;
        this.animalId = animalId;
        this.sensorType = sensorType;
        this.value = value;
        this.timestamp = timestamp;
    }

    // Getters and setters

    @Override
    public String toString() {
        return "SensorData [ID=" + id + ", Animal ID=" + animalId + ", Sensor Type=" + sensorType +
                ", Value=" + value + ", Timestamp=" + timestamp + "]";
    }

    public int getAnimalId() {
        return animalId;
    }

    public String getSensorType() {
        return sensorType;
    }

    public double getValue() {
        return value;
    }

    public void setId(int int1) {
        id = int1;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    
    public void analyzeData() {
        // Analyze the sensor data
        System.out.println("Analyzing Sensor Data:");
        System.out.println("ID: " + id);
        System.out.println("Animal ID: " + animalId);
        System.out.println("Sensor Type: " + sensorType);
        System.out.println("Value: " + value);
        System.out.println("Timestamp: " + timestamp);
        System.out.println();
    }

    public void visualizeData() {
        // Visualize the sensor data
        System.out.println("Visualizing Sensor Data:");
        System.out.println("ID: " + id);
        System.out.println("Animal ID: " + animalId);
        System.out.println("Sensor Type: " + sensorType);
        System.out.println("Value: " + value);
        System.out.println("Timestamp: " + timestamp);
        System.out.println();
    }
}
