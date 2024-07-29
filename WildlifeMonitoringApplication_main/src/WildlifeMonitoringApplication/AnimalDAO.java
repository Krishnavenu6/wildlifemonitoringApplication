package WildlifeMonitoringApplication;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AnimalDAO {
    public void addAnimal(Animal animal) {
        String query = "INSERT INTO Animals (species, latitude, longitude, healthStatus, behavior) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Databaseconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, animal.getSpecies());
            stmt.setDouble(2, animal.getLatitude());
            stmt.setDouble(3, animal.getLongitude());
            stmt.setString(4, animal.getHealthStatus());
            stmt.setString(5, animal.getBehavior());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    animal.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Animal> getAllAnimals() {
        List<Animal> animals = new ArrayList<>();
        String query = "SELECT * FROM Animals";

        try (Connection conn = Databaseconnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Animal animal = new Animal(
                        rs.getInt("id"),
                        rs.getString("species"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude"),
                        rs.getString("healthStatus"),
                        rs.getString("behavior")
                );
                animals.add(animal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animals;
    }

    public void updateAnimalLocation(int id, double latitude, double longitude) {
        String query = "UPDATE Animals SET latitude = ?, longitude = ? WHERE id = ?";

        try (Connection conn = Databaseconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, latitude);
            stmt.setDouble(2, longitude);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAnimalHealthStatus(int id, String newHealthStatus) {
        String query = "UPDATE Animals SET healthStatus = ? WHERE id = ?";

        try (Connection conn = Databaseconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newHealthStatus);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAnimalBehavior(int id, String newBehavior) {
        String query = "UPDATE Animals SET behavior = ? WHERE id = ?";

        try (Connection conn = Databaseconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newBehavior);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAnimalSensorData(int id, String sensorType, double value) {
        String query = "INSERT INTO AnimalSensorData (animalId, sensorType, value) VALUES (?, ?, ?)";

        try (Connection conn = Databaseconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.setString(2, sensorType);
            stmt.setDouble(3, value);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Animal> getAnimalsWithinGeofence(double latitude, double longitude, double radius) {
        List<Animal> animalsWithinGeofence = new ArrayList<>();
        String query = "SELECT * FROM Animals WHERE " +
                "SQRT(POW(69.1 * (latitude - ?), 2) + POW(69.1 * (? - longitude) * COS(latitude / 57.3), 2)) < ?";

        try (Connection conn = Databaseconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, latitude);
            stmt.setDouble(2, longitude);
            stmt.setDouble(3, radius);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Animal animal = new Animal(
                            rs.getInt("id"),
                            rs.getString("species"),
                            rs.getDouble("latitude"),
                            rs.getDouble("longitude"),
                            rs.getString("healthStatus"),
                            rs.getString("behavior")
                    );
                    animalsWithinGeofence.add(animal);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animalsWithinGeofence;
    }

    public List<Animal> getAnimalsWithinCriticalHabitat(double latitude, double longitude, double radius) {
        List<Animal> animalsWithinCriticalHabitat = new ArrayList<>();
        String query = "SELECT * FROM Animals WHERE " +
                "SQRT(POW(69.1 * (latitude - ?), 2) + POW(69.1 * (? - longitude) * COS(latitude / 57.3), 2)) < ?";

        try (Connection conn = Databaseconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, latitude);
            stmt.setDouble(2, longitude);
            stmt.setDouble(3, radius);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Animal animal = new Animal(
                            rs.getInt("id"),
                            rs.getString("species"),
                            rs.getDouble("latitude"),
                            rs.getDouble("longitude"),
                            rs.getString("healthStatus"),
                            rs.getString("behavior")
                    );
                    animalsWithinCriticalHabitat.add(animal);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animalsWithinCriticalHabitat;
    }

    public void deleteAnimal(int id) {
        String query = "DELETE FROM Animals WHERE id = ?";

        try (Connection conn = Databaseconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void simulateHealthChange() {
        List<Animal> animals = getAllAnimals();
        for (Animal animal : animals) {
            String newHealthStatus = getRandomHealthStatus();
            updateAnimalHealthStatus(animal.getId(), newHealthStatus);
        }
    }

    public void simulateBehaviorChange() {
        List<Animal> animals = getAllAnimals();
        for (Animal animal : animals) {
            String newBehavior = getRandomBehavior();
            updateAnimalBehavior(animal.getId(), newBehavior);
        }
    }

    private String getRandomHealthStatus() {
        String[] healthStatuses = {"Good", "Poor", "Excellent"};
        int randomIndex = (int) (Math.random() * healthStatuses.length);
        return healthStatuses[randomIndex];
    }

    private String getRandomBehavior() {
        String[] behaviors = {"Resting", "Foraging", "Moving"};
        int randomIndex = (int) (Math.random() * behaviors.length);
        return behaviors[randomIndex];
    }

	public Animal getAnimal(int animalId) {
		// TODO Auto-generated method stub
		return null;
	}
}

