package cop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConnectionPool {
	
	
	static String DB_USER_NAME = "root";
	static String DB_PASSWORD = "ADMIN";
	static String DB_URL = "jdbc:mysql://localhost:3306/coupons?useSSL=false";
	static String DB_DRIVER = "com.mysql.jdbc.Driver";
	static Integer DB_MAX_CONNECTIONS = 5;
	private static ConnectionPool conPool=null;
	
	private List<Connection>availableConnections = new ArrayList<Connection>();
	
	
	public ConnectionPool()
	 {
	  initializeConnectionPool();
	 }

	//initialize connection
	 private void initializeConnectionPool()
	 {
		 if(availableConnections.size() <= 0)
		 {
			 for(int i=0; i < DB_MAX_CONNECTIONS;i++) {
			  	availableConnections.add(createNewConnectionForPool());
			 }
		 }
	 }
/*	 
	 private synchronized boolean checkIfConnectionPoolIsFull()
	 {
	  final int MAX_POOL_SIZE = DB_MAX_CONNECTIONS;

	  if(availableConnections.size() < MAX_POOL_SIZE)
	  {
	   return false;
	  }

	  return true;
	 }
*/
	 
	 
	 public static ConnectionPool getInstance() {
		 if(conPool== null)
		 {
			 conPool = new ConnectionPool();
		 }
		 return conPool;
	 }
	 
	 
	// create new connection
	 public static Connection createNewConnectionForPool()
	 {
		 Connection connection=null;
				 try {
			   Class.forName(DB_DRIVER);
			   connection = DriverManager.getConnection(
			    DB_URL, DB_USER_NAME,DB_PASSWORD);
			  } catch (ClassNotFoundException e) {
			   e.printStackTrace();
			  } catch (SQLException e) {
			   e.printStackTrace();
			  }
	  return connection;

	 }
	 
	 //get connection
	 public synchronized Connection getConnectionFromPool()
	 {
	  Connection connection = null;
	  if(availableConnections.size() > 0)
	  {
	 	 connection = availableConnections.get(0);
	 	 availableConnections.remove(0);
	 	 return connection;
	  }
	  return createNewConnectionForPool();
	 }

	public void closeAllConnections() {
		Iterator<Connection> itr = availableConnections.iterator();
		while(itr.hasNext()) {
			try {
				itr.next().close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
		//notify all threads
		System.err.println("Close all Connections "+availableConnections.toString().replaceAll("com.mysql.jdbc.JDBC4Connection@", "@"));
		
	}
	 
}
