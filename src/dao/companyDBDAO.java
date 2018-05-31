package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;

import cop.Company;
import cop.ConnectionPool;
import cop.Coupon;
import cop.couponType;

public class companyDBDAO implements companyDAO {
	
	private ConnectionPool pool;

	public companyDBDAO() {
		this.pool = ConnectionPool.getInstance();
	}
	
	// Create Company
	@Override
	public void createCompany(Company cmp) {
		Connection conn = null;
		Statement stmt = null;
		ConnectionPool conpool = new ConnectionPool();
		conn  = conpool.getConnectionFromPool(); 
		try {
			stmt = conn.createStatement();
			String sql;
			sql ="INSERT INTO company " +"VALUES ("+cmp.getId()+",'"+cmp.getCompName()+"' ,'"+cmp.getPassword()+"', '"+cmp.getEmail()+"');";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			stmt.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Remove Company 
	@Override
	public void removeCompany(Company cmp) {
		
		Connection conn = null;
		Statement stmt = null;
		ConnectionPool conpool = new ConnectionPool();
		conn  = conpool.getConnectionFromPool(); 
		try {
			stmt = conn.createStatement();
			String sql;
			sql =("DELETE FROM company WHERE id ="+cmp.getId()) ;
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			stmt.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Update Company
	@Override
	public void updateCompany(Company cmp) {
		

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
			//update the company details 
			sql ="UPDATE company SET COMP_NAME='"+ cmp.getCompName()+"',PASSWORD='"+cmp.getPassword()+"',EMAIL='"+cmp.getEmail()+"' WHERE ID="+cmp.getId()+";";
			stmt.executeUpdate(sql);
			System.out.println("Company Updated into the table...");
			
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
	
	// Get company by ID
	@Override
	public Company getCompany(int id) {
		
		Company comp = new Company();
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
			sql ="SELECT * FROM company WHERE ID='" + id + "';" ;
			ResultSet rs = stmt.executeQuery(sql);
	
			if(rs.next()) {
			
				// Set all the select Values by id 
				comp.setId(id);
				comp.setCompName(rs.getString("COMP_NAME"));
				comp.setPassword(rs.getString("PASSWORD"));
				comp.setEmail(rs.getString("EMAIL"));
			}
			else {
				System.out.println("THERE IS NO SUCH COMPANY ID !!");
			}
			// close ResultSet connections 
			rs.close();

		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
		
			e.printStackTrace();
		}
		finally{
			
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

		return comp;
	}
	
	//get all companies
	@Override
	public Collection<Company> getAllCompanies(){
		
		Collection<Company> companyList= new HashSet<Company>();
		Company comp=new Company();
		Connection conn = null;
		Statement stmt = null;
		try{
			//Open a connection
			System.out.println("Connecting to database...");
			conn = pool.getConnectionFromPool(); 

			// Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM company;";
			ResultSet rs = stmt.executeQuery(sql);
			// Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				comp.setId(rs.getInt("ID"));
				comp.setCompName(rs.getString("COMP_NAME"));
				comp.setPassword(rs.getString("PASSWORD"));
				comp.setEmail(rs.getString("EMAIL"));
				companyList.add(comp);
				comp = new Company();
			}
			
			//Clean-up environment
			rs.close();

		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
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
		return companyList;
	}
	
	//get coupons 
	@Override
	public Collection<Coupon> getCoupons() {
		Collection<Coupon> couponList= new HashSet<Coupon>();
		Collection<Company> companyList= getAllCompanies();
		Coupon coupon = new Coupon();
		
		Connection conn = null;
		Statement stmt = null;
		try{
			//STEP 1: Open a connection
			System.out.println("Connecting to database...");
			conn = pool.getConnectionFromPool(); 

			
			String sql;
			for (Company company : companyList) {
				//Execute a query
				System.out.println("Creating statement...");
				stmt = conn.createStatement();
				sql = "SELECT coupon.ID, coupon.TITLE, coupon.START_DATE, coupon.END_DATE, coupon.TYPE, coupon.AMOUNT, coupon.MESSAGE, coupon.PRICE, coupon.IMAGE FROM coupon,company_coupon WHERE company_coupon.ID='" + company.getId() + "' AND  company_coupon.ID = coupon.ID ;"; 
				ResultSet rs = stmt.executeQuery(sql);
				//Extract data from result set
				while(rs.next()){
					//Retrieve by column name
					coupon.setId(rs.getInt("ID"));
					coupon.setTitle(rs.getString("TITLE"));
					coupon.setStartDate(rs.getDate("START_DATE"));
					coupon.setEndDate(rs.getDate("END_DATE"));
					couponType compType = couponType.valueOf(rs.getString("coupom.TYPE"));
					coupon.setType(compType);
					coupon.setAmount(rs.getInt("AMOUNT"));
					coupon.setPrice(rs.getDouble("PRICE"));
					coupon.setImage(rs.getString("IMAGE"));
					//adding coupon to the list 
					couponList.add(coupon);
					coupon = null;
				}
				//STEP 4: Clean-up environment
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
	
		return couponList;
	}

	// checking credentials 
	@Override
	public boolean login(String compName, String password) {
		
		Collection<Company>compList=getAllCompanies();
		
		for(Company comp:compList) {
			
			if(compName.equals(comp.getCompName()) ) {
				if(password.equals(comp.getPassword())) {
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

	public String getCompanyByName(String name) {
		
		Company comp = new Company();
		return comp.getCompName();
	}
}
