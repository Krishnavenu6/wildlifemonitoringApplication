package WildlifeMonitoringApplication;

import java.util.Random;

public class SensorDataCollector {
	// Private instance variable to generate random numbers
    private Random random;
 // Constructor to initialize the random number generator
    public SensorDataCollector() {
        random = new Random();
    }

    public double collectTemperatureData() {
        return 20 + (40 - 20) * random.nextDouble(); // Random temperature between 20 and 40 degrees 
    }

    public double collectHumidityData() {
        return 30 + (70 - 30) * random.nextDouble(); // Random humidity between 30% and 70%
    }

    public double collectHeartRateData() {
        return 60 + (100 - 60) * random.nextDouble(); // Random heart rate between 60 and 100 BPM
    }

    public double collectLatitudeData() {
        return 30 + (60 - 30) * random.nextDouble(); // Random latitude between 30 and 60 degrees
    }

    public double collectLongitudeData() {
        return 40 + (80 - 40) * random.nextDouble(); // Random longitude between 40 and 80 degrees
    }
    // Method to collect random health status data between 50% and 90%
    public double collectHealthStatusData() {
        return 50 + (90 - 50) * random.nextDouble(); // Random health status between 50 and 90%
    }

    public double collectBehaviorData() {
        return 20 + (80 - 20) * random.nextDouble(); // Random behavior between 20 and 80%
    }
         // Method to simulate the collection of sensor data
    public void simulateSensorDataCollection() {
    	// Collect sensor data
        double temperature = collectTemperatureData();
        double humidity = collectHumidityData();
        double heartRate = collectHeartRateData();
        double latitude = collectLatitudeData();
        double longitude = collectLongitudeData();
        double healthStatus = collectHealthStatusData();
        double behavior = collectBehaviorData();
         // Print the collected sensor data
        System.out.println("Temperature: " + temperature + "°C");
        System.out.println("Humidity: " + humidity + "%");
        System.out.println("Heart Rate: " + heartRate + " BPM");
        System.out.println("Latitude: " + latitude + "°");
        System.out.println("Longitude: " + longitude + "°");
        System.out.println("Health Status: " + healthStatus + "%");
        System.out.println("Behavior: " + behavior + "%");
    }
}


		


