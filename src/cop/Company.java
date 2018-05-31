package cop;

import java.util.Collection;

public class Company {
	
	private int id;
	private String compName;
	private String password;
	private String email;
	private Collection<Coupon>coupons;
	
	public Company(int id, String compName, String password,String email, Collection<Coupon> coupons) {
		super();
		this.id = id;
		this.compName = compName;
		this.password = password;
		this.email= email;
		this.coupons = coupons;
		
	}
	
	//empty constructor
	public Company()
	{
		
	}
	
	public String toString() {
		return (this.id+"ID"+this.compName+"Company Name"+this.password+"Password"+this.coupons+"Coupons");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
