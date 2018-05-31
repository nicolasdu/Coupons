package cop;

import java.util.Collection;

public class Customer {
	
	private int id;
	private String custName;
	private String password;
	private Collection<Coupon> coupons;
	
	
	public Customer(int id, String custName, String password, Collection<Coupon> coupons) {
		super();
		this.id = id;
		this.custName = custName;
		this.password = password;
		this.coupons = coupons;
	}
	
	//Empty Constructor
	public Customer() {
	}

	public String toString() {
		return (this.id+"ID"+this.custName+"Customer Name"+this.password+"Password"+this.coupons+"Coupons");
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCustName() {
		return custName;
	}


	public void setCustName(String custName) {
		this.custName = custName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Collection<Coupon> getCoupons() {
		return coupons;
	}


	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}
	
	

}
