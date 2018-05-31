package dao;

import java.util.Collection;

import cop.Company;
import cop.Coupon;

public interface companyDAO {
	
	public void createCompany (Company cmp);
	public void removeCompany(Company cmp);
	public void updateCompany(Company cmp);
	public Company getCompany(int id);
	public Collection<Company> getAllCompanies();
	public Collection <Coupon> getCoupons();
	public boolean login (String compName,String password);

}
