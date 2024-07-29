package WildlifeMonitoringApplication;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;
public class WildlifeMonitoringApplicationMain {
    private static AnimalDAO animalDAO = new AnimalDAO();
    private static SensorDataDAO sensorDataDAO = new SensorDataDAO();
    private static SensorDataCollector sensorDataCollector = new SensorDataCollector();
    private static GPSWildlifeTrackingAndMapping gpsWildlifeTrackingAndMapping = new GPSWildlifeTrackingAndMapping();
    private static Scanner scanner = new Scanner(System.in);
    private static Registration registration = new Registration();
    private static EnvironmentalMonitor monitor = new EnvironmentalMonitor();
    

    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println("--------------------------------------------------");
            System.out.println("\n**********Wildlife Monitoring Application***********");
            System.out.println("\n--------------------------------------------------");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println("--------------------------------------------------");
            System.out.println("Enter your choice:");
            System.out.println("--------------------------------------------------");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
            case 1:
                registration.registers();
                break;
            case 2:
                registration.login();
                break;
                case 3:
                    System.out.println("--------------------------------------------------");
                    System.out.println("\n************Thank You Visit Again***************");
                    System.out.println("\n--------------------------------------------------");
                    return;
                default:
                    System.out.println("\nInvalid Input");
                    break;
            }

            // After successful login, display the main menu
            while (true) {
                System.out.println("--------------------------------------------------");
                System.out.println("\n**********Wildlife Monitoring Application***********");
                System.out.println("\n--------------------------------------------------");
                System.out.println("1. Add Animal");
                System.out.println("2. View Animals");
                System.out.println("3. Delete Animals");
                System.out.println("4. Update Animal Location");
                System.out.println("5. Update Animal Health Status");
                System.out.println("6. Update Animal Sensor Data");
                System.out.println("7. View Sensor Data");
                System.out.println("8. Simulate Animal Movement");
                System.out.println("9. Simulate Animal Health Change");
                System.out.println("10. Simulate Animal Behavior Change");
                System.out.println("11. Simulate Sensor Data Collection");
                System.out.println("12. GPS Wildlife Tracking and Mapping");
                System.out.println("13. Monitor Environmental Conditions");
                System.out.println("14. Logout");
                System.out.println("--------------------------------------------------");
                System.out.println("Enter your choice:");
                System.out.println("--------------------------------------------------");
                choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        addAnimal();
                        break;
                    case 2:
                        viewAnimals();
                        break;
                    case 3:
                    	deleteAnimal();
                    	break;
                    case 4:
                        updateAnimalLocation();
                        break;
                    case 5:
                        updateAnimalHealthStatus();
                        break;
                    case 6:
                        updateAnimalSensorData();
                        break;
                    case 7:
                        viewSensorData();
                        break;
                    case 8:
                        simulateAnimalMovement();
                        break;
                    case 9:
                        simulateAnimalHealthChange();
                        break;
                    case 10:
                        simulateAnimalBehaviorChange();
                        break;
                    case 11:
                        simulateSensorDataCollection();
                        break;
                    case 12:
                        gpsWildlifeTrackingAndMappingMenu();
                        break;
                    case 13:
                        monitorEnvironmentalConditions(monitor);
                        break;
                    case 14:
                    	System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("\nInvalid Input");
                        break;
                }
            }
        }
    }

    private static void addAnimal() {
        System.out.print("Enter Name: ");
        String species = scanner.nextLine();
        double[] coordinates = gpsWildlifeTrackingAndMapping.getRandomCoordinates();
        double latitude = coordinates[0];
        double longitude = coordinates[1];
        System.out.print("Enter Health Status: ");
        String healthStatus = scanner.nextLine();
        System.out.print("Enter Behavior: ");
        String behavior = scanner.nextLine();

        Animal animal = new Animal(0, species, latitude, longitude, healthStatus, behavior);
        animalDAO.addAnimal(animal);
        System.out.println("Animal added successfully.");
    }

    private static void viewAnimals() {
        List<Animal> animals = animalDAO.getAllAnimals();
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }
    private static void deleteAnimal() {
        System.out.print("Enter Animal ID to delete: ");
        int animalId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        try {
            animalDAO.deleteAnimal(animalId);
            System.out.println("Animal deleted successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateAnimalLocation() {
        System.out.print("Enter Animal ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        double[] coordinates = gpsWildlifeTrackingAndMapping.getRandomCoordinates();
        double latitude = coordinates[0];
        double longitude = coordinates[1];

        animalDAO.updateAnimalLocation(id, latitude, longitude);
        System.out.println("Animal location updated successfully.");
    }

    private static void updateAnimalHealthStatus() {
        System.out.print("Enter Animal ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter New Health Status: ");
        String newHealthStatus = scanner.nextLine();

        animalDAO.updateAnimalHealthStatus(id, newHealthStatus);
        System.out.println("Animal health status updated successfully.");
    }

    private static void updateAnimalSensorData() {
        System.out.print("Enter Animal ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        SensorDataCollector sensorDataCollector = new SensorDataCollector();

        try {
            double temperature = sensorDataCollector.collectTemperatureData();
            double humidity = sensorDataCollector.collectHumidityData();
            double heartRate = sensorDataCollector.collectHeartRateData();
            double latitude = sensorDataCollector.collectLatitudeData();
            double longitude = sensorDataCollector.collectLongitudeData();
            double healthStatus = sensorDataCollector.collectHealthStatusData();
            double behavior = sensorDataCollector.collectBehaviorData();

            sensorDataDAO.addSensorData(new SensorData(0, id, "Temperature", temperature, new Timestamp(System.currentTimeMillis())));
            sensorDataDAO.addSensorData(new SensorData(0, id, "Humidity", humidity, new Timestamp(System.currentTimeMillis())));
            sensorDataDAO.addSensorData(new SensorData(0, id, "Heart Rate", heartRate, new Timestamp(System.currentTimeMillis())));
            sensorDataDAO.addSensorData(new SensorData(0, id, "Latitude", latitude, new Timestamp(System.currentTimeMillis())));
            sensorDataDAO.addSensorData(new SensorData(0, id, "Longitude", longitude, new Timestamp(System.currentTimeMillis())));
            sensorDataDAO.addSensorData(new SensorData(0, id, "Health Status", healthStatus, new Timestamp(System.currentTimeMillis())));
            sensorDataDAO.addSensorData(new SensorData(0, id, "Behavior", behavior, new Timestamp(System.currentTimeMillis())));
            System.out.println("Animal sensor data updated successfully.");
        } catch (Exception e) {
            System.out.println("Error updating animal sensor data: " + e.getMessage());
        }
    }

    private static void viewSensorData() {
        System.out.print("Enter Animal ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        List<SensorData> sensorDataList = sensorDataDAO.getSensorDataByAnimalId(id);
        for (SensorData sensorData : sensorDataList) {
            System.out.println(sensorData);
        }
    }

    private static void simulateAnimalMovement() {
        gpsWildlifeTrackingAndMapping.trackWildlife();
    }

    private static void simulateAnimalHealthChange() {
        System.out.print("Simulating Animal Health Change...");
        animalDAO.simulateHealthChange();
        System.out.println("Health status simulated successfully.");
    }

    private static void simulateAnimalBehaviorChange() {
        System.out.print("Simulating Animal Behavior Change...");
        animalDAO.simulateBehaviorChange();
        System.out.println("Behavior simulated successfully.");
    }

    private static void simulateSensorDataCollection() {
        System.out.print("Simulating Sensor Data Collection...");
        sensorDataCollector.simulateSensorDataCollection();
        System.out.println("Sensor data collection simulated successfully.");
    }

    private static void gpsWildlifeTrackingAndMappingMenu() {
        System.out.println("1. Track Wildlife");
        System.out.println("2. Map Wildlife Locations");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1:
                gpsWildlifeTrackingAndMapping.trackWildlife();
                break;
            case 2:
                gpsWildlifeTrackingAndMapping.mapWildlifeLocations();
                break;
            default:
                System.out.println("\nInvalid Input");
                break;
        }
    }
        private static void monitorEnvironmentalConditions(EnvironmentalMonitor monitor) {
            System.out.print("Enter Animal ID: ");
            int animalId = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            monitor.monitorTemperature(animalId);
            monitor.monitorRainfall(animalId);
            monitor.monitorVegetation(animalId);
        
    }
}
