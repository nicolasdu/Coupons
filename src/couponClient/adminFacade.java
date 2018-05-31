package couponClient;

import java.util.Collection;

import cop.Company;
import cop.Customer;
import dao.companyDBDAO;
import dao.couponDBDAO;
import dao.customerDBDAO;

public class adminFacade implements couponClientFacade{
	
	companyDBDAO company;
	couponDBDAO coupon;
	customerDBDAO customer;
	static adminFacade adminFacade1;

	public adminFacade() {
		
		this.company = new companyDBDAO();
		this.coupon = new couponDBDAO();
		this.customer = new customerDBDAO();
	}
	
	public void createCompany (Company cmp) {
		
		company.createCompany(cmp);
	}
	
	public void removeCompany (Company cmp) {
		
		company.removeCompany(cmp);
	}
	
	public void updateCompany (Company cmp) {
		
		company.updateCompany(cmp);
	}
	
	public Company getCompany(int id) {
		
		return company.getCompany(id);		
	}
	
	public Collection<Company> getAllCompanies(){
		
		return company.getAllCompanies();
	}
	
	public void createCustomer(Customer cust)
	{
		customer.createCustomer(cust);
	}

	public void removeCustomer(Customer cust)
	{
		customer.removeCustomer(cust);
	}
	
	public void updateCustomer(Customer cust)
	{
		customer.updateCustomer(cust);
	}

	public Customer getCustomer(int id)
	{
		return customer.getCustomer(id);
	}

	public Collection<Customer> getAllCustomer()
	{
		return customer.getAllCustomers();
	}
	
	public static adminFacade getInstance()
	{
		if(adminFacade1 == null)
		{
			adminFacade1 = new adminFacade();
		}
		return adminFacade1;
	}
	
	@Override
	public couponClientFacade login(String name, String password,clientType type) {
		if(name.equals("admin"))
		{
			if(password.equals("1234"))
			{
				switch (type) {
			    case adminLogin:
			        return new adminFacade();
			    case companyLogin:
			    	return new companyFacade();
			    case customerLogin:
			    	return new customerFacade();			
			    }
			}
			else
			{
				System.out.println("Wrong Password ");
			}
		}
		else
		{
			System.out.println("Wrong Username");
		}
		return null;
	}
}
