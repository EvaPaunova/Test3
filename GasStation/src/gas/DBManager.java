package gas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import gas.Car.FuelType;

public class DBManager {
	
	private static final String DB_PASS = "ittstudent-123";
	private static final String DB_USER = "ittstudent";
	private static final String DB_PORT = "3306";
	private static final String DB_IP = "192.168.8.22";
	private static final String DB_NAME = "java_ee_s9";
	
	private static Connection connection;
	private static DBManager instance;
	
	public static synchronized DBManager getInstance() {
		if(instance == null) {
			instance = new DBManager();
		}
		return instance;
	}
	
	private DBManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Sorry, Driver not loaded or does not exist! Aborting.");
			return;
		}
		System.out.println("Driver loaded");
		//create connection
		try {
			connection = DriverManager.getConnection("jdbc:mysql://"+DB_IP+":"+DB_PORT+"/" + DB_NAME, DB_USER, DB_PASS);
		
		} catch (SQLException e) {
			System.out.println("Sorry, connection failed. Maybe wrong credentials?");
			System.out.println(e.getMessage());
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public void getAllLoadings() {
		String sql = "SELECt kolonka_id, fuel_type, fuel_quantity, loading_time FROM station_loadings";
		String sql1 = "SELECt kolonka_id, COUNT(*) FROM station_loadings GROUP BY kolonka_id HAVING Date(loading_time) = Dae.now()";
		String sql2 = "SELECt kolonka_id, SUM(fuel_quanity) FROM station_loadings GROUP BY fuel_type";
		String sql3 = "SELECt kolonka_id, fuel_type, fuel_quantity, loading_time FROM station_loadings";
		PreparedStatement s;
		TreeMap<String, TreeSet<Loading>> loadings = new TreeMap<>();
		try {
			s = connection.prepareStatement(sql);
			ResultSet result = s.executeQuery();
			while(result.next()) {
				Loading l = new Loading(result.getString("kolonka_id"), 
						result.getString("fuel_type"), 
						result.getInt("fuel_quantity"), 
						result.getTimestamp("columnIndex").toLocalDateTime());
				if(!loadings.containsKey(result.getString("kolonka_id"))) {
					loadings.put(result.getString("kolonka_id"), new TreeSet<>());
				}
				loadings.get(result.getString("kolonka_id")).add(l);
			}
			for(Map.Entry<String, TreeSet<Loading>> e: loadings.entrySet()) {
				System.out.println(e.getKey());
				for(Loading l: e.getValue()){
					System.out.println(l);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertIntoDB(FuelType fuel, int amount, int kolonka, LocalDateTime date) {
		try {
			String sql = "INSERT INTO loading_stations (fuel_type,fuel_quantity,koloka_id,loading_time) VALUES(?,?,?,?)";
			PreparedStatement s = connection.prepareStatement(sql);
			s.setString(1, fuel.toString());
			s.setInt(2, amount);
			s.setString(3, "Kolonka " + kolonka);
			s.setTimestamp(4, Timestamp.valueOf(date));
			s.executeUpdate(); 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
