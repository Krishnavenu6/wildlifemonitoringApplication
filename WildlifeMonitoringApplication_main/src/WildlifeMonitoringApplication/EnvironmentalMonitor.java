package WildlifeMonitoringApplication;
import java.util.List;

public class EnvironmentalMonitor {
    private static final String SENSOR_TYPE_TEMPERATURE = "temperature";
    private static final String SENSOR_TYPE_RAINFALL = "rainfall";
    private static final String SENSOR_TYPE_VEGETATION = "vegetation";

    private SensorDataDAO sensorDataDAO;
    private AnimalDAO animalDAO;

    public EnvironmentalMonitor() {
        sensorDataDAO = new SensorDataDAO();
        animalDAO = new AnimalDAO();
    }

    public void monitorTemperature(int animalId) {
        Animal animal = animalDAO.getAnimal(animalId);
        if (animal != null) {
            List<SensorData> sensorData = getSensorData(animalId, SENSOR_TYPE_TEMPERATURE);
            if (!sensorData.isEmpty()) {
                double temperature = sensorData.get(sensorData.size() - 1).getValue();
                if (temperature > 30) {
                    System.out.println("Temperature is high for " + animal.getSpecies() + ". Animal may be stressed.");
                    sendAlert(animal, "Temperature is high. Animal may be stressed.");
                } else if (temperature < 20) {
                    System.out.println("Temperature is low for " + animal.getSpecies() + ". Animal may be stressed.");
                    sendAlert(animal, "Temperature is low. Animal may be stressed.");
                } else {
                    System.out.println("Temperature is normal for " + animal.getSpecies() + ".");
                }
            } else {
                System.out.println("No temperature sensor data available for " + animal.getSpecies() + ".");
            }
        } else {
            System.out.println("Animal with ID " + animalId + " not found.");
        }
    }

    public void monitorRainfall(int animalId) {
        Animal animal = animalDAO.getAnimal(animalId);
        if (animal != null) {
            List<SensorData> sensorData = getSensorData(animalId, SENSOR_TYPE_RAINFALL);
            if (!sensorData.isEmpty()) {
                double rainfall = sensorData.get(sensorData.size() - 1).getValue();
                if (rainfall > 100) {
                    System.out.println("Rainfall is high for " + animal.getSpecies() + ". Animal may be affected.");
                    sendAlert(animal, "Rainfall is high. Animal may be affected.");
                } else if (rainfall < 10) {
                    System.out.println("Rainfall is low for " + animal.getSpecies() + ". Animal may be affected.");
                    sendAlert(animal, "Rainfall is low. Animal may be affected.");
                } else {
                    System.out.println("Rainfall is normal for " + animal.getSpecies() + ".");
                }
            } else {
                System.out.println("No rainfall sensor data available for " + animal.getSpecies() + ".");
            }
        } else {
            System.out.println("Animal with ID " + animalId + " not found.");
        }
    }

    public void monitorVegetation(int animalId) {
        Animal animal = animalDAO.getAnimal(animalId);
        if (animal != null) {
            List<SensorData> sensorData = getSensorData(animalId, SENSOR_TYPE_VEGETATION);
            if (!sensorData.isEmpty()) {
                double vegetationCover = sensorData.get(sensorData.size() - 1).getValue();
                if (vegetationCover > 0.5) {
                    System.out.println("Vegetation cover is high for " + animal.getSpecies() + ". Animal may be affected.");
                    sendAlert(animal, "Vegetation cover is high. Animal may be affected.");
                } else if (vegetationCover < 0.1) {
                    System.out.println("Vegetation cover is low for " + animal.getSpecies() + ". Animal may be affected.");
                    sendAlert(animal, "Vegetation cover is low. Animal may be affected.");
                } else {
                    System.out.println("Vegetation cover is normal for " + animal.getSpecies() + ".");
                }
            } else {
                System.out.println("No vegetation sensor data available for " + animal.getSpecies() + ".");
            }
        } else {
            System.out.println("Animal with ID " + animalId + " not found.");
        }
    }

    private List<SensorData> getSensorData(int animalId, String sensorType) {
        return sensorDataDAO.getSensorDataByAnimalIdAndType(animalId, sensorType);
    }

    private void sendAlert(Animal animal, String message) {
        // Code to send alert to stakeholders or authorities
        System.out.println("Alert sent for " + animal.getSpecies() + ": " + message);
    }
}