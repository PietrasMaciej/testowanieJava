package com.example.restservicedemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.restservicedemo.domain.Bike;
import com.example.restservicedemo.domain.Person;

public class BikeManager {
	
	private Connection connection;
	
	private static final String URL = "jdbc:hsqldb:hsql://localhost/workdb";
	private static final String CREATE_TABLE_BIKE = "CREATE TABLE Bike(b_id bigint PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY, make varchar(20), model varchar(20), yop integer, owner_id bigint FOREIGN KEY references Person(p_id))";

	
	private PreparedStatement addBikesStmt;
	private PreparedStatement deleteAllBikesStmt;
	private PreparedStatement getAllBikesStmt;
	private PreparedStatement getBikeByIdStmt;
	private PreparedStatement getBikesWithOwnerIdStmt;
	private PreparedStatement getBikeWithOwnerStmt;
	private PreparedStatement createBikeTableStmt;
	private PreparedStatement dropBikeTableStmt;
	private PreparedStatement deleteBikeStmt;
	private PreparedStatement sellBikeStmt;
	
	private Statement statement;
	
	private PersonManager pm = new PersonManager();
	
	public BikeManager() {
		try {
			connection = DriverManager.getConnection(URL);
			statement = connection.createStatement();
			
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			
			while (rs.next()) {
				if ("Bike".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}
			
			if (!tableExists)
				statement.executeUpdate(CREATE_TABLE_BIKE);

			addBikesStmt = connection
					.prepareStatement("INSERT INTO Bike (b_id, make, model, yop, owner_id) VALUES (?, ?, ?, ?, ?)");
			deleteAllBikesStmt = connection
					.prepareStatement("DELETE FROM Bike");
			getAllBikesStmt = connection
					.prepareStatement("SELECT b_id, make, model, yop, owner_id FROM Bike");
			getBikeByIdStmt = connection
					.prepareStatement("SELECT b_id, make, model, yop, owner_id FROM Bike where b_id = ?");
			getBikesWithOwnerIdStmt = connection
					.prepareStatement("SELECT b_id, make, model, yop, owner_id FROM Bike where owner_id = ?");
			getBikeWithOwnerStmt = connection.prepareStatement(
					"SELECT p_id, name, yob, b_id, make, model, yop, owner_id FROM Person JOIN Bike ON b_id = ?");
			createBikeTableStmt = connection
					.prepareStatement(CREATE_TABLE_BIKE);
			dropBikeTableStmt = connection
					.prepareStatement("DROP TABLE Bike");
			deleteBikeStmt = connection
					.prepareStatement("DELETE FROM Bike WHERE b_id = ?");
			sellBikeStmt = connection
					.prepareStatement("UPDATE Bike SET owner_id = ? WHERE b_id = ?");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	Connection getConnnection() {
		return connection;
	}
	
	public void clearBikes() {
		try {
			deleteAllBikesStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void clearBike(Long id) {
		try {
			deleteBikeStmt.setLong(1, id);
			deleteBikeStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int addBike(Bike bike) {
		int count = 0;
		
		try {
			addBikesStmt.setLong(1, bike.getId());
			addBikesStmt.setString(2, bike.getMake());
			addBikesStmt.setString(3, bike.getModel());
			addBikesStmt.setInt(4, bike.getYop());
			addBikesStmt.setLong(5, bike.getOwner().getId());
			
			count = addBikesStmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public List<Bike> getAllBikes() {
		List<Bike> bikes = new ArrayList<Bike>();
		
		try {
			ResultSet rs = getAllBikesStmt.executeQuery();
			
			while (rs.next()) {
				Bike b = new Bike();
				b.setId(rs.getInt("b_id"));
				b.setMake(rs.getString("make"));
				b.setModel(rs.getString("model"));
				b.setYop(rs.getInt("yop"));
				b.setOwner(pm.getPerson(rs.getLong("owner_id")));
				bikes.add(b);
			}
 		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bikes;
	}
	
	public Bike getBike(Long id) {
		Bike b = new Bike();
		
		try {
			getBikeByIdStmt.setLong(1, id);
			ResultSet rs = getBikeByIdStmt.executeQuery();
			
			while (rs.next()) {
				b.setId(rs.getInt("b_id"));
				b.setMake(rs.getString("make"));
				b.setModel(rs.getString("model"));
				b.setYop(rs.getInt("yop"));
				b.setOwner(pm.getPerson(rs.getLong("owner_id")));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public Bike getBikeWithOwner(Bike bike) {

		Bike b = new Bike();
		try {
			
			getBikeWithOwnerStmt.setLong(1, bike.getId());
			ResultSet rs = getBikeWithOwnerStmt.executeQuery();

			while (rs.next()) {

				Person p = new Person();

				p.setId(rs.getInt("p_id"));
				p.setFirstName(rs.getString("name"));
				p.setYob(rs.getInt("yob"));

				b.setId(rs.getInt("b_id"));
				b.setMake(rs.getString("make"));
				b.setModel(rs.getString("model"));
				b.setYop(rs.getInt("yop"));

				b.setOwner(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public List<Bike> getBikesWithOwnerId(Person owner) {
		List<Bike> bikes = new ArrayList<Bike>();
		try {
			getBikesWithOwnerIdStmt.setLong(1, owner.getId());
			
			ResultSet rs = getBikesWithOwnerIdStmt.executeQuery();

			while (rs.next()) {
				Bike b = new Bike();
				b.setId(rs.getLong("id"));
				b.setMake(rs.getString("make"));
				b.setModel(rs.getString("model"));
				b.setYop(rs.getInt("yop"));	
				b.setOwner(owner);
				bikes.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bikes;
	}
	
	public int sellBike(Bike bike, Person person) {
		int count = 0;
		try {
			
			sellBikeStmt.setLong(1, person.getId());
			sellBikeStmt.setLong(2, bike.getId());
			

			count = sellBikeStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public void createBikeTable() {
		try {
			createBikeTableStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void dropBikeTable() {
		try {
			dropBikeTableStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}