package WildlifeMonitoringApplication;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SensorDataDAO {
    public void addSensorData(SensorData sensorData) {
        String query = "INSERT INTO SensorData (animal_id, sensorType, value) VALUES (?, ?, ?)";

        try (Connection conn = Databaseconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, sensorData.getAnimalId());
            stmt.setString(2, sensorData.getSensorType());
            stmt.setDouble(3, sensorData.getValue());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    sensorData.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SensorData> getSensorDataByAnimalId(int animalId) {
        List<SensorData> sensorDataList = new ArrayList<>();
        String query = "SELECT * FROM SensorData WHERE animal_id = ?";

        try (Connection conn = Databaseconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, animalId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    SensorData sensorData = new SensorData(
                            rs.getInt("id"),
                            rs.getInt("animal_id"),
                            rs.getString("sensorType"),
                            rs.getDouble("value"),
                            rs.getTimestamp("timestamp")
                    );
                    sensorDataList.add(sensorData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sensorDataList;
    }

    public List<SensorData> getSensorDataBySensorType(String sensorType) {
        List<SensorData> sensorDataList = new ArrayList<>();
        String query = "SELECT * FROM SensorData WHERE sensorType = ?";

        try (Connection conn = Databaseconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, sensorType);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    SensorData sensorData = new SensorData(
                            rs.getInt("id"),
                            rs.getInt("animal_id"),
                            rs.getString("sensorType"),
                            rs.getDouble("value"),
                            rs.getTimestamp("timestamp")
                    );
                    sensorDataList.add(sensorData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sensorDataList;
    }

    public List<SensorData> getSensorDataByDate(Timestamp startDate, Timestamp endDate) {
        List<SensorData> sensorDataList = new ArrayList<>();
        String query = "SELECT * FROM SensorData WHERE timestamp BETWEEN ? AND ?";

        try (Connection conn = Databaseconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setTimestamp(1, startDate);
            stmt.setTimestamp(2, endDate);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    SensorData sensorData = new SensorData(
                            rs.getInt("id"),
                            rs.getInt("animal_id"),
                            rs.getString("sensorType"),
                            rs.getDouble("value"),
                            rs.getTimestamp("timestamp")
                    );
                    sensorDataList.add(sensorData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sensorDataList;
    }
    
	private static AnimalDAO animalDAO = new AnimalDAO();

	public List<SensorData> getSensorDataByAnimalIdAndType(int animalId, String sensorType) {
		// TODO Auto-generated method stub
		return null;
	}

	public static AnimalDAO getAnimalDAO() {
		return animalDAO;
	}

	public static void setAnimalDAO(AnimalDAO animalDAO) {
		SensorDataDAO.animalDAO = animalDAO;
	}
}