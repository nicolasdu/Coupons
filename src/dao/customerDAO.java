package dao;

import java.util.Collection;
import cop.Coupon;
import cop.Customer;


public interface customerDAO {
	
	public void createCustomer (Customer cust);
	public void removeCustomer(Customer cust);
	public void updateCustomer(Customer cust);
	public Customer getCustomer(int id);
	public Collection<Customer> getAllCustomers();
	public Collection <Coupon> getCoupons();
	public boolean login (String custName,String password);

}
