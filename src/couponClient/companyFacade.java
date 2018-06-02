package couponClient;

import java.util.Collection;

import cop.Coupon;
import cop.couponType;
import dao.companyDBDAO;
import dao.couponDBDAO;

public class companyFacade implements couponClientFacade {
	
	private couponDBDAO couponDBDAO;
	private companyDBDAO compDBDAO;
	String loginCompany;
	
	//constructor
	public companyFacade() {
		
		compDBDAO=new companyDBDAO();
		couponDBDAO=new couponDBDAO();
		
	}
	

	
	public void CreateCoupon(Coupon coup) {
		
		couponDBDAO.createCoupon(coup);	
	}
	
	public void removeCoupon(Coupon coup) {
		
		couponDBDAO.removeCoupon(coup);
	}
	
	public void updateCoupon(Coupon coup) {
		
		couponDBDAO.updateCoupon(coup);
	}
	
	public Coupon getCoupon(int id) {
		return couponDBDAO.getCoupon(id);
	}
	
	public Collection<Coupon> getAllCoupon()
	{
		return couponDBDAO.getAllCoupon();
	}
	
	public Collection<Coupon> getCouponByType(couponType couponType)
	{
		return couponDBDAO.getCouponbyType(couponType);
	}
	
	@Override
	public couponClientFacade login(String name, String password, clientType type) {
		loginCompany = compDBDAO.getCompanyByName(name);
		if(compDBDAO.login(name, password))
		{
			switch (type) {
			    case adminLogin:
			        return adminFacade.getInstance();
			    case companyLogin:
			    	return new companyFacade();
			    case customerLogin:
			    	return new customerFacade();
			}
		}
		
		return null;
	}

}
