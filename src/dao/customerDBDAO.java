package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import cop.ConnectionPool;
import cop.Coupon;
import cop.Customer;
import cop.couponType;

public class customerDBDAO implements customerDAO  {
	
	private ConnectionPool pool;
	
	public customerDBDAO() {
		this.pool = ConnectionPool.getInstance();
	}

	//create new customer
	@Override
	public void createCustomer(Customer cust) {

		//Checking if ID Exists
		Customer tmp = getCustomer(cust.getId());		
		
		if(tmp != null) {
			Connection conn = null;
			Statement stmt = null;
			try{
				//Open a connection
				System.out.println("Connecting to database...");
				conn = pool.getConnectionFromPool(); 
				//Execute a query
				System.out.println("Creating statement...");
				stmt = conn.createStatement();
				String sql;
				sql ="INSERT INTO customer " +"VALUES ("+cust.getId()+", '"+cust.getCustName()+"','"+cust.getPassword()+"')";
				stmt.executeUpdate(sql);
				System.out.println("Inserted records into the table...");
		
			}catch(SQLException se){
				//Handle errors for JDBC
				se.printStackTrace();
			}
			finally{
				//finally block used to close resources
				try{
					if(stmt!=null)
						stmt.close();
				}catch(SQLException se2){
				}// nothing we can do
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
		}
		else {
		System.out.println("Company Already Exists");
		}
	}
	
	//Remove Customer
	@Override
	public void removeCustomer(Customer cust) {

		Connection conn = null;
		Statement stmt = null;
		try{
			//Open a connection
			System.out.println("Connecting to database...");
			conn = pool.getConnectionFromPool(); 
			//Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			//delete company  
			sql ="DELETE from customer WHERE ID ="+ cust.getId() + ";";
			stmt.executeUpdate(sql);
			System.out.println("You have deleted the Customer!");
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
		//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
	}

	// Update Customer
	@Override
	public void updateCustomer(Customer cust) {

		Connection conn = null;
		Statement stmt = null;
		try{
			// Open a connection
			System.out.println("Connecting to database...");
			conn = pool.getConnectionFromPool(); 
			// Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			// update Company
			sql ="UPDATE customer SET CUST_NAME= '"+ cust.getCustName()+"',PASSWORD='"+cust.getPassword()+"' WHERE ID="+cust.getId()+";";
			stmt.executeUpdate(sql);
			System.out.println("customer Updated into the table...");
			
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
		//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally{
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
	}

	//Get Customer
	@Override
	public Customer getCustomer(int id) {
		Customer cust = new Customer();
		Connection conn = null;
		Statement stmt = null;
		try{
			//STEP 2: Open a connection
			System.out.println("Connecting to database...");
			conn = pool.getConnectionFromPool(); 
			//STEP 3: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql ="SELECT * FROM customer WHERE ID='" + id + "';" ;
			ResultSet rs = stmt.executeQuery(sql);
	
			if(rs.next()) {
			
				// Set Select Values
				cust.setId(id);
				cust.setCustName(rs.getString("CUST_NAME"));
				cust.setPassword(rs.getString("PASSWORD"));
			
			}
			else {
				System.out.println("wrong Id Try again :-)");
				return null;
			}
			// close ResultSet connections 
			rs.close();

		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
		//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally{
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}

		return cust;
	}

	//Get Customers
	@Override
	public Collection<Customer> getAllCustomers(){
		
		Collection<Customer> custList= new HashSet<Customer>();
		Customer cust = new Customer();
		Connection conn = null;
		Statement stmt = null;
		try{
			//Open a connection
			System.out.println("Connecting to database...");
			conn = pool.getConnectionFromPool(); 

			//Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM customer";
			ResultSet rs = stmt.executeQuery(sql);
			//Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				cust.setId(rs.getInt("ID"));
				cust.setCustName(rs.getString("CUST_NAME"));
				cust.setPassword(rs.getString("PASSWORD"));
				custList.add(cust);
				cust = new Customer();
			}
			
			//Clean environment
			rs.close();

		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
		
		return custList;
	}

	//Get Coupons
	@Override
	public Collection<Coupon> getCoupons() {
		Collection<Coupon> coupList= new HashSet<Coupon>();
		Collection<Customer> customerList= getAllCustomers();
		Coupon coupon = new Coupon();
		
		Connection conn = null;
		Statement stmt = null;
		try{
			//Open a connection
			System.out.println("Connecting to database...");
			conn = pool.getConnectionFromPool(); 
			String sql;
			for (Customer customer : customerList) {
				//Execute a query
				System.out.println("Creating statement...");
				stmt = conn.createStatement();
				sql = "SELECT coupon.ID, coupon.TITLE, coupon.START_DATE, coupon.END_DATE, coupon.TYPE, coupon.AMOUNT, coupon.MESSAGE, coupon.PRICE, coupon.IMAGE FROM coupon,company_coupon WHERE customer_coupon.ID='" + customer.getId() + "' AND  customer_coupon.ID = coupon.ID ;"; 
				ResultSet rs = stmt.executeQuery(sql);
				//Extract data from result set
				while(rs.next()){
					coupon.setId(rs.getInt("ID"));
					coupon.setTitle(rs.getString("TITLE"));
					coupon.setStartDate(rs.getDate("START_DATE"));
					coupon.setEndDate(rs.getDate("END_DATE"));
					couponType copTp = couponType.valueOf(rs.getString("coupom.TYPE"));
					coupon.setType(copTp);
					coupon.setAmount(rs.getInt("AMOUNT"));
					coupon.setPrice(rs.getDouble("PRICE"));
					coupon.setImage(rs.getString("IMAGE"));
					coupon.setMessage(rs.getString("MESSAGE"));
					//adding coupon to the list 
					coupList.add(coupon);
					coupon = new Coupon();
				}
				//Clean-up environment
				rs.close();
			}
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
	
		return coupList;
	}

	// Checking credentials 
	@Override
	public boolean login(String custName, String password) {
		
		Collection<Customer> customerList = getAllCustomers();
		
		//checking credentials
		for (Customer customer : customerList) {
			if(custName.equals(customer.getCustName()) ) {
				if(password.equals(customer.getPassword())) {
					return true; 
					}
				else {
					System.out.println("Wrong Pass!!");
					}
			}
				else
				{
					System.out.println("Wrong User!!");
				}		
			
		}
		
		return false;
	}
}
