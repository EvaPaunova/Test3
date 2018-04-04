package liutenitsa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

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
	
	public void insertLiutenitsa(int liutenitsa_id, String date, int quantity, String baba_name) {
		try {
			String sql = "INSERT INTO lutenica (liutenitsa_id, date, quantity, baba_name) VALUES(?,?,?,?)";
			PreparedStatement s = connection.prepareStatement(sql);
			s.setInt(1, liutenitsa_id);
			s.setTimestamp(2, Timestamp.valueOf(date));
			s.setInt(3, quantity);
			s.setString(4, baba_name);;
			s.executeUpdate(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void bestMoma() {
		String sql = "SELECT name, age, SUM(quantity) as c FROM nabrano GROUP BY moma_name ORDER BY c DESC LIMIT 1";
		PreparedStatement s;
		try {
			s = connection.prepareStatement(sql);
			ResultSet result = s.executeQuery();
			while(result.next()) {
				System.out.println(result.getString("name") + result.getInt("age"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void partidi() {
		String sql = "SELECT date, COUNT(*) as c, SUM(quantity) as kg FROM lutenica GROUP BY date";
		PreparedStatement s;
		try {
			s = connection.prepareStatement(sql);
			ResultSet result = s.executeQuery();
			while(result.next()) {
				System.out.println(result.getString("date") + result.getInt("s") + result.getInt("kg"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void bestZelenchuk() {
		String sql = "SELECT veggie_name, SUM(quantity) as c FROM nabrano GROUP BY veggie_name ORDER BY c DESC LIMIT 1";
		PreparedStatement s;
		try {
			s = connection.prepareStatement(sql);
			ResultSet result = s.executeQuery();
			System.out.println("Nai bran zelenchuk");
			while(result.next()) {
				System.out.println(result.getString("veggie_name"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void bestBaba() {
		String sql = "SELECT baba_name, SUM(quantity) as c FROM lutenica GROUP BY baba_name ORDER BY c DESC LIMIT 1";
		PreparedStatement s;
		try {
			s = connection.prepareStatement(sql);
			ResultSet result = s.executeQuery();
			System.out.println("Best baba");
			while(result.next()) {
				System.out.println(result.getString("Baba_name"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
