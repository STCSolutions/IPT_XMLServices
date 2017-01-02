package com.DB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionManager {
	public ConnectionManager(){

            Properties ProbFile=new Properties();
        try {
            ProbFile.load(new FileInputStream(new File("C:\\STCs\\Config.properties")));
        } catch (IOException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        url =ProbFile.getProperty("DataBaseUrl");
        userName =ProbFile.getProperty("DataBaseUserName");
        password =ProbFile.getProperty("DataBasePass");
		if(connection == null){
			loadDriver();
			connection = createConnection();
		}
	}
	
	private void loadDriver(){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Connection createConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, userName, password);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public Connection getConnection(){
		if(isClosed())
			return connection = createConnection();
		else 
			return connection;
	}
	
	protected boolean isClosed(){
		boolean isClosed = false;
		try {
			isClosed = connection.isClosed();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isClosed;
	}
	
	public Statement createStatement(){
		Statement statement = null;
		try {
			statement = getConnection().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statement;
	}
	
	public boolean add(String sql){
		statement = createStatement();	
		
		try {
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			                 System.out.println("Error In DB OPerations'Add Query' That: "+e.getMessage());
			return false;
		}
		return true;
	}
	
	public void setAutoCommit(boolean autoCommit){
		try {
			connection.setAutoCommit(autoCommit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteRow(String sql){
		try {
			if(statement == null)
				statement = createStatement();
			statement.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	
	
	public void commit(){
		try {
			if(!connection.isClosed())
				connection.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet getRows(String sql){
		try {
			if(statement == null)
				statement = createStatement();
			result = statement.executeQuery(sql);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return result;
	}
	
	private static Connection connection;
	private Statement statement;
	private ResultSet result;
	private String url = "";//"jdbc:mysql://192.168.1.121:3306/myvox";
	private String userName="";//"root";
	private String password = "";//"sa";
	private String driver="com.mysql.jdbc.Driver";

}
