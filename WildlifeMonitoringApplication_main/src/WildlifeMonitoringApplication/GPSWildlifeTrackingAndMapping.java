package WildlifeMonitoringApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GPSWildlifeTrackingAndMapping {
    private Random random;
    private List<Geofence> geofences;
    private List<CriticalHabitat> criticalHabitats;

    public GPSWildlifeTrackingAndMapping() {
        random = new Random();
        geofences = new ArrayList<>();
        criticalHabitats = new ArrayList<>();
    }

    public double[] getRandomCoordinates() {
        double latitude = -90 + (90 - (-90)) * random.nextDouble();
        double longitude = -180 + (180 - (-180)) * random.nextDouble();
        return new double[]{latitude, longitude};
    }

    public void trackWildlife() {
        for (int i = 0; i < 10; i++) {
            double[] coordinates = getRandomCoordinates();
            double speed = random.nextDouble() * 10 + 1; // Random speed between 1-10 km/h
            double direction = random.nextDouble() * 360; // Random direction in degrees

            // Check for geofence alerts
            checkGeofenceAlert(coordinates);

            // Print the tracked data
            System.out.println("Time: " + i + " minutes");
            System.out.println("Latitude: " + coordinates[0]);
            System.out.println("Longitude: " + coordinates[1]);
            System.out.println("Speed: " + speed + " km/h");
            System.out.println("Direction: " + direction + " degrees");
            System.out.println();

            // Simulate sensor data collection
            simulateSensorDataCollection();
        }
    }

    public void mapWildlifeLocations() {
        // Simulate creating geofences and identifying critical habitats
        for (int i = 0; i < 3; i++) {
            Geofence geofence = new Geofence(getRandomCoordinates(), random.nextDouble() * 10 + 1);
            CriticalHabitat criticalHabitat = new CriticalHabitat(getRandomCoordinates(), random.nextDouble() * 10 + 1);

            geofences.add(geofence);
            criticalHabitats.add(criticalHabitat);

            // Print the geofence and critical habitat data
            System.out.println("Geofence " + (i + 1) + ":");
            System.out.println(geofence);
            System.out.println();

            System.out.println("Critical Habitat " + (i + 1) + ":");
            System.out.println(criticalHabitat);
            System.out.println();
        }
    }

    public void simulateAnimalMovement() {
        double[] coordinates = getRandomCoordinates();
        double speed = random.nextDouble() * 10 + 1; // Random speed between 1-10 km/h
        double direction = random.nextDouble() * 360; // Random direction in degrees

        System.out.println("Animal Movement:");
        System.out.println("Latitude: " + coordinates[0]);
        System.out.println("Longitude: " + coordinates[1]);
        System.out.println("Speed: " + speed + " km/h");
        System.out.println("Direction: " + direction + " degrees");
        System.out.println();
    }

    public void simulateAnimalHealthChange() {
        String healthStatus = random.nextBoolean() ? "Good" : "Poor";

        System.out.println("Animal Health Change:");
        System.out.println("Health Status: " + healthStatus);
        System.out.println();
    }

    public void simulateAnimalBehaviorChange() {
        String behavior = random.nextBoolean() ? "Foraging" : "Resting";

        System.out.println("Animal Behavior Change:");
        System.out.println("Behavior: " + behavior);
        System.out.println();
    }

    public void simulateSensorDataCollection() {
        double heartRate = random.nextDouble() * 100;
        double bodyTemperature = random.nextDouble() * 10 + 35;
        double activityLevel = random.nextDouble() * 5;

        System.out.println("Sensor Data:");
        System.out.println("Heart Rate: " + heartRate + " bpm");
        System.out.println("Body Temperature: " + bodyTemperature + "°C");
        System.out.println("Activity Level: " + activityLevel + " units");
        System.out.println();
    }

    private void checkGeofenceAlert(double[] currentCoordinates) {
        for (Geofence geofence : geofences) {
            if (geofence.isInside(currentCoordinates)) {
                System.out.println("Alert: Animal entered Geofence!");
            } else {
                System.out.println("Alert: Animal exited Geofence!");
            }
        }
    }

    private static class Geofence {
        private double[] center;
        private double radius;

        public Geofence(double[] center, double radius) {
            this.center = center;
            this.radius = radius;
        }

        public boolean isInside(double[] coordinates) {
            double distance = Math.sqrt(Math.pow(coordinates[0] - center[0], 2) + Math.pow(coordinates[1] - center[1], 2));
            return distance <= radius;
        }

        @Override
        public String toString() {
            return "Center: [" + center[0] + ", " + center[1] + "], Radius: " + radius + " km";
        }
    }

    private static class CriticalHabitat {
        private double[] center;
        private double radius;

        public CriticalHabitat(double[] center, double radius) {
            this.center = center;
            this.radius = radius;
        }

        @Override
        public String toString() {
            return "Center: [" + center[0] + ", " + center[1] + "], Radius: " + radius + " km";
        }
    }
}