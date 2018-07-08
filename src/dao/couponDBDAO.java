package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.sql.Statement;
import cop.ConnectionPool;
import cop.Coupon;
import cop.couponType;

public class couponDBDAO implements couponDAO {
	
	private ConnectionPool pool;
	
	public couponDBDAO() {
		this.pool = ConnectionPool.getInstance();
	}

	//create coupon
	@Override
	public void createCoupon(Coupon coup) {
		
		Coupon tmp = getCoupon(coup.getId());		
		
		if(tmp == null) {
			Connection conn = null;
			Statement stmt = null;
			ConnectionPool conpool = new ConnectionPool();
			conn  = conpool.getConnectionFromPool();
			try{
				//Open a connection
				System.out.println("Connecting to database...");
				conn = pool.getConnectionFromPool(); 
				//Execute a query
				System.out.println("Creating statement...");
				stmt = conn.createStatement();
				String sql;				
				sql ="INSERT INTO coupon "+"VALUES ("+coup.getId()+", '"+ coup.getTitle()+"', '" +coup.getStartDate()+"', '"+coup.getEndDate()+"', "+coup.getAmount()+", '"+coup.getMessage()+"', '"+coup.getPrice()+"', '"+coup.getImage()+"','"+coup.getType()+"')";
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
		System.out.println("Coupon already Exists");
		}
		
	}
	
	
	//remove coupon
	@Override
	public void removeCoupon(Coupon coup) {
		Coupon tmp = getCoupon(coup.getId());		
		
		if(tmp != null) {
			Connection conn = null;
			Statement stmt = null;
			try{
				// Open a connection
				System.out.println("Connecting to database...");
				conn = pool.getConnectionFromPool(); 
				// Execute a query
				System.out.println("Creating statement...");
				stmt =conn.createStatement();
				String sql;
				// delete company details 
				sql ="DELETE from coupon WHERE ID ="+ coup.getId() + ";";
				stmt.executeUpdate(sql);
				System.out.println("coupon deleted!");
				
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
		System.out.println("Coupon does not Exist !! ");
		}		
	}
	
	//Update Coupon
	@Override
	public void updateCoupon(Coupon coup) {
		
		Coupon tmp = getCoupon(coup.getId());		
		
		if(tmp != null) {
			Connection conn = null;
			Statement stmt = null;
			try{
				//STEP 2: Open a connection
				System.out.println("Connecting to database...");
				conn = pool.getConnectionFromPool(); 
				//STEP 3: Execute a query
				System.out.println("Creating statement...");
				stmt =conn.createStatement();
				String sql;				
				sql ="UPDATE coupon SET END_DATE='"+coup.getEndDate()+"',PRICE="+coup.getPrice()+" WHERE ID="+coup.getId()+";";
				stmt.executeUpdate(sql);
				System.out.println("updated records into the table...");
				
			}catch(SQLException se){
				//Handle errors for JDBC
				se.printStackTrace();
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
		else {
		System.out.println("There is no such Coupon");
		}
	}

	//get Coupon
	@Override
	public Coupon getCoupon(int id) {
		Coupon coupon=new Coupon();
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
			sql ="SELECT * FROM coupon WHERE ID='" + id + "';" ;
			ResultSet rs = stmt.executeQuery(sql);
	
			if(rs.next()) {
			
					coupon.setId(id);
					coupon.setTitle(rs.getString("TITLE"));
					coupon.setStartDate(rs.getDate("START_DATE"));
					coupon.setEndDate(rs.getDate("END_DATE"));
					couponType coupType = couponType.valueOf(rs.getString("coupon.TYPE"));
					coupon.setType(coupType);
					coupon.setAmount(rs.getInt("AMOUNT"));
					coupon.setPrice(rs.getDouble("PRICE"));
					coupon.setImage(rs.getString("IMAGE"));
					coupon.setMessage(rs.getString("MESSAGE"));
				}
			else {
				System.out.println("wrong Id Try again :-)");
				return null;
			}
			
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
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
	return coupon;
}

	//Get all the Coupons
	@Override
	public Collection<Coupon> getAllCoupon() {
		Collection<Coupon> couponList= new HashSet<Coupon>();
		Coupon coupon = new Coupon();
		Connection conn = null;
		Statement stmt = null;
		
		try{
			//STEP 1: Open a connection
			System.out.println("Connecting to database...");
			conn = pool.getConnectionFromPool(); 

			String sql;
				//STEP 2: Execute a query
				System.out.println("Creating statement...");
				stmt =conn.createStatement();
				sql = "SELECT coupon.ID, coupon.TITLE, coupon.START_DATE, coupon.END_DATE, coupon.TYPE, coupon.AMOUNT, coupon.MESSAGE, coupon.PRICE, coupon.IMAGE FROM coupon ORDER by coupon.ID;"; 
				ResultSet rs = stmt.executeQuery(sql);
				// Extract data from result set
				
				while(rs.next()){

					coupon.setId(rs.getInt("ID"));
					coupon.setTitle(rs.getString("TITLE"));
					coupon.setStartDate(rs.getDate("START_DATE"));
					coupon.setEndDate(rs.getDate("END_DATE"));
					couponType coupType = couponType.valueOf(rs.getString("coupon.TYPE"));
					coupon.setType(coupType);
					coupon.setAmount(rs.getInt("AMOUNT"));
					coupon.setPrice(rs.getDouble("PRICE"));
					coupon.setImage(rs.getString("IMAGE"));
					coupon.setMessage(rs.getString("MESSAGE"));
					//add it to the List
					couponList.add(coupon);
					coupon = new Coupon();
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
	
		return couponList;
	}

	//Get Coupon Type
	@Override
	public Collection<Coupon> getCouponbyType(couponType type) {
		
		Collection<Coupon> couponList= new ArrayList<Coupon>();
		Coupon coupon = new Coupon();
		Connection conn = null;
		Statement stmt = null;
		
		try{
			//Open a connection
			System.out.println("Connecting to database...");
			conn = pool.getConnectionFromPool(); 		
			String sql;
				//Execute a query
				System.out.println("Creating statement...");
				stmt = conn.createStatement();
				sql = "SELECT coupon.ID, coupon.TITLE, coupon.START_DATE, coupon.END_DATE, coupon.TYPE, coupon.AMOUNT, coupon.MESSAGE, coupon.PRICE, coupon.IMAGE FROM coupon WHERE coupon.TYPE = '"+type+"';"; 
				ResultSet rs = stmt.executeQuery(sql);
				//Extract data from result set
				while(rs.next()){
					
					coupon.setId(rs.getInt("ID"));
					coupon.setTitle(rs.getString("TITLE"));
					coupon.setStartDate(rs.getDate("START_DATE"));
					coupon.setEndDate(rs.getDate("END_DATE"));
					coupon.setType(type);
					coupon.setAmount(rs.getInt("AMOUNT"));
					coupon.setPrice(rs.getDouble("PRICE"));
					coupon.setImage(rs.getString("IMAGE"));
					coupon.setMessage(rs.getString("MESSAGE"));
					//adding it to the list 
					couponList.add(coupon);
					coupon = new Coupon();
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
	
		return couponList;
	}

}
